#include<iostream>
#include<random>
#include <fstream>
#include"skiplist.h"


SkipList::SkipList() :_count(0), _list_head(new Node("", nullptr, 0, MAX_LEVEL)), _list_tail(new Node("", nullptr, 0, MAX_LEVEL)) {
	srand(time(0));//����ʱ������
	for (int i = 0; i < MAX_LEVEL; i++) {
		_list_head->_forward[i] = _list_tail;//��ʼ��
	}
}

int SkipList::random_level() {
	//����Ӳ�
	int level = 1;
	while (level <= MAX_LEVEL)
	{
		auto num = (rand() % 100) / 101.0;
		if (num < _P) {
			level++;
		}
		else {
			break;
		}
	}
	return level;
}

Handle* SkipList::search(const std::string& key) const {
	std::shared_lock<std::shared_mutex> lock(_mutex);
	//��д��,�������н������Զ��ͷ��ڴ�
	auto cur_node = _list_head;
	for (int i = MAX_LEVEL - 1; i >= 0; i--) {
		while (cur_node->_forward[i]->_key != "" && cur_node ->_forward[i]->_key < key)
		{
			cur_node = cur_node->_forward[i];
		}
	}
	cur_node = cur_node->_forward[0];
	//cur_node���ǵ�ǰҪ��Ľڵ�
	if (cur_node->_key == key) {
		auto handle = new (std::nothrow) Handle(cur_node->_value, cur_node->_value_size);
		if (handle == nullptr) {
			std::cout << "Alloc handle failed" << std::endl;
		}
		return handle;
	}
	return nullptr;
}

Status SkipList::insert(const std::string& key, const char* value, uint64_t value_size) {
	//��д��
	std::unique_lock<std::shared_mutex> lock(_mutex);
	//���ҽڵ�ʱ����������ڵ�ǰ��Ľڵ��¼���������ڴ����½ڵ�ʱ����������
	std::vector<Node*> update_forward_nodes(MAX_LEVEL);

	auto cur_node = _list_head;

	for (int i = MAX_LEVEL - 1; i >= 0; i--) {
		while (cur_node->_forward[i]->_key != "" && cur_node->_forward[i]->_key < key) {
			cur_node = cur_node->_forward[i];
		}
		update_forward_nodes[i] = cur_node;
	}
	cur_node = cur_node->_forward[0];
	if (cur_node->_key == key) {
		//���key�Ѿ����ڣ�����value,value_size
		char* new_value = new (std::nothrow)char[value_size];
		if (new_value == nullptr) {
			std::cout << "Construct node faild, failed to allocate memory" << std::endl;
			return Status::FAIL;
		}
		memcpy(new_value, value, value_size);
		cur_node->_value.reset(new_value);
		cur_node->_value_size = value_size;
		std::cout << "Update " + key + " success" << std::endl;
	}
	else {
		_count++;//�ڵ�����һ
		int level = random_level();
		//�����½ڵ�
		Node* new_node = new Node(key, value, value_size, level);
		for (int i = 0; i < level; i++) {
			//��������Ĳ��뷽��
			new_node->_forward[i] = update_forward_nodes[i]->_forward[i];
			update_forward_nodes[i]->_forward[i] = new_node;
		}
		std::cout << "Insert " + key + " success" << std::endl;

	}
	//����ɹ�
	return Status::SUCCESS;


}

Status SkipList::remove(const std::string& key) {
	/*
		ɾ��һ���ڵ㣺
		1.���ҽڵ�
		2.�б�ɾ��
	*/
	//��д��
	std::unique_lock<std::shared_mutex> lock(_mutex);
	std::vector<Node*> update_forward_nodes(MAX_LEVEL);
	auto cur_node = _list_head;

	for (int i = MAX_LEVEL - 1; i >= 0; i--) {
		while (cur_node->_forward[i]->_key != "" && cur_node->_forward[i]->_key < key) {
			cur_node = cur_node->_forward[i];
		}
		update_forward_nodes[i] = cur_node;
	}
	cur_node = cur_node->_forward[0];
	if (cur_node->_key == key) {
		//�ҵ��ڵ�
		//�ýڵ�Ĳ�����
		int level = cur_node->_forward.size();
		for (int i = 0; i < level; i++) {
			update_forward_nodes[i]->_forward[i] = cur_node->_forward[i];
		}
		SAFE_DELETE(cur_node);
		std::cout << "Remove " + key + " success" << std::endl;
		_count--;
		return Status::SUCCESS;
		//ɾ���ɹ�
	}
	//ɾ��ʧ��,û���ҵ��ڵ�
	std::cout << "Reomve failed, do not find " + key;
	return Status::FAIL;
}

void SkipList::print() {
	/*
		��ӡ����
	*/
	//��д��
	std::shared_lock<std::shared_mutex> lock(_mutex);
	//��ӡkey
	for (int i = MAX_LEVEL - 1; i >= 0; i--) {
		auto cur_node = _list_head->_forward[i];
		while (cur_node != _list_tail) {
			std::cout << cur_node->_key + "->";
			cur_node = cur_node->_forward[i];
		}
		//��β
		std::cout << "NIL" << std::endl;
	}
	std::cout << std::endl;
	std::cout << std::endl;
	std::cout << std::endl;


	//��ӡvalue
	for (int i = MAX_LEVEL - 1; i >= 0; i--) {
		auto cur_node = _list_head->_forward[i];
		while (cur_node != _list_tail) {
			std::cout << cur_node->_key + "->";
			cur_node = cur_node->_forward[i];
		}
		std::cout << "NIL" << std::endl;
	}

}

Status SkipList::dump() {
	/*
		д�����
	*/
	std::cout<< "Dump skiplist to disk." << std::endl;
	//����
	std::shared_lock<std::shared_mutex> lock(_mutex);
	std::fstream file("./skiplist.dump", std::ios::binary | std::ios::out);
	file.write(reinterpret_cast<char*>(&_count), sizeof(uint64_t));
	auto cur_node = _list_head->_forward[0];

	while (cur_node != _list_tail) {
		uint64_t key_size = cur_node->_key.size();
		auto key = cur_node->_key.c_str();
		file.write(reinterpret_cast<char*>(&key_size), sizeof(uint64_t));
		//ǿ������ת��

		file.write(const_cast<char*>(key), key_size);
		file.write(reinterpret_cast<char*>(&cur_node->_value_size), sizeof(uint64_t));
		file.write(reinterpret_cast<char*>(cur_node->_value.get()), cur_node->_value_size);
		cur_node = cur_node->_forward[0];
	}

	file.close();

	return Status::SUCCESS;


}

Status SkipList::load() {
	std::cout << "Load skiplist from disk." << std::endl;
	std::fstream file("./skiplist.dump", std::ios::binary | std::ios::in);
	if (!file || file.eof()) {
		return Status::SUCCESS;
	}

	uint64_t* insert_count = new uint64_t;
	std::shared_ptr<uint64_t> insert_count_ptr(insert_count);
	file.read(reinterpret_cast<char*>(insert_count), sizeof(uint64_t));
	auto count = *insert_count;

	while (count-- != 0) {
		uint64_t* key_size = new uint64_t;
		std::shared_ptr<uint64_t> key_size_ptr(key_size);
		file.read(reinterpret_cast<char*>(key_size), sizeof(uint64_t));

		char* key_cstr = new char[*key_size];
		std::shared_ptr<char> key_ptr(key_cstr);
		file.read(reinterpret_cast<char*>(key_cstr), *key_size);
		std::string key(key_cstr, *key_size);

		uint64_t* value_size = new uint64_t;
		std::shared_ptr<uint64_t> value_size_ptr(value_size);
		file.read(reinterpret_cast<char*>(value_size), sizeof(uint64_t));

		char* value = new char[*value_size];
		std::shared_ptr<char> value_ptr(value);
		file.read(value, *value_size);

		insert(key, value, *value_size);
	}

	return Status::SUCCESS;
}
SkipList::~SkipList() {
	auto cur_node = _list_head->_forward[0];
	while (cur_node->_forward[0] != _list_tail) {
		auto next_node = cur_node->_forward[0];
		SAFE_DELETE(cur_node);
		cur_node = next_node;
	}

}