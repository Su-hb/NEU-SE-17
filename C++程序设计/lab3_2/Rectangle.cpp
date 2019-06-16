#include "Rectangle.h"
#include <iostream>
using namespace std;
Rectangle::Rectangle(ColorType c, CPoint px, CPoint py, CPoint pz, CPoint pa) :Shape(c), pzs(px), pys(py), pzx(pz), pyx(pa) {

}
void Rectangle::draw() {
	cout << "this is a Rectangle!" << endl;
}