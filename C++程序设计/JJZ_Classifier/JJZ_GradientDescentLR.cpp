#include "JJZ_GradientDescentLR.h"
#include "JJZ_GradientDescentLR.h"
#include "JJZ_MyMatrix.h"
#include "JJZ_Classifier.h"
#include <iostream>
#include <fstream>
#include <iomanip>

JJZ_GradientDescentLR::JJZ_GradientDescentLR(int rows, int cols) :JJZ_LogisticsRegression(rows, cols) {

}

JJZ_GradientDescentLR::~JJZ_GradientDescentLR() {
	//delete[]JJZ_hessian;
}

double JJZ_GradientDescentLR::JJZ_WtX(int row)
{
	double JJZ_ans = 0;

	for (int i = 0; i < JJZ_getN(); i++)
		JJZ_ans += JJZ_getweights()[i] * JJZ_getdata()[row * (JJZ_getN() + 1) + i];
	return JJZ_ans;
}

double JJZ_GradientDescentLR::JJZ_Sigmoid(int row)
{
	double ans = JJZ_WtX(row);
	return 1 / (1 + exp(-ans));
}

void JJZ_GradientDescentLR::JJZ_Gradient(void)
{
	for (int i = 0; i < JJZ_getN(); i++)
		JJZ_nabla[i] = 0;
	for (int row = 0; row < JJZ_getM(); row++)
	{
		for (int j = 0; j < JJZ_getN(); j++)
		{
			JJZ_nabla[j] += JJZ_getdata()[row * (JJZ_getN() + 1) + j] * (JJZ_getdata()[row * (JJZ_getN() + 1) + JJZ_getN()] - JJZ_Sigmoid(row));
		}
	}
	return;
}


void JJZ_GradientDescentLR::JJZ_Train(void)
{
	for (int i = 0; i < JJZ_getN(); i++)
		JJZ_getweights()[i] = 0;
	for (int p = 0; p < JJZ_getPASS()*100; p++)
	{
		JJZ_Gradient();
		for (int i = 0; i < JJZ_getN(); i++)
		{
			JJZ_getweights()[i] += JJZ_getALPHA() * JJZ_nabla[i];
		}
	}
	return;
}

void JJZ_GradientDescentLR::JJZ_Test(double  w)
{
	int count = 0;
	for (int row = 0; row < JJZ_getM(); row++)
		if ((JJZ_Sigmoid(row) >= w && JJZ_getdata()[row * (JJZ_getN() + 1) + JJZ_getN()] == 1) || (JJZ_Sigmoid(row) < w && JJZ_getdata()[row * (JJZ_getN() + 1) + JJZ_getN()] == 0))
		{
			count++;
		}
	cout << "The accuracy is:" << (double)count / JJZ_getM() << endl;
}