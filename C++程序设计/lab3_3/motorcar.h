#pragma once
#include "vehicle.h"
class motorcar :
	public vehicle
{
private:
	int SeatNum;
public:
	motorcar();
	motorcar(int m, int w, int n);
	void Run();
	void Stop();

};

