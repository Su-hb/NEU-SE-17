#include "stdafx.h"
#include "Struct.h"


void FindInDegree(ALGraph G1, int indegree[]){
	int i;
	ArcNode *p;
	indegree[0] = 0;
	for (i = 0; i < G1.vexnum; i++){
		p = G1.vertices[i].firstarc;
		while (p != NULL){
			indegree[p->adjvex]++;
			p = p->nextarc;
		}
	}
}

void TopoSort(ALGraph G1)
{

	string *a = new string[G1.vexnum];//�����ڻ�·�еĶ����ݴ����ַ�����a��  
	int *indegree = new int[G1.vexnum];//Ϊ����������鿪�ٿռ�  
	int top = 0, base = 0;
	int *S = new int[G1.vexnum];//Ϊ����ջ���ٿռ�  
	int i;
	for (i=0; i<G1.vexnum; i++)//��ʼ�������������
		indegree[i] = 0;

	FindInDegree(G1, indegree);//��������Ⱥ������������������  

	for (i = 0; i < G1.vexnum; i++){
		if (!indegree[i])//����ȼ�Ϊ0�Ķ�����ջ    
			S[top++] = i;
	}
		
	int count = 0;//��¼�Ѿ������������еĶ���ĸ���  
	int k;
	ArcNode *p = new ArcNode;
	while (top != base)//��ջ�����Ǽ���ѭ��  
	{
		k = S[--top];
		a[count++] = G1.vertices[k].name;
		p = G1.vertices[k].firstarc;
		while (p != NULL){
			if (!(--indegree[p->adjvex])){//�����ȼ�Ϊ0������ջ     
				S[top++] = p->adjvex;
			}
			p = p->nextarc;
		}	
	}
	if (count<G1.vexnum)
	{
		cout << endl << "ͼ���л�·,��·Ϊ��" << endl;
		for (i = 0; i<G1.vexnum; i++)
		{
			for (k = 0; k < count; k++){
				if (G1.vertices[i].name == a[k])//�������������a�еĶ�����ͬ������ѭ����	
					//˵������ѭ����      
					break;
			}
				if (k >= count)
					cout << G1.vertices[i].name;
		}

	}
	else
	{
		cout << "ͼ��û�л�·" << endl;

	}

	delete[]a;
	delete[]indegree;
	delete[]S;
}
