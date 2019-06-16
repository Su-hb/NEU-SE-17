#pragma once
#include "Shape.h"
#include "CPoint.h"
class Cline :
	public Shape
{
private:
	CPoint pstart, pend;
public:
	Cline(ColorType c);
	Cline(ColorType c,CPoint px, CPoint py);
	
	void draw();
};

