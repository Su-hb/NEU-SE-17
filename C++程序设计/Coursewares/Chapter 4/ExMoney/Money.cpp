#include "Money.h" 

Money::Money():all_cents(0){}
Money::Money(long cents): all_cents(cents){}
Money::Money(long dollars, int cents)
{
	all_cents = dollars*100 + cents;
}
double Money::get_value()
{
	return all_cents*0.01;
}
void Money::input(istream & ins)
{
	char one_char, decimal_point, digit1, digit2;
	long dollars;
	int cents;
	bool negative=true;

	ins>>one_char;
	if(one_char=='-')
			ins>>one_char;
	else 
			negative=false;
	ins>>dollars>>decimal_point>>digit1>>digit2;
	cents = (digit1-'0')*10 + (digit2-'0');
	all_cents=dollars*100+cents;
	if(negative)
		all_cents=-all_cents;
}

void Money::output(ostream& outs)
{
	long positive_cents, dollars, cents;
	if(all_cents<0)
		positive_cents = -all_cents;
	else
		positive_cents=all_cents;
	dollars = positive_cents/100;
	cents = positive_cents%100;

	if(all_cents<0)
		outs<<"-$"<<dollars<<'.';
	else
		outs<<"$"<<dollars<<'.';

	if(cents<10)
		outs<<'0';
	outs<<cents;
}

