
#include "node.h"
#include <iostream>

Node::Node(const std::string& key, const char* value, uint64_t value_size, int level) {
	/*
		构造函数、初始化节点，如果没有成功分配内存，写入日志；
		因为有value_size 如果节点的value的长度并未有达到value_size，进行填补；
	*/
	_key = key;
	_value_size = value_size;
	char* new_value = new (std::nothrow)char[value_size];//
	if (new_value != nullptr) {
		_value = std::shared_ptr<char[]>(new_value, [](char* value_ptr) { SAFE_DELETE_ARR(value_ptr); });
		memcpy(new_value, value, value_size);
		_forward = std::vector<Node*>(level);
	}
	else {
		std::cout << "Construct node faild, failed to allocate memory"<<std::endl;
	}
}

Node::~Node() {
	_value.reset();
}