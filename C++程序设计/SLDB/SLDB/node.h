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
	Node(const Node& node) = delete;//��ֹ����
	Node& operator=(const Node& node) = delete; //��ֹ����
	Node(const Node&& node) = delete;//��ֹ����
	Node& operator=(const Node&& node) = delete;//��ֹ����

private:
	Node();
	std::string _key;//��ֵ��
	std::shared_ptr<char[]> _value;
	uint64_t _value_size;//ֵ�Ĵ�С
	std::vector<Node*> _forward;//�洢����һ���ڵ�

	friend class SkipList;
};
#endif