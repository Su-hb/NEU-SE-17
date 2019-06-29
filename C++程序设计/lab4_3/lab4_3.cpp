// lab4_3.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Play
{
	void operator () (int i)
	{
		cout << i << " ";
	}
};


int main()
{	
	vector<int> nums;
	int num = -1;
	while (num != 0)
	{
		cin >> num;
		nums.push_back(num);
	}

	sort(nums.begin(), nums.end());
	cout << "迭代输出：";
	for (int i = 0; i < nums.size(); i++) {
		cout << nums[i] << " ";
	}

    cout << "\n迭代器输出：";
	vector<int>::iterator v = nums.begin();
	while (v != nums.end()) {
		cout <<  *v <<" ";
		v++;
	}

	cout << "\nfor_each输出：";
	for_each(nums.begin(), nums.end(), Play());


}
