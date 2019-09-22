#include "stdafx.h"
#include "Struct.h"
extern int edges[MAX_VERTEX_NUM][MAX_VERTEX_NUM];//����洢ԭͼ��������ȫ������


int Mininum(ALGraph G, Edge a)
{
	int min, i, j, mark;
	for (i = 0; a[i].lowcost == 0; i++);//�ҳ���û�м�����С�������ı�  
		min = a[i].lowcost;

	mark = i;

	for (j = i + 1; j<G.vexnum; j++)//�ҳ�Ȩֵ��С��   
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
	for (int j = 0; j<G.vexnum; j++)//��ʼ�������ߵ���Ϣ   
		if (j != i)
		{
			closedge[j].vex = u;
			closedge[j].lowcost = edges[i][j];
		}

	closedge[i].lowcost = 0;//��ʼ��u�Ѿ�����С��������  
	int k;

	std::cout << "��·�޽��滮ͼΪ��" << endl;
	for (i = 1; i<G.vexnum; i++)
	{
		k = Mininum(G, closedge);
		std::cout << "��" << closedge[k].vex << "��" << G.vertices[k].name << "��һ��·" << endl;

		closedge[k].lowcost = 0;//��k���������������   
		for (j = 0; j<G.vexnum; j++)
			if (edges[k][j]<closedge[j].lowcost)//���¼���Ķ���ʹ�����С�ˣ��͸�����    
			{
				closedge[j].lowcost = edges[k][j];
				closedge[j].vex = G.vertices[k].name;
			}
	}
}

