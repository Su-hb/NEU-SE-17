#include "Cline.h"
#include <iostream>

Cline::Cline(ColorType c,CPoint px, CPoint py) :Shape(c),pstart(px), pend(py) {

}

void Cline::draw() {
	std::cout << "this is a line!" << std::endl;
}