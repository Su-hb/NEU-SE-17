#include "JJZ_LogisticsRegression.h"
JJZ_LogisticsRegression::JJZ_LogisticsRegression(int rows, int cols) :JJZ_Classifier(rows, cols) {
	
}
JJZ_LogisticsRegression::~JJZ_LogisticsRegression() {
	//delete[] JJZ_nabla;
}
void JJZ_LogisticsRegression::JJZ_SetParameter(int pass, double alpha)
{
	
	JJZ_PASS = pass;
	JJZ_ALPHA = alpha;
}
