#include "stdafx.h"
#include "Struct.h"
extern int edges[MAX_VERTEX_NUM][MAX_VERTEX_NUM];//����洢ԭͼ��������ȫ������
bool if_vex_in(ALGraph a, string e)//�жϽ���Ƿ���ͼ��
{

	for (int i = 0; i<a.vexnum; i++){
		if (a.vertices[i].name == e){
			return true;
		}
	}
	return false;
}

int LocateVex(ALGraph a, string e)//���ؽ�����ڽӱ��е����
{
	for (int i = 0; i<a.vexnum; i++){
		if (a.vertices[i].name == e){
			return i;
		}
	}
	return -1;
}


void checked(ALGraph G)//����Ƿ���ͼ�Ѵ���
{
	if (G.vexnum == 0)
	{
		
		std::cout << "\n*ȱ�ٺϷ��ľ�������ֲ�ͼ��\n*���ȴ���һ���Ϸ��ľ�������ֲ�ͼ��\n";
		getchar();

	}
}
void checked1(ALGraph G1)//����Ƿ��е���·��ͼ�Ѵ���
{
	if (G1.vexnum == 0)
	{

		std::cout << "\n*ȱ�ٺϷ��ĵ���·��ͼ��\n*���ȴ���һ���Ϸ��ĵ���·��ͼ��\n";
		

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
