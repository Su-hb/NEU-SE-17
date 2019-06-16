#ifndef CPOINT_H
#define CPOINT_H
#include <iostream>
using namespace std;

class CPoint
{
private:
	int x,  y;
	static int nCount;   // nCount????????????
public:
    CPoint();
	CPoint(int , int );
	CPoint(CPoint&);
	~CPoint();
	int GetX();
	int GetY();
	void SetX(int);
	void SetY(int);
	void ShowPoint();
	CPoint operator+(CPoint& pt);
	friend CPoint operator- (CPoint pt1, CPoint pt2);
	friend istream & operator>>(istream& in,CPoint& cp);
	friend ostream & operator<<(ostream& in,CPoint cp);

};


#endif // CPOINT_H
