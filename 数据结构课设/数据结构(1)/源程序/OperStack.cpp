#include "stdafx.h"
#include "Struct.h"

//��ջ�Ļ���������
void initstack(stackhead &L)   //����һ����ջ
{
	L.base = (zanInode*)malloc(size*sizeof(zanlind));
	if (!L.base) exit(0);
	L.top = L.base;
	L.stacksize_curren = 0;
}
void push(stackhead &L, zanInode e) //��Ԫ��eѹ��sջ
{
	*L.top++ = e;
	L.stacksize_curren++;
}
void pop(stackhead &L, zanInode &e)   //��Ԫ��e����sջ
{
	if (L.top == L.base)
	{
		cout << "ͣ�������޳���!!";
		return;
	}
	e = *--L.top;
	L.stacksize_curren--;
}
