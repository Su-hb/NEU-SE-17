#ifndef CLINE_H
#define CLINE_H

#include "CPoint.h"

class Cline
{
private:
	CPoint pt1, pt2;  //pt1��pt2�ֱ������߶ε������յ�
public:
	Cline();
	Cline(int ,int ,int ,int );
	Cline(CPoint&,CPoint& );
	double Distance();  //������߶γ��ȵĳ�Ա����
	void ShowLine();
};

#endif // CLINE_H
