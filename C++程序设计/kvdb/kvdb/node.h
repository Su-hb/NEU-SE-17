#pragma once
#include <string>
#include <memory>
#include <vector>

#include "common.h"
using namespace std;
class Node {
public:
	Node(const string& key, const char* vaule, uint64_t value_size, int level);
	~Node();
	Node(const Node& node) = delete;
	Node& operator = (const Node& node) = delete;
	Node(const Node&& node) = delete;
	Node& operator = (const Node&& node) = delete;
private:
	Node();
	string _key;
	shared_ptr<char[]> _value;

};