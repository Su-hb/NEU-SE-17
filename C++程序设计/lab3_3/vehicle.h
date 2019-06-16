#pragma once
class vehicle
{
private:
	int Weight,MaxSpeed;
public:
	vehicle();
	vehicle(int w , int m );
	virtual	void Run();
	virtual void Stop();
};

