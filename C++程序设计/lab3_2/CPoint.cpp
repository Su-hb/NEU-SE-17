#include "CPoint.h"
#include <iostream>
using namespace std;
CPoint::CPoint() {
	x = 0;
	y = 0;
}
CPoint::CPoint(int x , int y ) :x(x), y(y) {

}
void CPoint::ShowPoint() {
	cout << "x:" << x << ",y:" << y << endl;
}
int CPoint::GetX() {
	return x;
}
int CPoint::GetY() {
	return y;
}
void CPoint::SetX(int px) {
	x = px;
}
void CPoint::SetY(int py){
	y = py;
}