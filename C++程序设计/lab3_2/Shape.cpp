#include "Shape.h"
#include <iostream>
using namespace std;
Shape::Shape(ColorType c) {
	color = c;
}
Shape::~Shape() {

}
void Shape::draw() {
	cout << endl;
}