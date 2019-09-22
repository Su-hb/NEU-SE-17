#pragma once
#include "JJZ_Classifier.h"
class JJZ_LogisticsRegression :
	public JJZ_Classifier
{
private:
	int JJZ_PASS;
	double JJZ_ALPHA;

public:
	JJZ_LogisticsRegression(int rows, int cols);
	~JJZ_LogisticsRegression();
	int JJZ_getPASS() { return JJZ_PASS; }
	double JJZ_getALPHA() { return JJZ_ALPHA; }
	void JJZ_Train(void);
	void JJZ_Test(double w);
	void JJZ_SetParameter(int pass, double alpha);
};

