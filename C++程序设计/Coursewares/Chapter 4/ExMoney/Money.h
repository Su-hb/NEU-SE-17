#include <iostream.h>
class Money
{
public:
	Money();
	Money(long cents);
	Money(long dollars, int cents);

	double get_value();
	void input(istream& ins);
	void output(ostream& outs);

	friend Money add(Money amount1, Money amount2);
	friend bool equal(Money amount1, Money amount2);

private:
	long all_cents;
};

