// bpnn.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>
#include "bpnn.h"
#include <assert.h>

#include<random>

bpnn::bpnn() {
	initialized = false;
}

bpnn::bpnn(int in, int hid, int out) {
	num_in = in;
	num_hid = hid;
	num_out = out;
	learn_rate = 0.1;
	momentum = 0.3;
	init_cap();
	init_weight();
	initialized = true;

}

bool bpnn::init_cap()
{
	// 为向量预分配空间
	vec_in.resize(num_in);
	vec_hid.resize(num_hid);
	vec_out.resize(num_out);
	delta_hid.resize(num_hid);
	delta_out.resize(num_out);
	const_in.resize(num_hid);
	const_hid.resize(num_out);
	in_hid.resize(num_hid);
	hid_out.resize(num_out);
	pre_in_hid.resize(num_hid);
	pre_hid_out.resize(num_out);
	for (auto& t : in_hid)
		t.resize(num_in);
	for (auto& t : pre_in_hid)
		t.resize(num_in);
	for (auto& t : hid_out)
		t.resize(num_hid);
	for (auto& t : pre_hid_out)
		t.resize(num_hid);
	return true;
}

//初始化权重矩阵参数
bool bpnn::init_weight()
{
	std::random_device r;
	auto rd = [&]() {return (double)r() / 2.0 / r.max() - 0.25; };
	for (auto& t : const_in)
		t = rd();
	for (auto& t : const_hid)
		t = rd();
	for (auto& t : in_hid)
		for (auto& x : t)
			x = rd();
	for (auto& t : hid_out)
		for (auto& x : t)
			x = rd();
	return true;
}

inline double bpnn::sigmoid(double x)
{
	return 1.0 / (1 + std::exp(-x));
}

//sigmoid 的导数
inline double bpnn::sigmoid_d(double x)
{
	return x * (1 - x);
}

//释放内存

bpnn::~bpnn() {

	vec_in.clear();
	vec_hid.clear();
	vec_out.clear();
	delta_hid.clear();
	delta_out.clear();
	in_hid.clear();
	hid_out.clear();
	pre_in_hid.clear();
	pre_hid_out.clear();
	const_in.clear();
	const_hid.clear();
	initialized = false;
}

//设置学习速率
bool bpnn::set_learn_rate(double _rate) {
	if (_rate < 0.001 || _rate > 1.0) {
		return false;
	}
	learn_rate = _rate;
	return true;
}

//设置冲量项
bool bpnn::set_momentum(double _mom) {
	if (_mom < 0 || _mom > 1.0) {
		return false;
	}
	momentum = _mom;
	return true;
}
//正向传播
const bpnn::vecd& bpnn::compute(const vecd& _in) {
	if (!initialized) exit(1);
	assert((int)_in.size() >= num_in);//assert宏的原型定义在<assert.h>中，其作用是如果它的条件返回错误，则终止程序执行。
	std::copy_n(_in.begin(), num_in, vec_in.begin());
	std::fill(vec_hid.begin(), vec_hid.end(), 0.0);
	std::fill(vec_out.begin(), vec_out.end(), 0.0);
	for (int i = 0; i < num_hid; i++)
		for (int j = 0; j < num_in; j++)
			vec_hid[i] += in_hid[i][j] * vec_in[j];

	for (int i = 0; i < num_hid; ++i)
		vec_hid[i] = sigmoid(vec_hid[i] + const_in[i]);

	for (int i = 0; i < num_out; ++i)
		for (int j = 0; j < num_hid; ++j)
			vec_out[i] += hid_out[i][j] * vec_hid[j];
	for (int i = 0; i < num_out; ++i)
		vec_out[i] = sigmoid(vec_out[i] + const_hid[i]);


	return vec_out;



}
//反向传播
const double bpnn::backward(const vecd& _out) {
	double error = 0;
	for (int i = 0; i < num_out; i++) {
		delta_out[i] = sigmoid_d(vec_out[i]) * (_out[i] - vec_out[i]);

		error += std::abs(delta_out[i]);
	}
	for (int i = 0; i < num_hid; ++i)
	{
		delta_hid[i] = 0;
		for (int j = 0; j < num_out; ++j)
			delta_hid[i] += hid_out[j][i] * delta_out[j];
		delta_hid[i] *= sigmoid_d(vec_hid[i]);
		error += std::abs(delta_hid[i]);
	}

	// 更新网络权值
	double d_ij;
	for (int i = 0; i < num_out; ++i)
		for (int j = 0; j < num_hid; ++j)
		{
			d_ij = learn_rate * delta_out[i] * vec_hid[j] + momentum * pre_hid_out[i][j];
			hid_out[i][j] += d_ij;
			pre_hid_out[i][j] = d_ij;
		}
	for (int i = 0; i < num_out; ++i)
		const_hid[i] += learn_rate * delta_out[i];

	for (int i = 0; i < num_hid; ++i)
		for (int j = 0; j < num_in; ++j)
		{
			d_ij = learn_rate * delta_hid[i] * vec_in[j] + momentum * pre_in_hid[i][j];
			in_hid[i][j] += d_ij;
			pre_in_hid[i][j] = d_ij;
		}
	for (int i = 0; i < num_hid; ++i)
		const_in[i] += learn_rate * delta_hid[i];

	return error;
	
}
//训练
double bpnn::learn(const vecd& _in, const vecd& out) {
	//正向传播
	compute(_in);
	//反向传播
	return backward(out);
}

//误差值
double bpnn::learn_all(const matd& _in, const matd& _out, int times)
{
	double sumerr = 0;
	//训练times
	for (int i = 0; i < times; ++i)
	{
		sumerr = 0;
		for (int j = 0; j < (int)_in.size(); ++j)
			sumerr += learn(_in[j], _out[j]);
		std::cout << i << ":\t" << sumerr << std::endl;
	}
	return sumerr;
}
//将模型写入文件
void bpnn::save(std::ostream& out)
{
	int magic_number = 0x12345678;
	out.write((char*)& magic_number, sizeof(int));
	out.write((char*)& num_in, sizeof(int));
	out.write((char*)& num_hid, sizeof(int));
	out.write((char*)& num_out, sizeof(int));
	out.write((char*)& learn_rate, sizeof(double));
	out.write((char*)& momentum, sizeof(double));
	auto write_vd = [&](vecd& v)
	{
		for (auto& x : v)
			out.write((char*)& x, sizeof(double));
	};
	auto write_vvd = [&](matd& v)
	{
		for (auto& x : v)
			write_vd(x);
	};
	write_vd(vec_in);
	write_vd(vec_hid);
	write_vd(vec_out);
	write_vd(const_in);
	write_vd(const_hid);
	write_vd(delta_hid);
	write_vd(delta_out);
	write_vvd(in_hid);
	write_vvd(pre_in_hid);
	write_vvd(hid_out);
	write_vvd(pre_hid_out);
	out.flush();
}

//读取文件
bool bpnn::read(std::istream& in)
{
	int magic_number;
	in.read((char*)& magic_number, sizeof(int));
	if (magic_number != 0x12345678) return false;
	in.read((char*)& num_in, sizeof(int));
	in.read((char*)& num_hid, sizeof(int));
	in.read((char*)& num_out, sizeof(int));
	in.read((char*)& learn_rate, sizeof(double));
	in.read((char*)& momentum, sizeof(double));
	assert(num_in > 0);
	assert(num_hid > 0);
	assert(num_out > 0);
	if (!init_cap()) return false;
	auto read_vd = [&](vecd& v)
	{
		for (auto& x : v)
			in.read((char*)& x, sizeof(double));
	};
	auto read_vvd = [&](matd& v)
	{
		for (auto& x : v)
			read_vd(x);
	};
	read_vd(vec_in);
	read_vd(vec_hid);
	read_vd(vec_out);
	read_vd(const_in);
	read_vd(const_hid);
	read_vd(delta_hid);
	read_vd(delta_out);
	read_vvd(in_hid);
	read_vvd(pre_in_hid);
	read_vvd(hid_out);
	read_vvd(pre_hid_out);
	initialized = true;
	return true;
}


// 运行程序: Ctrl + F5 或调试 >“开始执行(不调试)”菜单
// 调试程序: F5 或调试 >“开始调试”菜单

// 入门使用技巧: 
//   1. 使用解决方案资源管理器窗口添加/管理文件
//   2. 使用团队资源管理器窗口连接到源代码管理
//   3. 使用输出窗口查看生成输出和其他消息
//   4. 使用错误列表窗口查看错误
//   5. 转到“项目”>“添加新项”以创建新的代码文件，或转到“项目”>“添加现有项”以将现有代码文件添加到项目
//   6. 将来，若要再次打开此项目，请转到“文件”>“打开”>“项目”并选择 .sln 文件
