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
	void ReadData(string textName);//��ȡtextName�ļ��е�������data��
	void OutputData(void); //���data�е�����
	void Preprocess(string textName);//Ԥ����textName�ļ��е����ݣ���ŵ����ļ���textName1����
	double Sigmoid(double num);
};

