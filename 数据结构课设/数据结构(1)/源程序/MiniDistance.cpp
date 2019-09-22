#include "stdafx.h"
#include "Struct.h"

extern int edges[MAX_VERTEX_NUM][MAX_VERTEX_NUM];//����洢ԭͼ��������ȫ������



void ShortestPath(ALGraph G, int path[][MAX_VERTEX_NUM], int D[][MAX_VERTEX_NUM])//�����·�� 
{
	int u, v, w;
	for (v = 0; v<G.vexnum; v++)
		for (w = 0; w<G.vexnum; w++)
		{
			D[v][w] = edges[v][w];//����̾����ʼ��Ϊ��������֮���Ȩֵ    
			if (edges[v][w]<MAXNUM)
				path[v][w] = v;//�����·����ʼ��Ϊ�����ǰһ���������   
		}   
	for (u = 0; u < G.vexnum; u++)
	{
		for (v = 0; v < G.vexnum; v++)
		{
			for (w = 0; w < G.vexnum; w++)
			{
				if (D[v][u] + D[u][w]<D[v][w])//����¼���Ľ�㵼�����·������ˣ��͸�������ͬʱ��¼���ӵ�·���ı�� 
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
		std::cout << G.vertices[i].name << "--" << G.vertices[j].name << endl;//������������·���ϵıߵ����˶���  
	else
	{
		OutPutShortestPath(G, path, D, i, path[i][j]);//�����Ҿ������м�·��   
		OutPutShortestPath(G, path, D, path[i][j], j);
	}
}


void MiniDistanse(ALGraph G1, int path[][MAX_VERTEX_NUM], int D[][MAX_VERTEX_NUM])//������·�� 
{
	ShortestPath(G1, path, D);
	string A, B;
	std::cout << "������Ҫ��ѯ�����������������ƣ�";
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
				cout << "����ľ����������ڣ������º˶Բ���������:";
				std::cin >> A >> B;
				continue;
			}
		}else{
			cout << "����ľ����������ڣ������º˶Բ���������:";
			std::cin >> A >> B;
		}
	}
	int i = LocateVex(G1, A);
	int j = LocateVex(G1, B);
	std::cout << "���·��Ϊ��";
	OutPutShortestPath(G1, path, D, i, j);
	std::cout << "��̾���Ϊ��" << D[i][j] << endl;
}

