#ifndef CLINE_H
#define CLINE_H

#include "CPoint.h"

class Cline
{
private:
	CPoint pt1, pt2;  //pt1和pt2分别代表该线段的起点和终点
public:
	Cline();
	Cline(int ,int ,int ,int );
	Cline(CPoint&,CPoint& );
	double Distance();  //计算该线段长度的成员函数
	void ShowLine();
};

#endif // CLINE_H
