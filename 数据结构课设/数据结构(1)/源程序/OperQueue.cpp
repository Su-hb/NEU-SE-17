#include "stdafx.h"
#include "Struct.h"


//队列的基本操作；
void initqueue(linkqueue &q) //构造一个空队列 
{
	q.front = q.rear = (queueptr)malloc(sizeof(duilie));
	if (!q.front || !q.rear){
		exit(0);
	}
		
	q.front->next = NULL;
	q.length = 0;
}
void enqueue(linkqueue &q, int number, int ar_time) //把元素的插入队列（属性为number，ar_time）
{
	queueptr p;
	p = (queueptr)malloc(sizeof(duilie));
	if (!p) {
		exit(0);
	}
	//删除队尾元素
	p->number = number;
	p->ar_time = ar_time;
	p->next = NULL;
	q.rear->next = p;
	q.rear = p;
	q.length++;
}
void popqueue(linkqueue &q, queueptr &w) //把元素的插入队列（属性为number，ar_time）
{
	queueptr p;
	if (q.front == q.rear)//如果q为空队列 
	{
		cout << "停车场的通道为空 ! !" << endl;
		return;
	}
	//删除队头元素
	p = q.front->next;
	//w=p;
	q.front->next = p->next;
	q.length--;
	if (q.rear == p)//当队列只有一个节点是  删除一个节点后 队列变为空
	{
		q.front = q.rear;
	}
	free(p);

}
