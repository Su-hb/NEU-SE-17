#include <iostream>

using namespace std;
int main() {
	shared_ptr<int> p1(new int(33));
	long a = p1.use_count();
	cout << a;
	return 0;
}