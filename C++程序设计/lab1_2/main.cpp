#include <iostream>
using namespace std;
//2������-���ֵ����
int Maxl(int a,int b){
    if (a > b){
        return a;
    }
    else{
        return b;
    }
}
//3������-���ֵ����
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
//2��˫����-���ֵ����
double Maxl(double a, double b){
    if (a > b){
        return a;
    }
    else{
        return b;
    }
}
//3��˫����-���ֵ����
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
    cout <<a<<" "<<b<<" �еĽϴ�ֵΪ��"<< Maxl(a,b)<<endl;
    a = 1,b = 1;
    cout <<a<<" "<<b<<" �еĽϴ�ֵΪ��"<< Maxl(a,b)<<endl;
    a = 1,b = 2,c = 3;
    cout <<a<<" "<<b<<" "<<c<<" �еĽϴ�ֵΪ��"<< Maxl(a,b,c)<<endl;
    a = 1,b = 1,c = 1;
    cout <<a<<" "<<b<<" "<<c<<" �еĽϴ�ֵΪ��"<< Maxl(a,b,c)<<endl;

    double x,y,z;
    x = 1.5,y = 2.5;
    cout <<x<<" "<<y<<" �еĽϴ�ֵΪ��"<< Maxl(x,y)<<endl;
    x = 1.5,y = 1.5;
    cout <<x<<" "<<y<<" �еĽϴ�ֵΪ��"<< Maxl(x,y)<<endl;
    x = 1.5,y = 2.5,z = 3.5;
    cout <<x<<" "<<y<<" "<<z<<" �еĽϴ�ֵΪ��"<< Maxl(x,y,z)<<endl;
    x = 1.5,y = 1.5,z = 1.5;
    cout <<x<<" "<<y<<" "<<z<<" �еĽϴ�ֵΪ��"<< Maxl(x,y,z)<<endl;
}
