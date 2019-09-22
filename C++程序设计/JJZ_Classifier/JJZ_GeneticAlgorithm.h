#pragma once
#include "JJZ_Classifier.h"
#include <vector>
class JJZ_GeneticAlgorithm :
	public JJZ_Classifier
{
private:
	//染色体长度
	int length;
	//随机生成初始种群

public:
	double population[200][7] ;
	double parents[200][7];

public:
	JJZ_GeneticAlgorithm(int rows, int cols);
	double getRandData(int, int);
	double Sigmoid(double ans);
	void generate_inital(int w, int num);
	int fitness(double* x_gene);
	int selection(double retian_rate, double random_select_rate,int num,int w);
	void crossover(int sumCount,int num,int w);
	void mutation(double rate, int num,int dai);
	void result(int num);
	void evolve(int dai,int num, int w, double retain_rate , double random_select_rate, double mutation_rate);
};

