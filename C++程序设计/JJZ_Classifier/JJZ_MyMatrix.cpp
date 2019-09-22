#include "JJZ_MyMatrix.h"
#include <iostream>
#include <iomanip>
using namespace std;

double JJZ_Determinant(double* M, int n)
//求方阵M[n][n]所对应的行列式
{
	if (n == 1) return M[0];
	double JJZ_del = 0;
	double* JJZ_minor = new double[(n - 1) * (n - 1)];
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n - 1; j++)
		{
			for (int k = 0; k < n - 1; k++)
			{
				int JJZ_row = j + 1;
				int JJZ_col = k < i ? k : k + 1;
				JJZ_minor[j * (n - 1) + k] = M[JJZ_row * n + JJZ_col];
			}
		}
		double JJZ_cofactor = JJZ_Determinant(JJZ_minor, n - 1);
		if (i % 2 == 0)
			JJZ_del += M[i] * JJZ_cofactor;
		else
			JJZ_del -= M[i] * JJZ_cofactor;

	}
	delete[]JJZ_minor;
	return JJZ_del;
}

bool JJZ_Inverse(double* M1, double* M2, int n)
//对方阵M1[n][n]求逆，存放到结果M2[n][n]中
{
	double JJZ_del = JJZ_Determinant(M1, n);
	if (JJZ_del == 0) return false;

	double* JJZ_minor = new double[(n - 1) * (n - 1)];
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			for (int r = 0; r < n - 1; r++)
			{
				for (int c = 0; c < n - 1; c++)
				{
					int row = r < i ? r : r + 1;
					int col = c < j ? c : c + 1;
					JJZ_minor[r * (n - 1) + c] = M1[row * n + col];
				}
			}
			double del_ij = JJZ_Determinant(JJZ_minor, n - 1);
			if ((i + j) % 2 == 0)
				M2[j * n + i] = del_ij / JJZ_del;
			else
				M2[j * n + i] = -del_ij / JJZ_del;
		}
	}
	return true;
}

void JJZ_Multiply(double* A, double* B, double* C, int m, int n, int k)
//C[m][k] = A[m][n]*B[n][k]
{
	for (int i = 0; i < m; i++)
		for (int j = 0; j < k; j++)
		{
			double JJZ_temp = 0;
			for (int x = 0; x < n; x++)
				JJZ_temp += A[i * n + x] * B[x * k + j];
			C[i * k + j] = JJZ_temp;
		}
	return;
}

void JJZ_Output(double* M, int row, int col)
//Output the matrix M[row][col]
{
	cout << left;
	for (int i = 0; i < row; i++)
	{
		for (int j = 0; j < col; j++)
		{
			cout << setw(10) << M[i * row];
		}
		cout << endl;
	}
}