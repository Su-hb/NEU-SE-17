//
// Created by happen on 9/29/18.
//



#include "skiplist.h"
#include <iostream>
#include <random>
using namespace std;
int main(int argc, char** argv) {
	SkipList* sl = new SkipList();
	srand(time(NULL));
	clock_t startTime, endTime;
	startTime = clock();//计时开始
	for (int i = 0; i < 10000; i++) {
		string aaa = to_string(rand());
		sl->insert(aaa, "11111", 6);
	}
	endTime = clock();//计时结束
	cout << "The run time is: " << (double)(endTime - startTime) / CLOCKS_PER_SEC << "s" << endl;
	cout << sl->search("11")->value << endl;
	sl->print();
	sl->dump();
	
}