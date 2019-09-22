#include "stdafx.h"
#include "Struct.h"


//���еĻ���������
void initqueue(linkqueue &q) //����һ���ն��� 
{
	q.front = q.rear = (queueptr)malloc(sizeof(duilie));
	if (!q.front || !q.rear){
		exit(0);
	}
		
	q.front->next = NULL;
	q.length = 0;
}
void enqueue(linkqueue &q, int number, int ar_time) //��Ԫ�صĲ�����У�����Ϊnumber��ar_time��
{
	queueptr p;
	p = (queueptr)malloc(sizeof(duilie));
	if (!p) {
		exit(0);
	}
	//ɾ����βԪ��
	p->number = number;
	p->ar_time = ar_time;
	p->next = NULL;
	q.rear->next = p;
	q.rear = p;
	q.length++;
}
void popqueue(linkqueue &q, queueptr &w) //��Ԫ�صĲ�����У�����Ϊnumber��ar_time��
{
	queueptr p;
	if (q.front == q.rear)//���qΪ�ն��� 
	{
		cout << "ͣ������ͨ��Ϊ�� ! !" << endl;
		return;
	}
	//ɾ����ͷԪ��
	p = q.front->next;
	//w=p;
	q.front->next = p->next;
	q.length--;
	if (q.rear == p)//������ֻ��һ���ڵ���  ɾ��һ���ڵ�� ���б�Ϊ��
	{
		q.front = q.rear;
	}
	free(p);

}
