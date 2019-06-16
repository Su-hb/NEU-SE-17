#pragma once
#include <string>
using namespace std;

#define MM 1000  //number of instances
#define NN 100    //number of attributes

class Classifier
{
private:
	int M = 0;
	int N = 0;
	double data[MM][NN];
	double logisW[NN];
	double weight[NN];
public:
	Classifier(void);
	~Classifier(void);
	void train(void);
	void test(double w);
	void ReadData(string textName);//读取textName文件中的数据至data中
	void OutputData(void); //输出data中的数据
	void Preprocess(string textName);//预处理textName文件中的数据，存放到新文件“textName1”中
	double Sigmoid(double num);
};

