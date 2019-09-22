#include "stdafx.h"
#include "Struct.h"

int edges[MAX_VERTEX_NUM][MAX_VERTEX_NUM];//定义存储原图顶点矩阵的全局数组

void OutputGraph(ALGraph G)
{
	int i, j;
	ArcNode *p;
	int kk[MAXNUM];

	for (i = 0; i<G.vexnum; i++)
	{
		p = G.vertices[i].firstarc;

		while (p != NULL)
		{
			edges[i][p->adjvex] = p->weight;
			kk[p->adjvex] = 1;
			p = p->nextarc;
		}
		for (j = 0; j<G.vexnum; j++){
			if (kk[j] != 1){
				edges[i][j] = MAXNUM;
			}
		}
		for (j = 0; j<G.vexnum; j++){
			kk[j] = 0;
		}
	}

	for (i = 0; i<G.vexnum; i++)//输出横向名字 
	{
		std::cout << "	" << G.vertices[i].name;
	}
	std::cout << endl;

	for (i = 0; i<G.vexnum; i++)
	{
		std::cout << G.vertices[i].name << "	";
		for (j = 0; j < G.vexnum; j++)
		{
			if (i == j){
				edges[i][j] = 0;
				std::cout << edges[i][j] << "	";
			}
			else if (edges[i][j] == MAXNUM){
				std::cout << MAXNUM << "	";
			}
			else{
				std::cout << edges[i][j] << "	";
			}
		}
		std::cout << endl;
	}
}