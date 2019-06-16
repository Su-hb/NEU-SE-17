#include "Cirle.h"
#include <iostream>
Circle::Circle(ColorType c, CPoint m) :Shape(c), mid(m) {

}
void Circle::draw() {
	std::cout << "this is a circle" << std::endl;
}