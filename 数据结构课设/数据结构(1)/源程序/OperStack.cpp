#include "stdafx.h"
#include "Struct.h"

//堆栈的基本操作；
void initstack(stackhead &L)   //构造一个空栈
{
	L.base = (zanInode*)malloc(size*sizeof(zanlind));
	if (!L.base) exit(0);
	L.top = L.base;
	L.stacksize_curren = 0;
}
void push(stackhead &L, zanInode e) //把元素e压入s栈
{
	*L.top++ = e;
	L.stacksize_curren++;
}
void pop(stackhead &L, zanInode &e)   //把元素e弹出s栈
{
	if (L.top == L.base)
	{
		cout << "停车场内无车辆!!";
		return;
	}
	e = *--L.top;
	L.stacksize_curren--;
}
