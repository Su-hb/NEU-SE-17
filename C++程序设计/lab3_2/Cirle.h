#pragma once
#include "Shape.h"
#include "CPoint.h"
class Circle :
	public Shape
{
private:
	CPoint mid;
public:
	Circle(ColorType c);
	Circle(ColorType c, CPoint px);

	void draw();
};

