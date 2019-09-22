#pragma once
double JJZ_Determinant(double*, int);
bool JJZ_Inverse(double* M1, double* M2, int n);
void JJZ_Multiply(double* A, double* B, double* C, int m, int n, int k);
void JJZ_Output(double* M, int row, int col);

