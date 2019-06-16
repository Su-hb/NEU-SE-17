#include "vehicle.h"
vehicle::vehicle() {
	Weight = 0;
	MaxSpeed = 0;
}
vehicle::vehicle(int w, int m) :Weight(w), MaxSpeed(m) {

}

void vehicle::Run(){}

void vehicle::Stop() {}
