#include <iostream>

using namespace std;
int main() {
	shared_ptr<int> p1(new int());
	cout<< p1.use_count();
	return 0;
}
