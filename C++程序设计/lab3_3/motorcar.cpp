#include "motorcar.h"
#include <iostream>
using namespace std;
motorcar::motorcar() {
	SeatNum = 0;
}
motorcar::motorcar(int m, int w, int n) :vehicle(m, w), SeatNum(n) {

}
void motorcar::Run() {
	cout << "��������" << endl;
}
void motorcar::Stop() {
	cout << "����ͣ��" << endl;
}