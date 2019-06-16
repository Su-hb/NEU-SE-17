#pragma once
enum ColorType
{
	White, Black, Red, Green, Blue, Yellow, Magenta, Cyan
};
class Shape
{
protected:
	ColorType color;
public:
	~Shape();
	Shape(ColorType c);
	virtual void draw(void);
	
};

