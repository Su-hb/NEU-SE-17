// lab3_1.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>
#include "CPoint.h"
#include "CThreePoint.h"
int CPoint::nCount = 0;
using namespace std;
int main()
{
	CPoint c2p;
	cout << "二维点：" << endl;
	c2p.ShowPoint();
	cout << "三维点：" << endl;
	CThreePoint c3p(1,2,3);
	c3p.ShowPoint();
	system("pause");
	return 0;
}

