#include <iostream>

#include "include/Cline.h"
#include "include/CPoint.h"
using namespace std;
int CPoint::nCount = 0;

int main()
{
    //lab2_1
    CPoint cp;
    cp.ShowPoint();
    cout<<cp;
    cin>>cp;
    CPoint res,ress;
    res = cp+cp;
    ress = cp -cp;

    res.ShowPoint();
    ress.ShowPoint();
	system("pause");
    return 0;
    /*
    CPoint c1 (1,1);
    c1.ShowPoint();
    CPoint c2 = c1;
    c2.ShowPoint();
    cout<<&c1<<endl;
    cout<<&c2<<endl;
    Cline a(c1,c2);
    return 0;*/
}
