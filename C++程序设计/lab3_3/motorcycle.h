#pragma once
#include "bicycle.h"
#include "motorcar.h"
class motorcycle :
	public bicycle,public motorcar
{
public:
	motorcycle();
	motorcycle(int m, int w, int h,int n);
	
};

