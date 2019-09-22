#include "stdafx.h"
#include "Struct.h"


int k = 0;
bool visited[MAX_VERTEX_NUM];//�ж϶����Ƿ񱻱����� 
string vex[MAX_VERTEX_NUM];//�洢�����Ķ������� 
ArcNode *p;
string vex1[2 * MAX_VERTEX_NUM];//�洢������·ͼ�Ķ���������� 
extern int edges[MAX_VERTEX_NUM][MAX_VERTEX_NUM];//����洢ԭͼ��������ȫ������


void DFS(ALGraph G, int v)
{
	visited[v] = true;
	vex[k++] = G.vertices[v].name;
	p = G.vertices[v].firstarc;

	while (p != NULL){
		if (!visited[p->adjvex]){
			DFS(G, p->adjvex);//p�������ڽӵ㶼��������
			p = G.vertices[v].firstarc;
		}
		p = p->nextarc;
	}
}

void DFSTraverse(ALGraph G)
{
	int v;
	for (v = 0; v<G.vexnum; v++){
		visited[v] = false;//��ʼ����������Ϊû�б�����  
	}
	for (v = 0; v<G.vexnum; v++){
		if (!visited[v])//���û�б����ʾͶ������������ȱ���    
			DFS(G, v);
	}
}

void CreatTourSortGraph(ALGraph G, ALGraph &G1)
{
	DFSTraverse(G);
	cout << endl;
	int i, j, n = 0;
	bool Is;
	for (i = 0; i<G.vexnum - 1; i++)
	{
		k = 0;
		Is = true;
		while (Is)
		{
			vex1[n++] = vex[i + k];
			if (IsEdge(G, vex[i + k], vex[i + 1]))//�������֮���б߾�ֱ��������������     
				Is = false;
			else
				k--;//���û�оͻ��ݣ�ֱ���ҵ���vex[i+1]�бߵ�   
		}
	}
	vex1[n] = vex[i];//�����һ������Ž�vex������  

	for (i = 0; i <= n; i++)
		visited[i] = false;

	for (i = 0; i<G.vexnum; i++)//��ʼ������ͼ  
	{
		G1.vertices[i].name = G.vertices[i].name;
		G1.vertices[i].firstarc = NULL;
	}

	int arcnum = 0;//��¼������·ͼ�еıߵĸ���  
	for (k = 0; k<n; k++)
	{
		i = LocateVex(G, vex1[k]);
		j = LocateVex(G, vex1[k + 1]);
		if (visited[j])//����õ��Ѿ��ڵ�����·ͼ,/��Ѱ�����Ƿ����Ѿ��ڵ�����·ͼ�еĵ��γɻ�·   
		{
			int m = k + 2;//��������ֱ��ǰ��  
			if (m <= n)//�ж��Ƿ񳬳��洢������·ͼ������    
			{
				while (visited[LocateVex(G, vex1[m])])//ֱ�������µĽڵ�Ϊֹ     
				{
					/*�ж�����������ԭ���ľ����ֲ�ͼ���Ƿ��бߣ�����о������������ߣ����߼��������*/
					if (IsEdge(G, vex1[k], vex1[m]))
					{
						j = LocateVex(G, vex1[m]);
						ArcNode *P = new ArcNode;
						ArcNode *Q = new ArcNode;
						P->adjvex = j;
						P->weight = edges[i][j];
						Q = G1.vertices[i].firstarc;
						G1.vertices[i].firstarc = P;
						P->nextarc = Q;
						arcnum++;
					}
					if (++m >= n)//����ڲ��ҵĹ����е�������ĩβ��ǿ���˳�       
						break;
				}
			}
		}
		else//���û���ڵ�����·ͼ�о���������   
		{
			visited[i] = visited[j] = true;
			ArcNode *P = new ArcNode;
			ArcNode *Q = new ArcNode;
			P->adjvex = j;
			P->weight = edges[i][j];
			Q = G1.vertices[i].firstarc;
			G1.vertices[i].firstarc = P;
			P->nextarc = Q;
			arcnum++;
		}
	}
	G1.vexnum = G.vexnum;//��ֵ������·ͼ�ж���ĸ���  
	G1.arcnum = arcnum;//��ֵ������·ͼ�бߵĸ���  
	cout << "����·��Ϊ��";
	for (i = 0; i <= n; i++)
		cout << vex1[i];//���������·ͼ  
}
