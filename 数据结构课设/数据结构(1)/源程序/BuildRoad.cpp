#include "stdafx.h"
#include "Struct.h"
extern int edges[MAX_VERTEX_NUM][MAX_VERTEX_NUM];//定义存储原图顶点矩阵的全局数组


int Mininum(ALGraph G, Edge a)
{
	int min, i, j, mark;
	for (i = 0; a[i].lowcost == 0; i++);//找出还没有加入最小生成树的边  
		min = a[i].lowcost;

	mark = i;

	for (j = i + 1; j<G.vexnum; j++)//找出权值最小的   
		if (a[j].lowcost>0 && a[j].lowcost<min)
		{
			min = a[j].lowcost;    
			mark = j;
		}
	return mark;
}

void MiniSpanTree(ALGraph G, string u) 
{
	Edge closedge;
	int j;
	int i = LocateVex(G, u);
	for (int j = 0; j<G.vexnum; j++)//初始化各个边的信息   
		if (j != i)
		{
			closedge[j].vex = u;
			closedge[j].lowcost = edges[i][j];
		}

	closedge[i].lowcost = 0;//初始化u已经在最小生成树中  
	int k;

	std::cout << "道路修建规划图为：" << endl;
	for (i = 1; i<G.vexnum; i++)
	{
		k = Mininum(G, closedge);
		std::cout << "从" << closedge[k].vex << "到" << G.vertices[k].name << "修一条路" << endl;

		closedge[k].lowcost = 0;//将k顶点加入生成树中   
		for (j = 0; j<G.vexnum; j++)
			if (edges[k][j]<closedge[j].lowcost)//当新加入的顶点使距离更小了，就更改他    
			{
				closedge[j].lowcost = edges[k][j];
				closedge[j].vex = G.vertices[k].name;
			}
	}
}

