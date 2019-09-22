#include "stdafx.h"
#include "Struct.h"
extern int edges[MAX_VERTEX_NUM][MAX_VERTEX_NUM];//定义存储原图顶点矩阵的全局数组
bool if_vex_in(ALGraph a, string e)//判断结点是否在图中
{

	for (int i = 0; i<a.vexnum; i++){
		if (a.vertices[i].name == e){
			return true;
		}
	}
	return false;
}

int LocateVex(ALGraph a, string e)//返回结点在邻接表中的序号
{
	for (int i = 0; i<a.vexnum; i++){
		if (a.vertices[i].name == e){
			return i;
		}
	}
	return -1;
}


void checked(ALGraph G)//检查是否有图已创建
{
	if (G.vexnum == 0)
	{
		
		std::cout << "\n*缺少合法的景区景点分布图！\n*请先创建一个合法的景区景点分布图！\n";
		getchar();

	}
}
void checked1(ALGraph G1)//检查是否有导游路线图已创建
{
	if (G1.vexnum == 0)
	{

		std::cout << "\n*缺少合法的导游路线图！\n*请先创建一个合法的导游路线图！\n";
		

	}
}

bool IsEdge(ALGraph G, string e1, string e2){
	ArcNode *p;
	int j;
	int kk[MAX_VERTEX_NUM];
	int m = LocateVex(G, e1);
	int n = LocateVex(G, e2);

	p = G.vertices[m].firstarc;

	while (p != NULL)
	{

		kk[p->adjvex] = p->adjvex;
		p = p->nextarc;
	}

	for (j = 0; j<G.vexnum; j++){
		if (kk[j] == n){
			return true;
			break;
		}
		else{
			if (j == G.vexnum - 1){
				return false;
			}
			continue;

		}
	}

	for (j = 0; j<G.vexnum; j++){
		kk[j] = 0;
	}
	return false;
}
