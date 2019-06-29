#include <iostream.h>
class Point
{
protected:
	int x, y;
public:
	Point(int px=0, int py=0)
	{
		x = px;
		y = py;
	}
	int GetX(){return x;}
	int GetY(){return y;}
	void SetX(int px){x = px;}
	void SetY(int py){y = py;}
	void ShowPoint()
	{
		cout<<"The X coordinate is: "<<x<<endl
			   <<"The Y coordinate is: "<<y<<endl;
	}
};

class SpacePoint: public Point
{
protected:
	int z;
public:
	SpacePoint(int px=0, int py=0, int pz=0):Point(px,py)
	{
		z = pz;
	}
	int GetZ(){return z;}
	void SetZ(int pz){z = pz;}
	void ShowPoint()
	{
		Point::ShowPoint();
		cout<<"The Z coordinate is: "<<z<<endl;
	}
};

void main()
{

}