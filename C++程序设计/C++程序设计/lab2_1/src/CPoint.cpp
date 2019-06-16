#include "../include/CPoint.h"
#include <iostream>
using namespace std;
CPoint::CPoint(){
    nCount++;
}

CPoint::CPoint(int px=0,int py = 0)
{
    x = px;
    y = py;
    nCount++;
}
CPoint::CPoint(CPoint& c)
{
    x = c.x;
    y = c.y;
    nCount++;
}
CPoint::~CPoint()
{
    cout<< "��"<<nCount<<"���㱻�ͷ�"<<endl;
    nCount--;

}
int CPoint::GetX(){
    return x;
}
int CPoint::GetY(){
    return y;
}
void CPoint::SetX(int px){
    x = px;
}
void CPoint::SetY(int py){
    y = py;
}
void CPoint::ShowPoint(){
    cout<<"���x����Ϊ��"<<x<<" y����Ϊ��"<<y<<endl;
}
CPoint CPoint::operator+(CPoint& pt){
    CPoint tmp(0,0) ;
    tmp.x = this->x + pt.x;
    tmp.y = this->y + pt.y;
    return tmp;
}
CPoint operator- (CPoint pt1, CPoint pt2){
    CPoint tmp(pt1.x - pt2.x,pt1.y + pt2.y) ;

    return tmp;
}
istream& operator>>(istream& in,CPoint& cp){
    in>>cp.x>>cp.y;
    return in;
}
ostream& operator<<(ostream& out,CPoint cp){
    out<<"<"<<cp.x<<","<<cp.y<<">";
    return out;
}
