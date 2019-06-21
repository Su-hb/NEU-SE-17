#include <iostream>
#include "JJZ_GradientDescentLR.h"
using namespace std;
int main() {

	JJZ_GradientDescentLR c(345,7);
	c.JJZ_Preprocess("bupa.txt");
	c.JJZ_ReadData("1bupa.txt");
 	c.JJZ_SetParameter(10, 0.01);
	c.JJZ_Train();
	c.JJZ_Test(0.5);
	return 0;
}