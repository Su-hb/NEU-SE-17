#include <cmath>
#include <shared_mutex>
#include "common.h"
#include "node.h"

#pragma once
#ifndef JJZ_SKIPLIST_H
#define JJZ_SKIPLIST_H
struct Handle
{
	std::shared_ptr<char[]> value;//空智能指针，可以指向类型为char[]的对象
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

	SkipList(const SkipList& s1) = delete;//禁用函数
	SkipList(const SkipList&& sl) = delete;//禁用函数
	SkipList& operator=(const SkipList&& sl) = delete;//禁用函数

private:
	int random_level();//随机层数，

	Node* _list_head;//跳表头
	Node* _list_tail;//跳表尾

	uint16_t _count;
	mutable std::shared_mutex _mutex;//读写锁

	static constexpr int MAX_LEVEL = 128;//跳表的最大层数
	static constexpr float _P = 0.5;//加层的概率
};


#endif


