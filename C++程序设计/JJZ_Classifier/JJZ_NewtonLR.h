#pragma once
#include "JJZ_LogisticsRegression.h"
class JJZ_NewtonLR :
	public JJZ_LogisticsRegression
{
private:
	double* JJZ_nabla = new double[JJZ_getN()];
	double* JJZ_hessian = new double[JJZ_getN()];
private:
	double JJZ_WtX(int row);
	double JJZ_Sigmoid(int row);
	void JJZ_Gradient(void);
private:
	void JJZ_Hessian(void);
public:
	JJZ_NewtonLR(int row, int clos);
	~JJZ_NewtonLR(void);
	void JJZ_Train();
	void JJZ_Test(double w);
	double* JJZ_gethessian(void){ return JJZ_hessian; }
};

