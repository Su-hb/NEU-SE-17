#include "motorcycle.h"
#include <iostream>
using namespace std;
motorcycle::motorcycle() {

}
motorcycle::motorcycle(int m, int w, int h, int n) :bicycle(m, w, h), motorcar(m, w, n) {

}
