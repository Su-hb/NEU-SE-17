#include <iostream>
#include <string>

using namespace std;

template <typename T>
inline  const T & Min(const T & a, const T & b)
{
	return a < b ? a : b;
}

inline const char* Min(const char* a, const char* b) {
	if (strcmp(a, b) < 0) {
		return a;
	}
	return b;
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
	cout << "Min(s2, s1): " << Min(s2, s1) << endl;


	const char* c1 = "Zzz";
	const char* c2 = "Horld";
	cout << "Min(c2, c1): " << Min(c1, c2) << endl;
	return 0;
}