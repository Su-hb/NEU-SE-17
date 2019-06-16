#include <iostream>
using namespace std;
//2个整数-最大值函数
int Maxl(int a,int b){
    if (a > b){
        return a;
    }
    else{
        return b;
    }
}
//3个整数-最大值函数
int Maxl(int a,int b,int c){
    if(a > b && a > c){
        return a;
    }
    else{
        if( b > a && b > c){
            return b;
        }
        else{
            return c;
        }
    }
}
//2个双精度-最大值函数
double Maxl(double a, double b){
    if (a > b){
        return a;
    }
    else{
        return b;
    }
}
//3个双精度-最大值函数
double Maxl(double a, double b, double c){
    if(a > b && a > c){
        return a;
    }
    else{
        if( b > a && b > c){
            return b;
        }
        else{
            return c;
        }
    }
}

int main(){
    int a,b,c;
    a = 1,b = 2;
    cout <<a<<" "<<b<<" 中的较大值为："<< Maxl(a,b)<<endl;
    a = 1,b = 1;
    cout <<a<<" "<<b<<" 中的较大值为："<< Maxl(a,b)<<endl;
    a = 1,b = 2,c = 3;
    cout <<a<<" "<<b<<" "<<c<<" 中的较大值为："<< Maxl(a,b,c)<<endl;
    a = 1,b = 1,c = 1;
    cout <<a<<" "<<b<<" "<<c<<" 中的较大值为："<< Maxl(a,b,c)<<endl;

    double x,y,z;
    x = 1.5,y = 2.5;
    cout <<x<<" "<<y<<" 中的较大值为："<< Maxl(x,y)<<endl;
    x = 1.5,y = 1.5;
    cout <<x<<" "<<y<<" 中的较大值为："<< Maxl(x,y)<<endl;
    x = 1.5,y = 2.5,z = 3.5;
    cout <<x<<" "<<y<<" "<<z<<" 中的较大值为："<< Maxl(x,y,z)<<endl;
    x = 1.5,y = 1.5,z = 1.5;
    cout <<x<<" "<<y<<" "<<z<<" 中的较大值为："<< Maxl(x,y,z)<<endl;
}
