#include <iostream>
#include<algorithm>

using namespace std;

int main()
{
    int t[] = {5,10,2,9,15,9};
    int l[] = {6,1,20,3,8};

    int numOfWork = sizeof(t)/sizeof(t[0]);
    int numOfMachine = sizeof(l)/sizeof(l[0]);

    sort(t,t+numOfWork);//对工作进行排序
    sort(l,l+numOfMachine);//对机器排序
    int tmp = numOfMachine-1;
    for(int i = numOfWork-1;i > 0;i--){
        if( l[tmp] > t[i]){
            tmp --;
        }
    }
    cout << numOfMachine - tmp << endl;
    return 0;
}
