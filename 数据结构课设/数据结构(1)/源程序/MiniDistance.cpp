#include "stdafx.h"
#include "Struct.h"

extern int edges[MAX_VERTEX_NUM][MAX_VERTEX_NUM];//定义存储原图顶点矩阵的全局数组



void ShortestPath(ALGraph G, int path[][MAX_VERTEX_NUM], int D[][MAX_VERTEX_NUM])//求最短路径 
{
	int u, v, w;
	for (v = 0; v<G.vexnum; v++)
		for (w = 0; w<G.vexnum; w++)
		{
			D[v][w] = edges[v][w];//对最短距离初始化为任意两点之间的权值    
			if (edges[v][w]<MAXNUM)
				path[v][w] = v;//对最短路径初始化为自身的前一个结点的序号   
		}   
	for (u = 0; u < G.vexnum; u++)
	{
		for (v = 0; v < G.vexnum; v++)
		{
			for (w = 0; w < G.vexnum; w++)
			{
				if (D[v][u] + D[u][w]<D[v][w])//如果新加入的结点导致最短路径变短了，就更改他，同时记录增加的路径的编号 
				{
					D[v][w] = D[v][u] + D[u][w];
					path[v][w] = u;
				}
			}
		}
	}
	
	
}

void OutPutShortestPath(ALGraph G, int path[][MAX_VERTEX_NUM], int D[][MAX_VERTEX_NUM], int i, int j)
{
	if (path[i][j] == i)
		std::cout << G.vertices[i].name << "--" << G.vertices[j].name << endl;//输出经过的最短路径上的边的两端顶点  
	else
	{
		OutPutShortestPath(G, path, D, i, path[i][j]);//依次找经过的中间路径   
		OutPutShortestPath(G, path, D, path[i][j], j);
	}
}


void MiniDistanse(ALGraph G1, int path[][MAX_VERTEX_NUM], int D[][MAX_VERTEX_NUM])//输出最短路径 
{
	ShortestPath(G1, path, D);
	string A, B;
	std::cout << "请输入要查询距离的两个景点的名称：";
	std::cin >> A >> B;
	while (true)
	{
	
		if (if_vex_in(G1, A))
		{
			if (if_vex_in(G1, B))
			{
				break;
			}
			else{
				cout << "输入的景点名不存在，请重新核对并重新输入:";
				std::cin >> A >> B;
				continue;
			}
		}else{
			cout << "输入的景点名不存在，请重新核对并重新输入:";
			std::cin >> A >> B;
		}
	}
	int i = LocateVex(G1, A);
	int j = LocateVex(G1, B);
	std::cout << "最短路径为：";
	OutPutShortestPath(G1, path, D, i, j);
	std::cout << "最短距离为：" << D[i][j] << endl;
}

