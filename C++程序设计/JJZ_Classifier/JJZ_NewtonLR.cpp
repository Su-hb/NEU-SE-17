#include "JJZ_NewtonLR.h"
#include "JJZ_MyMatrix.h"
#include "JJZ_Classifier.h"
#include <iostream>
#include <fstream>
#include <iomanip>

JJZ_NewtonLR::JJZ_NewtonLR(int rows, int cols) :JJZ_LogisticsRegression(rows, cols) {
	
}

JJZ_NewtonLR::~JJZ_NewtonLR() {
	//delete[]JJZ_hessian;
}

double JJZ_NewtonLR::JJZ_WtX(int row)
{
	double JJZ_ans = 0;

	for (int i = 0; i < JJZ_getN(); i++)
		JJZ_ans += JJZ_getweights()[i] * JJZ_getdata()[row * (JJZ_getN() + 1) + i];
	return JJZ_ans;
}

double JJZ_NewtonLR::JJZ_Sigmoid(int row)
{
	double ans = JJZ_WtX(row);
	return 1 / (1 + exp(-ans));
}

void JJZ_NewtonLR::JJZ_Gradient(void)
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

void JJZ_NewtonLR::JJZ_Hessian(void)
{
	for (int i = 0; i < JJZ_getN(); i++)
		for (int j = 0; j < JJZ_getN(); j++)
			JJZ_hessian[i * JJZ_getN() + j] = 0;

	for (int row = 0; row < JJZ_getM(); row++)
	{
		double ans = JJZ_Sigmoid(row) * (JJZ_Sigmoid(row) - 1);
		for (int i = 0; i < JJZ_getN(); i++)
			for (int j = 0; j < JJZ_getN(); j++)
				JJZ_hessian[i * JJZ_getN() + j] += ans * JJZ_getdata()[row * (JJZ_getN() + 1) + i] * JJZ_getdata()[row * (JJZ_getN() + 1) + j];
	}
	return;
}

void JJZ_NewtonLR::JJZ_Train(void)
{
	double* temp = new double[JJZ_getN() * JJZ_getN()];
	double* del = new double[JJZ_getN()];
	for (int i = 0; i < JJZ_getPASS(); i++)
	{
		JJZ_Gradient();
		JJZ_Hessian();
		if (JJZ_Inverse(JJZ_hessian, temp, JJZ_getN()) == false)
		{
			cout << "There is no inverse matrix" << endl;
			exit(1);
		}
		JJZ_Multiply(temp, JJZ_nabla, del, JJZ_getN(), JJZ_getN(), 1);
		for (int i = 0; i < JJZ_getN(); i++)
		{
			JJZ_getweights()[i] -= del[i];
		}
	}
	return;
}

void JJZ_NewtonLR::JJZ_Test(double  w)
{
	int count = 0;
	for (int row = 0; row < JJZ_getM(); row++)
		if ((JJZ_Sigmoid(row) >= w && JJZ_getdata()[row * ( JJZ_getN()+ 1) + JJZ_getN()] == 1) || (JJZ_Sigmoid(row) < w && JJZ_getdata()[row * (JJZ_getN() + 1) + JJZ_getN()] == 0))
			count++;
	cout << "The accuracy is:" << (double)count / JJZ_getM() << endl;
}