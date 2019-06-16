#include "Classifier.h"
#include <iostream>
#include <fstream>
#include <cmath>
#include <vector>
#include <iomanip>

using namespace std;


Classifier::Classifier(void)
{
}


Classifier::~Classifier(void)
{
}

vector<string> split(const string& str, const string& delim) {
	vector<string> res;
	if ("" == str) return res;
	//�Ƚ�Ҫ�и���ַ�����string����ת��Ϊchar*����
	char* strs = new char[str.length() + 1]; //��Ҫ����
	strcpy(strs, str.c_str());

	char* d = new char[delim.length() + 1];
	strcpy(d, delim.c_str());

	char* p = strtok(strs, d);
	while (p) {
		string s = p; //�ָ�õ����ַ���ת��Ϊstring����
		res.push_back(s); //����������
		p = strtok(NULL, d);
	}
	return res;
}
//�ָ��ַ���
double stringToDouble(string num)
{
	bool minus = false;      //����Ƿ��Ǹ���  
	string real = num;       //real��ʾnum�ľ���ֵ
	if (num.at(0) == '-')
	{
		minus = true;
		real = num.substr(1, num.size() - 1);
	}

	char c;
	int i = 0;
	double result = 0.0, dec = 10.0;
	bool isDec = false;       //����Ƿ���С��
	unsigned long size = real.size();
	while (i < size)
	{
		c = real.at(i);
		if (c == '.')
		{//����С��
			isDec = true;
			i++;
			continue;
		}
		if (!isDec)
		{
			result = result * 10 + c - '0';
		}
		else
		{//ʶ��С����֮�󶼽��������֧
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
//�ַ���תΪDouble��
void Classifier::ReadData(string textName)
{
	ifstream inf(textName);
	char c;
	
	int row = 0;
	int col = 0;
	string res;
	while (getline(inf, res)) {

		vector<string> tmp = split(res, ",");
		col = tmp.size();
		for (int i = 1; i <= col; i++)
		{
			data[row][0] = 1;
			data[row][i] = stringToDouble(tmp[i-1]);
		}
		row++;
	}
	M = row;
	N = col;
	inf.close();
}

void Classifier::OutputData(void)
{
	cout<<left;
	for(int i=0;i<M;i++)
	{
		for(int j=0;j<N+1;j++)
			cout<<setw(10)<<data[i][j]<<"  ";
		cout<<endl;
	}
}

double Classifier::Sigmoid(double z) {
	double res = 1 / (1 + exp(-1 * z));
	return res;
}
void Classifier::test(double w) {
	
	//double weightt[7] = { 1, -0.211268,-1.59339,-9.64315,10.0794,5.06619,-1.61506 };//5000�ε��� w 0.516 ׼ȷ�� 71.3%
	//double weightt[7] = { 1,-0.253365701683853, 1.13, -4.51990652566481, 4.87, 1.792588002524339, -3.449983335818563 };//�Ŵ��㷨 250�ε��� ׼ȷ�� 67.4%
	int success = 0;
	
	for (int i = 0; i < M; i++) {
		double tmp = 0;
		for (int j = 1; j < N; j++) {
			
			tmp += data[i][j] * weight[j];//���Լ�¼Ȩ��ʱ��Ҫ��weight �޸�Ϊ weightt
		}
		
		if (Sigmoid(tmp) > w && data[i][7] == 1)success++;
		if (Sigmoid(tmp) <= w && data[i][7] == 0)success++;
	}

	cout << "׼ȷ��" << (double)success/M <<endl;

}
void Classifier::train(void ) {
	double alpha = 0.01;
	

	for (int i = 1; i < N; i++) {
		weight[i] = 1;
	}

	int num = 0;//��������
	double Fx[MM];
	double error[MM];
	double grad[NN];

	double error_sum = 1;
	while (num < 5000 || error_sum < 0.05) {

		if (num > 3000) {
			alpha = 15 / num;
		}
		num++;
		for (int i = 0; i < M; i++) {
			double tmp = 0;
			for (int j = 1; j < N; j++) {
				tmp += data[i][j] * weight[j];
			}
			Fx[i] = Sigmoid(tmp);

			error[i] = data[i][N] - Fx[i];

		}
		for (int i = 1; i < N; i++) {

			double tmp = 0;
			for (int j = 0; j < M; j++) {
				tmp += data[j][i] * error[j];
			}
			grad[i] = tmp;
		}
		cout << "������" << num << "��" << "  ";
		for (int i = 1; i < N; i++) {
			weight[i] = weight[i] + alpha * grad[i];
		}
		error_sum = 0;
		for (int i = 0; i < M; i++) {
			error_sum += error[i];
		}
		cout << error_sum << endl;
	}

	cout << "Ȩ��Ϊ ";
	for (int i = 1; i < N; i++) {
		cout << weight[i] << " ";
	}
	cout << endl;
}

void Classifier::Preprocess(string textName)
{
	ReadData(textName);
	double maxMin[NN][2];
	for(int i=0;i<N;i++)
	{
		maxMin[i][0]=-100000;
		maxMin[i][1]=100000;
	}

	for(int row=0;row<M;row++)
		for(int i=1;i<N;i++)
		{
			if(data[row][i] > maxMin[i][0]) 
				maxMin[i][0]=data[row][i];
			if(data[row][i] < maxMin[i][1]) 
				maxMin[i][1]=data[row][i];
		}
	
	
	ofstream outs("1"+textName);
	double d;
	for(int row=0;row<M;row++){
		for(int i=1;i<N;i++)
		{
			d = (data[row][i]-maxMin[i][1])/(maxMin[i][0]-maxMin[i][1]);
			outs<<d;
			outs.put(',');
		}
		outs<<data[row][N]-1; //���liver���ݼ���ת�����Ϊ0��1
		outs.put('\n');
    }
	outs.close();
}