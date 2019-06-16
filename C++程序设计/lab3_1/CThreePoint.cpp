#include "CThreePoint.h"
#include <iostream>
CThreePoint::CThreePoint() {

}
CThreePoint::CThreePoint(int x, int y, int z) :CPoint(x, y), z(z) {

}
int CThreePoint::GetZ() {
	return z;
}
void CThreePoint::SetZ(int pz) {
	z = pz;
}
void CThreePoint::ShowPoint() {
	cout << "x:" << GetX() << " y:" << GetY() << " z:" << z << endl;
}


