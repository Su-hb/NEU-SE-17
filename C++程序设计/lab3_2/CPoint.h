#pragma once
class CPoint
{
private:
	int x, y;
public:
	CPoint();
	CPoint(int x , int y );
	int GetX();
	int GetY();
	void SetX(int py);
	void SetY(int px);
	void ShowPoint();
};

