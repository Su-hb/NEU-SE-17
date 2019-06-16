#pragma once
#include "Shape.h"
#include "CPoint.h"
class Rectangle :
	public Shape
{
private:
	CPoint pzs, pys,pzx,pyx;
public:
	Rectangle(ColorType c);
	Rectangle(ColorType c, CPoint px, CPoint py,CPoint pz,CPoint pa);

	void draw();
};

