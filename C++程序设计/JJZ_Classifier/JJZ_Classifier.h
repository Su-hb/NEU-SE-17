#pragma once
#include <string>
using namespace std;

class JJZ_Classifier
{
private:
	int JJZ_M; //number of instances
	int JJZ_N; //number of attributes

private:
	double* JJZ_data;
	double* JJZ_weights;

public:
	JJZ_Classifier(int rows, int cols);
	~JJZ_Classifier(void);
	int JJZ_getM() { return JJZ_M; };
	int JJZ_getN() { return JJZ_N; };
	double* JJZ_getdata() { return JJZ_data; }
	double* JJZ_getweights() { return JJZ_weights; }
	void JJZ_ReadData(string textName);
	//读取textName文件中的数据至data中
	void JJZ_OutputData(void);
	//输出data中的数据
	void JJZ_Preprocess(string textName);
	//预处理textName文件中的数据，存放到新文件“textName1”中
	
public:
	void JJZ_Train(void);
	void JJZ_Test(double w);
	void JJZ_SetParameter(int pass, double alpha);

};

