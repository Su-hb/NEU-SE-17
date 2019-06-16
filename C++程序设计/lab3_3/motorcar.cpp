#include "motorcar.h"
#include <iostream>
using namespace std;
motorcar::motorcar() {
	SeatNum = 0;
}
motorcar::motorcar(int m, int w, int n) :vehicle(m, w), SeatNum(n) {

}
void motorcar::Run() {
	cout << "汽车在跑" << endl;
}
void motorcar::Stop() {
	cout << "汽车停下" << endl;
}