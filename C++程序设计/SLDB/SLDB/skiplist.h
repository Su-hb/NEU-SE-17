#include <cmath>
#include <shared_mutex>
#include "common.h"
#include "node.h"

#pragma once
#ifndef JJZ_SKIPLIST_H
#define JJZ_SKIPLIST_H
struct Handle
{
	std::shared_ptr<char[]> value;//������ָ�룬����ָ������Ϊchar[]�Ķ���
	uint16_t value_size;
	Handle(std::shared_ptr<char[]> _value, uint64_t size) : \
	value(_value), value_size(size) {}
	~Handle() {}
};

class SkipList
{
public:
	SkipList();
	~SkipList();


	void print();
	Status dump();
	Status load();
	Handle* search(const std::string& key) const;
	Status insert(const std::string& key, const char* value, uint64_t value_size);
	Status remove(const std::string& key);

	SkipList(const SkipList& s1) = delete;//���ú���
	SkipList(const SkipList&& sl) = delete;//���ú���
	SkipList& operator=(const SkipList&& sl) = delete;//���ú���

private:
	int random_level();//���������

	Node* _list_head;//����ͷ
	Node* _list_tail;//����β

	uint16_t _count;
	mutable std::shared_mutex _mutex;//��д��

	static constexpr int MAX_LEVEL = 128;//�����������
	static constexpr float _P = 0.5;//�Ӳ�ĸ���
};


#endif


