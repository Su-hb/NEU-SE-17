#pragma once

#ifndef BPNN_H_
#define BPNN_H_

#include <iostream>
#include <vector>
using namespace std;
class bpnn
{
	typedef vector<double> vecd;
	typedef vector<vecd> matd;
private:
	int num_in;//输入层维数
	int num_hid;//隐藏层
	int num_out;//输出层
	vecd vec_in; // 输入层向量
	vecd vec_hid; // 中间层向量
	vecd vec_out; // 输出层向量
	vecd delta_out; // 输出层误差
	vecd delta_hid; // 中间层误差
	vecd const_in;
	vecd const_hid;
	matd in_hid;
	matd pre_in_hid;
	matd hid_out;
	matd pre_hid_out;
	double learn_rate; // 学习率
	double momentum;
	bool   initialized;
	bool   init_cap();
	bool   init_weight();
	double sigmoid(double);
	double sigmoid_d(double); // sigmoid函数导数

public:
	// 构造函数，参数为各向量维度
	bpnn();
	bpnn(int, int, int);
	bool set_learn_rate(double);
	bool set_momentum(double);
	double learn(const vecd&, const vecd&);
	double learn_all(const matd&, const matd&, int times = 100);
	const vecd& compute(const vecd&);
	const double backward(const vecd&);
	void save(std::ostream&);
	bool read(std::istream&);
	bool clean(); // 释放内存空间
	~bpnn();
};


#endif // BPNN_H_


