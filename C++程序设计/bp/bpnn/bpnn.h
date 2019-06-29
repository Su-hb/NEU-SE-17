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
	int num_in;//�����ά��
	int num_hid;//���ز�
	int num_out;//�����
	vecd vec_in; // ���������
	vecd vec_hid; // �м������
	vecd vec_out; // ���������
	vecd delta_out; // ��������
	vecd delta_hid; // �м�����
	vecd const_in;
	vecd const_hid;
	matd in_hid;
	matd pre_in_hid;
	matd hid_out;
	matd pre_hid_out;
	double learn_rate; // ѧϰ��
	double momentum;
	bool   initialized;
	bool   init_cap();
	bool   init_weight();
	double sigmoid(double);
	double sigmoid_d(double); // sigmoid��������

public:
	// ���캯��������Ϊ������ά��
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
	bool clean(); // �ͷ��ڴ�ռ�
	~bpnn();
};


#endif // BPNN_H_


