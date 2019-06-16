#pragma once
#include "CPoint.h"
class CThreePoint :
	public CPoint
{
private:
	int z;
public:
	CThreePoint();
	CThreePoint(int, int, int);
	int GetZ();
	void SetZ(int pz);
	virtual void ShowPoint();
};

