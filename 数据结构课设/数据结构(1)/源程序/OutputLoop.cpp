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

	string *a = new string[G1.vexnum];//将不在回路中的顶点暂存在字符数组a中  
	int *indegree = new int[G1.vexnum];//为顶点入度数组开辟空间  
	int top = 0, base = 0;
	int *S = new int[G1.vexnum];//为顶点栈开辟空间  
	int i;
	for (i=0; i<G1.vexnum; i++)//初始化顶点入读数组
		indegree[i] = 0;

	FindInDegree(G1, indegree);//调用求入度函数求出各个顶点的入度  

	for (i = 0; i < G1.vexnum; i++){
		if (!indegree[i])//将入度减为0的顶点入栈    
			S[top++] = i;
	}
		
	int count = 0;//记录已经进入拓扑序列的顶点的个数  
	int k;
	ArcNode *p = new ArcNode;
	while (top != base)//当栈不空是继续循环  
	{
		k = S[--top];
		a[count++] = G1.vertices[k].name;
		p = G1.vertices[k].firstarc;
		while (p != NULL){
			if (!(--indegree[p->adjvex])){//如果入度减为0，则入栈     
				S[top++] = p->adjvex;
			}
			p = p->nextarc;
		}	
	}
	if (count<G1.vexnum)
	{
		cout << endl << "图中有回路,回路为：" << endl;
		for (i = 0; i<G1.vexnum; i++)
		{
			for (k = 0; k < count; k++){
				if (G1.vertices[i].name == a[k])//如果顶点与数组a中的顶点相同就跳出循环，	
					//说明不在循环中      
					break;
			}
				if (k >= count)
					cout << G1.vertices[i].name;
		}

	}
	else
	{
		cout << "图中没有回路" << endl;

	}

	delete[]a;
	delete[]indegree;
	delete[]S;
}
