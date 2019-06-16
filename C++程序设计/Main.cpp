#include <iostream>
#include <vector>
#include "Classifier.h"
using namespace std;

int main()
{
	Classifier c1;
	c1.Preprocess("bupa.txt");
	c1.ReadData("1bupa.txt");
	c1.train();
	c1.test(0.516);
	
	//c1.OutputData();
	system("pause");
	return 0;
}