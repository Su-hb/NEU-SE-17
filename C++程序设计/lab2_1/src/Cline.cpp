#include "../include/Cline.h"
#include "../include/CPoint.h"
#include <iostream>
#include <cmath>

using namespace std;
Cline::Cline()
{
}
Cline::Cline(int x1 , int y1,int x2,int y2)
: pt1(x1,y1), pt2(x2,y2)
{

}
Cline::Cline(CPoint& ptstart,CPoint& ptend){
    pt1 = ptstart;
    pt2 = ptend;
}

double Cline::Distance(){
    return sqrt(pow((pt1.GetX() - pt2.GetX()),2)+pow((pt1.GetY() - pt2.GetY()),2));
}
void Cline::ShowLine(){
    cout<<"起始点：<"<<pt1.GetX()<<","<<pt1.GetY()<<">"<<endl;
    cout<<"终止点：<"<<pt2.GetX()<<","<<pt2.GetY()<<">"<<endl;
    cout<<"线段长度："<<Distance()<<endl;
}



