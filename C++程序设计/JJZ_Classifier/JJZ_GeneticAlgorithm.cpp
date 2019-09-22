#include "JJZ_GeneticAlgorithm.h"
#include <iostream>
#include <vector>
#include <time.h>
using namespace std;
JJZ_GeneticAlgorithm::JJZ_GeneticAlgorithm(int rows, int cols) :JJZ_Classifier(rows, cols){
	srand((unsigned)time(NULL));
}

double JJZ_GeneticAlgorithm::getRandData(int min, int max)
{	

	double m1 = (double)(rand() % 101) / 101;// 计算 0，1之间的随机小数,得到的值域近似为(0,1)
	min++;//将 区间变为(min+1,max),

	double m2 = (double)((rand() % (max - min + 1)) + min);//计算 min+1,max 之间的随机整数，得到的值域为[min+1,max]
	m2 = m2 - 1;//令值域为[min,max-1]

	int flag = rand();
	if (flag % 2 == 0 )
	{	
		return m1 + m2;//返回值域为(min,max),为所求随机浮点数
	}
	return m1 - m2;

	
}

void JJZ_GeneticAlgorithm::generate_inital(int w, int num) {
	//population = new double* [num];
	for (int i = 0; i < num; i++) {
	
		//population[i] = new double[w];
		for (int j = 0; j < w; j++) {
			population[i][j] = getRandData(1, 100);
		}
	}
	
}

double JJZ_GeneticAlgorithm::Sigmoid(double ans)
{
	return 1 / (1 + exp(-ans));
}

int JJZ_GeneticAlgorithm::fitness(double* x_gene) {
	int count = 0;
	for (int i = 0; i < JJZ_getM(); i++) {
		double ans = 0;
		for (int j = 0; j < JJZ_getN(); j++)
		{
			ans += x_gene[j] * JJZ_getdata()[i*JJZ_getN()+i+j];
		}
		if (Sigmoid(ans) <= 0.6 && JJZ_getdata()[i * (JJZ_getN() + 1) + JJZ_getN()] == 0)
			count++;
		if (Sigmoid(ans) > 0.4 && JJZ_getdata()[i * (JJZ_getN() + 1) + JJZ_getN()] == 1)
			count++;
	}
	return count;
}

int JJZ_GeneticAlgorithm::selection(double retian_rate, double random_select_rate,int num, int w ) {
	//插入排序
	int a[201];
	for (int i = 0; i < num; i++)
	{	
		a[i] = fitness(population[i]);
	}
	for (int i = 0; i < num; i++)
	{
		for (int j = i - 1; j >= 0 && a[j + 1] > a[j]; j--)
		{
			swap(a[j], a[j + 1]);
			swap(population[j], population[j + 1]);
		}
	}

	//定义二维数组	
	int retain_length = (int) num* retian_rate;

	for (int i = 0; i < retain_length; i++) {
		for (size_t j = 0; j <w;j++)
		{
			parents[i][j] = population[i][j];
		}
	}
	int sumCount = retain_length;
	for (int i = retain_length; i < num ;i++) {
		double randomNum = rand() % 101;
		if (randomNum <=  100 *random_select_rate) {
			
			for (size_t j = 0; j < w; j++)
			{
				parents[sumCount][j] = population[i][j];
			}	
			sumCount++;
		}	
	}

	return sumCount;
}

void JJZ_GeneticAlgorithm::crossover(int sumCount,int num,int w) {
	// 新出生的孩子，最终会被加入存活下来的父母之中，形成新一代的种群。
	

	while (sumCount < num) {

		int male = rand()% 200;
		int female = rand() % 200;
		if (male != female){
			
			int cross_pos = rand() % w;
		
			for (int i = 0; i < w; i++) {
				if (i < cross_pos) {
					parents[sumCount][i] = parents[male][i];
				}
				else {
					parents[sumCount][i] = parents[female][i];
				}
			}

			sumCount++;
		}
	}
	for (int i = 0; i < num; i++) {
		for (int j = 0; j < w; j++) {
		
			population[i][j] = parents[i][j];
		}
	}
}

void JJZ_GeneticAlgorithm::mutation(double rate,int num,int dai) {
	int tmp = rand() % 100;
	for (int i = 0; i < num; i++) {
		_sleep(10);
		tmp = rand() % 100;
		cout << "变异随机率" << tmp << endl;
		if (tmp <= rate * 100) {

			int j = rand() % 7;
			double num = getRandData(1, 100) / dai;
			cout << "变异随机数为：" << num << endl;
			population[i][j] += num;

		}
	}
}

void JJZ_GeneticAlgorithm::result(int num) {
	//插入排序
	int Max = 0;
	int index = 0;

	for (int i = 0; i < num; i++)
	{	
		if (Max < fitness(population[i])) {
			Max = fitness(population[i]);
			index = i;
		}
	}

	cout << "准确率：" << (double)Max / (double)JJZ_getM() << endl;
}

void JJZ_GeneticAlgorithm::evolve(int dai,int num, int w,double retian_rate = 0.2, double random_select_rate = 0.5, double mutation_rate = 0.02) {
	int sumCount = selection(retian_rate, random_select_rate, num, w);
	crossover(sumCount, num, w);
	mutation(mutation_rate, num,dai);
	result(num);
}