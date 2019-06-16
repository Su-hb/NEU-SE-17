#pragma once
#include "vehicle.h"
class bicycle :
	public vehicle
{
private:
	int Height;
public:
	bicycle();
	bicycle(int w, int m, int h);

	void Run();

	void Stop();
	
};

