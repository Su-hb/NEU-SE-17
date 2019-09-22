
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
#define size 1           //ͣ����λ����


typedef struct ArcNode  /*�߱���*/
{
	int adjvex;   /*�ڽӵ���*/
	struct ArcNode *nextarc;  /*ָ����һ���ڽӵ��ָ����*/
	int weight;
	
}ArcNode;//���嶥����Ϣ   

typedef struct VNode /*�������*/
{
	string name;   //�����򣬴�ž�������
	string introduce;
	int popular;  //��ӭ��Ϊ0-100��
	ArcNode *firstarc;  //�߱�ͷָ��
}VNode, AdjList[MAX_VERTEX_NUM];//�������Ϣ   

typedef struct
{
	AdjList vertices;
	int vexnum, arcnum;
}ALGraph;//�����ڽӱ�   

typedef struct edge
{
	string vex;
	int lowcost;
}Edge[MAX_VERTEX_NUM];//���帨������

//ģ��ͣ�����Ķ�ջ�����ʣ�
typedef struct zanlind{
	int number;   //��������
	int ar_time;   //��������ʱ��
}zanInode;

typedef struct{
	zanInode   *base;   //ͣ�����Ķ�ջ��
	zanInode   *top;   //ͣ�����Ķ�ջ��
	int stacksize_curren;
}stackhead;

//ģ�����Ķ��е����ʣ�
typedef struct duilie{
	int number;      //��������
	int ar_time;     //��������ʱ��
	struct duilie *next;
}*queueptr;

typedef struct{
	queueptr front;   //����Ķ��еĶ�ͷ
	queueptr rear;    //����Ķ��еĶ�β
	int length;
}linkqueue;


void MainFace();   //������
void returnMainFace();//���������� 
void checked(ALGraph G);//����Ƿ���ͼ�Ѵ��� 
void checked1(ALGraph G1);//����Ƿ���ͼ�Ѵ��� 
bool if_vex_in(ALGraph a, string e);//�жϽ���Ƿ���ͼ�� 
int LocateVex(ALGraph a, string e);//���ؽ�����ڽӱ��е���� 
bool IsEdge(ALGraph G, string e1, string e2);//�ж�Ҫ�������������֮���Ƿ���ֱ�������ı�  
void CreatGraph(ALGraph &G);//����ͼ���ڽӱ�洢 
void OutputGraph(ALGraph G);//�����������ͼ ,���ڽӾ������ʽ���

void DFS(ALGraph G, int v);//�ݹ����  
void DFSTraverse(ALGraph G);//ͼ����ȱ���  
void CreatTourSortGraph(ALGraph G, ALGraph &G1);//����������·ͼ 
int Mininum(ALGraph G, Edge a);//Ѱ�һ�û��������С�������еıߵ���Сֵ 
void MiniSpanTree(ALGraph G, string u);//����С������ 

void TopoSort(ALGraph G1);//��������  
void FindInDegree(ALGraph G1, int indegree[]);//����ÿ���������ȣ��洢��indegree������  

void ShortestPath(ALGraph G, int path[][MAX_VERTEX_NUM], int  D[][MAX_VERTEX_NUM]);//�������·��  
void OutPutShortestPath(ALGraph G, int path[][MAX_VERTEX_NUM], int  D[][MAX_VERTEX_NUM], int i, int j);//�����·��  
void MiniDistanse(ALGraph G, int path[][MAX_VERTEX_NUM], int  D[][MAX_VERTEX_NUM]);//������·�� 
void Search(ALGraph G);//��������
int SearchIntro(ALGraph G,char str[MAXNUM]);//�����������
void BubbleSort(ALGraph G);//ð������
int ReturnVex(ALGraph G, int a);//���ػ�ӭ��Ϊa�ľ����±�����
void Car();

//��ջ�Ļ���������
void initstack(stackhead &L);   //����һ����ջ
void push(stackhead &L, zanInode e); //��Ԫ��eѹ��sջ
void pop(stackhead &L, zanInode &e); //��Ԫ��e����sջ


//���еĻ���������
void initqueue(linkqueue &q); //����һ���ն��� 
void enqueue(linkqueue &q, int number, int ar_time); //��Ԫ�صĲ�����У�����Ϊnumber��ar_time��
void popqueue(linkqueue &q, queueptr &w);//��Ԫ�صĲ�����У�����Ϊnumber��ar_time��
void EnterPark(stackhead &st, linkqueue &q);  //�Խ���ͣ�����������Ĵ���
void likai(stackhead &st, stackhead &sl, linkqueue &q);//���뿪�������Ĵ���
void ParkMain();

