#include "Money.h"

Money add(Money amount1, Money amount2)
{
	return amount1.all_cents + amount2.all_cents;
}
bool equal(Money amount1, Money amount2)
{
	return amount1.all_cents == amount2.all_cents;
}
void main()
{
	Money your_account, my_account(500,9);
	cout<<"Enter an amount of your money format as (-)$00.00 : ";
	your_account.input(cin);
	cout<<"your amount is ";
	your_account.output(cout);
	cout<<endl;
	cout<<"My amount is ";
	my_account.output(cout);
	cout<<endl;
	if(equal(your_account, my_account))
		cout<<"We have the same amount.\n";
	else
		cout<<"One of us is richer.\n";

	Money our_account = add(your_account, my_account);
	your_account.output(cout);
	cout<<" + ";
	my_account.output(cout);
	cout<<" equals: ";
	our_account.output(cout);
	cout<<endl;
	return;
}