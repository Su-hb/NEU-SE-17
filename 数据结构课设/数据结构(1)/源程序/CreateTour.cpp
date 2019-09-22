#include "stdafx.h"
#include "Struct.h"


int k = 0;
bool visited[MAX_VERTEX_NUM];//判断顶点是否被遍历过 
string vex[MAX_VERTEX_NUM];//存储遍历的顶点序列 
ArcNode *p;
string vex1[2 * MAX_VERTEX_NUM];//存储导游线路图的顶点遍历序列 
extern int edges[MAX_VERTEX_NUM][MAX_VERTEX_NUM];//定义存储原图顶点矩阵的全局数组


void DFS(ALGraph G, int v)
{
	visited[v] = true;
	vex[k++] = G.vertices[v].name;
	p = G.vertices[v].firstarc;

	while (p != NULL){
		if (!visited[p->adjvex]){
			DFS(G, p->adjvex);//p的所有邻接点都被遍历过
			p = G.vertices[v].firstarc;
		}
		p = p->nextarc;
	}
}

void DFSTraverse(ALGraph G)
{
	int v;
	for (v = 0; v<G.vexnum; v++){
		visited[v] = false;//初始化遍历顶点为没有被访问  
	}
	for (v = 0; v<G.vexnum; v++){
		if (!visited[v])//如果没有被访问就对其进行深度优先遍历    
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
			if (IsEdge(G, vex[i + k], vex[i + 1]))//如果他们之间有边就直接连接上这条边     
				Is = false;
			else
				k--;//如果没有就回溯，直到找到和vex[i+1]有边的   
		}
	}
	vex1[n] = vex[i];//将最后一个顶点放进vex数组中  

	for (i = 0; i <= n; i++)
		visited[i] = false;

	for (i = 0; i<G.vexnum; i++)//初始化导游图  
	{
		G1.vertices[i].name = G.vertices[i].name;
		G1.vertices[i].firstarc = NULL;
	}

	int arcnum = 0;//记录导游线路图中的边的个数  
	for (k = 0; k<n; k++)
	{
		i = LocateVex(G, vex1[k]);
		j = LocateVex(G, vex1[k + 1]);
		if (visited[j])//如果该点已经在导游线路图,/就寻找它是否与已经在导游线路图中的点形成回路   
		{
			int m = k + 2;//跳过他的直接前驱  
			if (m <= n)//判断是否超出存储导游线路图的数组    
			{
				while (visited[LocateVex(G, vex1[m])])//直到出现新的节点为止     
				{
					/*判断这两个点在原来的景区分布图中是否有边，如果有就连接上这条边，否者继续向后找*/
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
					if (++m >= n)//如果在查找的过程中到了数组末尾就强行退出       
						break;
				}
			}
		}
		else//如果没有在导游线路图中就连接上它   
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
	G1.vexnum = G.vexnum;//赋值导游线路图中顶点的个数  
	G1.arcnum = arcnum;//赋值导游线路图中边的个数  
	cout << "导游路线为：";
	for (i = 0; i <= n; i++)
		cout << vex1[i];//输出导游线路图  
}
