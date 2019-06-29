//
// Created by happen on 9/29/18.
//



#include "skiplist.h"
#include <iostream>
#include <random>
using namespace std;
int main(int argc, char** argv) {
	SkipList* sl = new SkipList();
	srand(time(0));
	for (int i = 0; i < 100; i++) {
		const string key = to_string(rand());
		const char* value = "value";
		sl->insert(key, value, 6);
	}
	sl->print();
	sl->dump();
	
}