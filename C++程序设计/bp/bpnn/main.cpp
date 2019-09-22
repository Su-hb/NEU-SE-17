#include<iostream>
#include<vector>
#include<string>
#include<fstream>
#include<algorithm>
#include "assert.h"
#include "bpnn.h"
using namespace std;

vector<vector<double>> input;
vector<vector<double>> target;
//找出数组中位数
int PartSort(double* arr, int start, int end)
{
	int left = start;
	int right = end;
	int key = arr[end];   //选取关键字
	while (left < right)
	{
		while (left < right && arr[left] <= key)  //左边找比key大的值
		{
			++left;
		}
		while (left < right && arr[right] >= key)  //右边找比key小的值
		{
			--right;
		}
		if (left < right)
		{
			swap(arr[left], arr[right]);  //找到之后交换左右的值
		}
	}
	swap(arr[right], arr[end]);
	return left;
}
//求一个无序数组的中位数
double GetMidNumNoSort1(double* arr, int size)
{
	assert(arr);
	int start = 0;
	int end = size - 1;
	int mid = (size - 1) / 2;
	int div = PartSort(arr, start, end);
	while (div != mid)
	{
		if (mid < div)   //左半区间找
			div = PartSort(arr, start, div - 1);
		else    //左半区间找
			div = PartSort(arr, div + 1, end);
	}
	return arr[mid];   //找到了
}
void test_iris()
{
	input.clear();
	target.clear();
	vector<int> index;
	const int hid_size = 2, out_size = 3;
	const int data_size = 150, in_size = 4;
	double high = 0.9, low = 0.1;
	target = { {high, low, low}, {low, high, low}, {low, low, high} };

	ifstream in("iris.data");
	if (!in.is_open()) {
		cout << "打不开文件" << endl;
	}
	double d1, d2, d3, d4;
	int ind;
	for (int i = 0; i < data_size; ++i)
	{
		in >> ind >> d1 >> d2 >> d3 >> d4;
		input.push_back({ d1,d2, d3, d4 });
		index.push_back(ind);
	}
	in.close();
	bpnn bpnn(in_size, hid_size, out_size);
	bpnn.set_momentum(0.3);
	bpnn.set_learn_rate(0.4);
	// 学习一半数据集50次
	cout << "index\terror\n";
	for (int i = 0; i < 50; i++)
	{
		double sumerr = 0;
		for (int j = 0; j < data_size / 6; ++j)
		{
			
			sumerr += bpnn.learn(input[j], target[index[j] - 1]);
			sumerr += bpnn.learn(input[j + 50], target[index[j + 50] - 1]);
			sumerr += bpnn.learn(input[j + 100], target[index[j + 100] - 1]);
		}
		cout << i << "\t" << sumerr << endl;
	}
	// 测试另一半数据集
	int correct = 0;
	for (int i = 0; i < data_size / 6; ++i)
		for (int j = 0; j < 3; ++j)
		{
			int r = i + 25 + 50 * j;
			auto x = bpnn.compute(input[r]);
			if (x[index[r] - 1] == *max_element(x.begin(), x.end()))
				correct++;
		}
	cout << "Accuracy:\t" << correct << "/" << data_size / 2 << endl;
}
vector<string> split(const string& str, const string& delim) {
	vector<string> res;
	if ("" == str) return res;
	//先将要切割的字符串从string类型转换为char*类型
	char* strs = new char[str.length() + 1]; //不要忘了
	strcpy(strs, str.c_str());

	char* d = new char[delim.length() + 1];
	strcpy(d, delim.c_str());

	char* p = strtok(strs, d);
	while (p) {
		string s = p; //分割得到的字符串转换为string类型
		res.push_back(s); //存入结果数组
		p = strtok(NULL, d);
	}
	return res;
}

//分割字符串
double stringToDouble(string num)
{
	bool minus = false;      //标记是否是负数  
	string real = num;       //real表示num的绝对值
	if (num.at(0) == '-')
	{
		minus = true;
		real = num.substr(1, num.size() - 1);
	}

	char c;
	int i = 0;
	double result = 0.0, dec = 10.0;
	bool isDec = false;       //标记是否有小数
	unsigned long size = real.size();
	while (i < size)
	{
		c = real.at(i);
		if (c == '.')
		{//包含小数
			isDec = true;
			i++;
			continue;
		}
		if (!isDec)
		{
			result = result * 10 + c - '0';
		}
		else
		{//识别小数点之后都进入这个分支
			result = result + (c - '0') / dec;
			dec *= 10;
		}
		i++;
	}

	if (minus == true) {
		result = -result;
	}

	return result;
}

void test_bupa() {
	input.clear();
	target.clear();
	vector<int> index;
	const int hid_size = 3, out_size = 2;
	const int data_size = 345, in_size = 6;
	double zero = 0.1, one = 0.9;
	target = {  {zero, one},{one, zero} };


	ifstream in("1bupa.txt");
	char c;
	string res;
	double d1, d2, d3, d4, d5, d6, d7;

	while (getline(in, res)) {

		vector<string> tmp = split(res, ",");
		d1 = stringToDouble(tmp[0]);
		d2 = stringToDouble(tmp[1]);
		d3 = stringToDouble(tmp[2]);
		d4 = stringToDouble(tmp[3]);
		d5 = stringToDouble(tmp[4]);
		d6 = stringToDouble(tmp[5]);
		d7 = stringToDouble(tmp[6]);
		
		input.push_back({ d1, d2, d3, d4 ,d5,d6 });

		index.push_back(d7);
	}
	in.close();
	
	bpnn bpnn(in_size, hid_size, out_size);

	bpnn.set_momentum(0.3);
	bpnn.set_learn_rate(0.4);

	double sumerr = 0;

	for (int i = 0; i < 345; i++) {
		sumerr += bpnn.learn(input[i], target[index[i] ]);
		cout << i << "\t" << sumerr << endl;
	}

	// 测试数据集
	double sum[345];
	double min = 10.0;
	double max = 0.0;
	for (int i = 0; i < 345; ++i) {
		auto x = bpnn.compute(input[i]);
		if (max < x[0]) max = x[0];
		if (min > x[0]) min = x[0];
		sum[i] = x[0];
	}
	sort(sum, sum + 345);

	double correct = 0;
	for (int i = 0; i < 345; ++i) {
		auto x = bpnn.compute(input[i]);
		double tmp = (x[0] - min) / (max - min);
		double pi = (sum[77] - min) / (max - min);
		cout << tmp << "== " << pi << endl;
		if ((tmp <= pi && index[i] == 0) || ((tmp > pi && index[i] == 1)))
			correct = correct + 1.;

		
	}
	
	double AAA = correct / 345.0;
	cout << "准确率:"<< AAA << endl;
			
			
}
int main()
{
	//test_bupa();
	test_iris();
	//	test_mnist();
	return 0;
}