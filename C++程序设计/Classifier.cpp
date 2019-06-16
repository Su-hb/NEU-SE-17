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
//字符型转为Double型
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
	
	//double weightt[7] = { 1, -0.211268,-1.59339,-9.64315,10.0794,5.06619,-1.61506 };//5000次迭代 w 0.516 准确率 71.3%
	//double weightt[7] = { 1,-0.253365701683853, 1.13, -4.51990652566481, 4.87, 1.792588002524339, -3.449983335818563 };//遗传算法 250次迭代 准确率 67.4%
	int success = 0;
	
	for (int i = 0; i < M; i++) {
		double tmp = 0;
		for (int j = 1; j < N; j++) {
			
			tmp += data[i][j] * weight[j];//测试记录权重时，要将weight 修改为 weightt
		}
		
		if (Sigmoid(tmp) > w && data[i][7] == 1)success++;
		if (Sigmoid(tmp) <= w && data[i][7] == 0)success++;
	}

	cout << "准确率" << (double)success/M <<endl;

}
void Classifier::train(void ) {
	double alpha = 0.01;
	

	for (int i = 1; i < N; i++) {
		weight[i] = 1;
	}

	int num = 0;//迭代次数
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
		cout << "迭代第" << num << "次" << "  ";
		for (int i = 1; i < N; i++) {
			weight[i] = weight[i] + alpha * grad[i];
		}
		error_sum = 0;
		for (int i = 0; i < M; i++) {
			error_sum += error[i];
		}
		cout << error_sum << endl;
	}

	cout << "权重为 ";
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
		outs<<data[row][N]-1; //针对liver数据集，转换类标为0和1
		outs.put('\n');
    }
	outs.close();
}