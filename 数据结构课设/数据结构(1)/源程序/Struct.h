
#include <string>
#include <iostream> 
#include <stdio.h>
#include <fstream>
#include <conio.h>
#include <stdlib.h>
#include <cstring>
#include <math.h>

using namespace std;

#define MAXNUM 32767  
#define MAX_VERTEX_NUM 20 
#define size 1           //停车场位置数


typedef struct ArcNode  /*边表结点*/
{
	int adjvex;   /*邻接点域*/
	struct ArcNode *nextarc;  /*指向下一个邻接点的指针域*/
	int weight;
	
}ArcNode;//定义顶点信息   

typedef struct VNode /*顶点表结点*/
{
	string name;   //顶点域，存放景点名称
	string introduce;
	int popular;  //欢迎度为0-100；
	ArcNode *firstarc;  //边表头指针
}VNode, AdjList[MAX_VERTEX_NUM];//定义边信息   

typedef struct
{
	AdjList vertices;
	int vexnum, arcnum;
}ALGraph;//定义邻接表   

typedef struct edge
{
	string vex;
	int lowcost;
}Edge[MAX_VERTEX_NUM];//定义辅助数组

//模拟停车场的堆栈的性质；
typedef struct zanlind{
	int number;   //汽车车号
	int ar_time;   //汽车到达时间
}zanInode;

typedef struct{
	zanInode   *base;   //停车场的堆栈底
	zanInode   *top;   //停车场的堆栈顶
	int stacksize_curren;
}stackhead;

//模拟便道的队列的性质；
typedef struct duilie{
	int number;      //汽车车号
	int ar_time;     //汽车到达时间
	struct duilie *next;
}*queueptr;

typedef struct{
	queueptr front;   //便道的队列的对头
	queueptr rear;    //便道的队列的队尾
	int length;
}linkqueue;


void MainFace();   //主界面
void returnMainFace();//返回主界面 
void checked(ALGraph G);//检查是否有图已创建 
void checked1(ALGraph G1);//检查是否有图已创建 
bool if_vex_in(ALGraph a, string e);//判断结点是否在图中 
int LocateVex(ALGraph a, string e);//返回结点在邻接表中的序号 
bool IsEdge(ALGraph G, string e1, string e2);//判断要查的这两个顶点之间是否有直接相连的边  
void CreatGraph(ALGraph &G);//创建图的邻接表存储 
void OutputGraph(ALGraph G);//输出景区景点图 ,以邻接矩阵的形式输出

void DFS(ALGraph G, int v);//递归遍历  
void DFSTraverse(ALGraph G);//图的深度遍历  
void CreatTourSortGraph(ALGraph G, ALGraph &G1);//创建导游线路图 
int Mininum(ALGraph G, Edge a);//寻找还没有纳入最小生成树中的边的最小值 
void MiniSpanTree(ALGraph G, string u);//求最小生成树 

void TopoSort(ALGraph G1);//拓扑排序  
void FindInDegree(ALGraph G1, int indegree[]);//计算每个顶点的入度，存储在indegree数组中  

void ShortestPath(ALGraph G, int path[][MAX_VERTEX_NUM], int  D[][MAX_VERTEX_NUM]);//计算最短路径  
void OutPutShortestPath(ALGraph G, int path[][MAX_VERTEX_NUM], int  D[][MAX_VERTEX_NUM], int i, int j);//求最短路径  
void MiniDistanse(ALGraph G, int path[][MAX_VERTEX_NUM], int  D[][MAX_VERTEX_NUM]);//输出最短路径 
void Search(ALGraph G);//搜索景点
int SearchIntro(ALGraph G,char str[MAXNUM]);//景点介绍搜索
void BubbleSort(ALGraph G);//冒泡排序
int ReturnVex(ALGraph G, int a);//返回欢迎度为a的景点下标索引
void Car();

//堆栈的基本操作；
void initstack(stackhead &L);   //构造一个空栈
void push(stackhead &L, zanInode e); //把元素e压入s栈
void pop(stackhead &L, zanInode &e); //把元素e弹出s栈


//队列的基本操作；
void initqueue(linkqueue &q); //构造一个空队列 
void enqueue(linkqueue &q, int number, int ar_time); //把元素的插入队列（属性为number，ar_time）
void popqueue(linkqueue &q, queueptr &w);//把元素的插入队列（属性为number，ar_time）
void EnterPark(stackhead &st, linkqueue &q);  //对进入停车场的汽车的处理；
void likai(stackhead &st, stackhead &sl, linkqueue &q);//对离开的汽车的处理；
void ParkMain();

