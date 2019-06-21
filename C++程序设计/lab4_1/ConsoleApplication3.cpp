#include <iostream>
#include <string>

using namespace std;

template <typename T>
inline T const& Min(T const& a, T const& b)
{
	return a < b ? a : b;
}
int main()
{

	int i = 39;
	int j = 20;
	cout << "Min(i, j): " << Min(i, j) << endl;

	double f1 = 13.5;
	double f2 = 20.7;
	cout << "Min(f1, f2): " << Min(f1, f2) << endl;

	string s1 = "Hello";
	string s2 = "World";
	cout << "Min(s2, s1): " << Min(s1, s2) << endl;


	const char* c1 = "Hello";
	const char* c2 = "World";
	cout << "Min(c2, c1): " << Min(c1, c2) << endl;
	return 0;
}