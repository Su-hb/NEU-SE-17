#pragma once

#ifndef JJZ_NODE_H
#define JJZ_NODE_H
#include <cstring>
#include <memory>
#include <vector>

#include "common.h"
#include <string>
class Node
{
public:
	Node(const std::string& key, const char* value, uint64_t value_size, int level);
	~Node();
	Node(const Node& node) = delete;//禁止调用
	Node& operator=(const Node& node) = delete; //禁止调用
	Node(const Node&& node) = delete;//禁止调用
	Node& operator=(const Node&& node) = delete;//禁止调用

private:
	Node();
	std::string _key;//键值对
	std::shared_ptr<char[]> _value;
	uint64_t _value_size;//值的大小
	std::vector<Node*> _forward;//存储的下一个节点

	friend class SkipList;
};
#endif