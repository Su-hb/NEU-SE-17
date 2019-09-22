#include <iostream>
#include "JJZ_GeneticAlgorithm.h"
#include <time.h>
using namespace std;



int main() {
	
	/*
	JJZ_GradientDescentLR c(345,7);
	c.JJZ_Preprocess("bupa.txt");
	c.JJZ_ReadData("1bupa.txt");
 	c.JJZ_SetParameter(10, 0.01);
	c.JJZ_Train();
	c.JJZ_Test(0.5);*/
	JJZ_GeneticAlgorithm ga(345,7);
	ga.JJZ_Preprocess("bupa.txt");
    ga.JJZ_ReadData("1bupa.txt");
	
	ga.generate_inital(7, 200);
	for (int i = 0; i < 200000; i++) {
		cout << "##########µü´ú´ÎÊý£º" << i << "############" << endl;
		ga.evolve(i,200, 7, 0.2, 0.5, 0.03);
		_sleep(10);
		//cout << rand() << endl;
	}
	ga.result(200);

	return 0;
}