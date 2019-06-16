#include "bicycle.h"
#include "vehicle.h"
#include <iostream>

bicycle::bicycle():vehicle()
{

	Height = 0;
}
bicycle::bicycle(int m, int w, int h) :vehicle(m, w), Height(h) {

}
void bicycle::Run() {
	std::cout << "自行车在跑" << std::endl;
}
void bicycle::Stop() {
	std::cout << "自行车停下了" << std::endl;
}