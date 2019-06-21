#include "JJZ_Classifier.h"
#include <iostream>
#include <fstream>
#include <iomanip>
#include "JJZ_MyMatrix.h"

using namespace std;


JJZ_Classifier::JJZ_Classifier(int rows, int cols)
{
	JJZ_M = rows;
	JJZ_N = cols;
	JJZ_data = new double[JJZ_M * (JJZ_N + 1)];
	JJZ_weights = new double[JJZ_N];

	for (int i = 0; i < JJZ_N; i++)
		JJZ_weights[i] = 0;
}

JJZ_Classifier::~JJZ_Classifier(void)
{
	delete[] JJZ_data;
	delete[] JJZ_weights;

}

void JJZ_Classifier::JJZ_ReadData(string textName)
{
	ifstream inf(textName);
	char JJZ_c;
	int JJZ_row = 0;
	while (inf.peek() != EOF) {
		for (int i = 1; i <= JJZ_N; i++)
		{
			JJZ_data[JJZ_row * (JJZ_N + 1) + 0] = 1;
			inf >> JJZ_data[JJZ_row * (JJZ_N + 1) + i];
			inf.get(JJZ_c);
		}
		JJZ_row++;
	}
	inf.close();
}

void JJZ_Classifier::JJZ_OutputData(void)
{
	cout << left;
	for (int i = 0; i < JJZ_M; i++)
	{
		for (int j = 0; j < JJZ_N + 1; j++)
			cout << setw(10) << JJZ_data[i * (JJZ_N + 1) + j] << "  ";
		cout << endl;
	}
}

void JJZ_Classifier::JJZ_Preprocess(string textName)
{
	JJZ_ReadData(textName);
	double* JJZ_maxMin = new double[JJZ_N * 2];

	for (int i = 0; i < JJZ_N; i++)
	{
		JJZ_maxMin[i * 2 + 0] = -100000;
		JJZ_maxMin[i * 2 + 1] = 100000;
	}

	for (int row = 0; row < JJZ_M; row++)
		for (int i = 1; i < JJZ_N; i++)
		{
			if (JJZ_data[row * (JJZ_N + 1) + i] > JJZ_maxMin[i * 2 + 0])
				JJZ_maxMin[i * 2 + 0] = JJZ_data[row * (JJZ_N + 1) + i];
			if (JJZ_data[row * (JJZ_N + 1) + i] < JJZ_maxMin[i * 2 + 1])
				JJZ_maxMin[i * 2 + 1] = JJZ_data[row * (JJZ_N + 1) + i];
		}

	ofstream outs("1" + textName);
	double d;
	for (int row = 0; row < JJZ_M; row++) {
		for (int i = 1; i < JJZ_N; i++)
		{
			d = (JJZ_data[row * (JJZ_N + 1) + i] - JJZ_maxMin[i * 2 + 1]) / (JJZ_maxMin[i * 2 + 0] - JJZ_maxMin[i * 2 + 1]);
			outs << d;
			outs.put(',');
		}
		outs << JJZ_data[row * (JJZ_N + 1) + JJZ_N] - 1;
		outs.put('\n');
	}
	outs.close();

	delete[] JJZ_maxMin;
}


