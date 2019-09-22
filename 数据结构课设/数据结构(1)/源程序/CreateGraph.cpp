#include "stdafx.h"
#include "Struct.h"

void CreatGraph(ALGraph &G)//创建图的邻接表存储
{


	//文件输入
	int i;
	string s, x, y;
	int weight;
	string filename = "map.txt";
	ifstream inf(filename.c_str());
	ArcNode *p, *q;

	std::cout << "请输入顶点数和边数：";
	std::cin >> G.vexnum >> G.arcnum;
	std::cout << endl << "                " << "\"请输入各顶点的信息\"" << endl << endl;
	std::cout << "请输入各顶点的名字、景点介绍、欢迎度（0-100的整数）：" << endl;
	for (i = 0; i < G.vexnum; i++)     //输入顶点 
	{
		inf >> G.vertices[i].name;//输入顶点
		inf >> G.vertices[i].introduce;
		inf >> s;
		G.vertices[i].popular = atoi(s.c_str());
		std::cout << G.vertices[i].name << " ";
		std::cout << G.vertices[i].introduce << " ";
		std::cout << G.vertices[i].popular << " " << endl;
		G.vertices[i].firstarc = NULL;   //首先初始化为NULL 

	}
	cout << endl;

	for (i = 0; i<G.arcnum; i++)
	{
		std::cout << "请输入第" << i + 1 << "条边的两个顶点以及该边的权值：";
		inf >> s;
		x = s;
		inf >> s;
		y = s;
		inf >> s;
		weight = atoi(s.c_str());
		cout << x << " " << y << " " << weight << endl;
		if (if_vex_in(G, x)){
			if (if_vex_in(G, y))
			{
				p = (struct ArcNode *)malloc(sizeof(struct  ArcNode));
				q = (struct ArcNode *)malloc(sizeof(struct  ArcNode));

				p->adjvex = LocateVex(G, x);   //保存该弧所指向的顶点位置 
				q->adjvex = LocateVex(G, y);   // 保存该弧所指向的顶点位置 


				p->weight = weight;   // 保存权值到一个结点里 
				q->weight = weight; //保存权值到一个结点里

				p->nextarc = G.vertices[LocateVex(G, y)].firstarc;
				G.vertices[LocateVex(G, y)].firstarc = p;

				q->nextarc = G.vertices[LocateVex(G, x)].firstarc;
				G.vertices[LocateVex(G, x)].firstarc = q;

			}
			else if (!if_vex_in(G, y)){
				std::cout << "请重新输入\n";
				i--;
				continue;
			}
		}
		else if (!if_vex_in(G, x)){
			std::cout << "请重新输入\n";
			i--;
		}
	}
	inf.close();
	
	//键盘输入
	/*
	int weight;
	string x, y;
	int i;
	ArcNode *p, *q;

	std::cout << "请输入顶点数和边数：";
	std::cin >> G.vexnum >> G.arcnum;
	std::cout << endl << "                " << "\"请输入各顶点的信息\"" << endl << endl;
	std::cout << "请输入各顶点的名字、景点介绍、欢迎度（0-100）：" << endl;

	for (i = 0; i<G.vexnum; i++)     //输入顶点
	{
	std::cin >> G.vertices[i].name;//输入顶点
	std::cin >> G.vertices[i].introduce;
	std::cin >> G.vertices[i].popular;
	getchar();
	G.vertices[i].firstarc = NULL;   //首先初始化为NULL
	}

	for (i = 0; i<G.arcnum; i++)
	{
	std::cout << "请输入第" << i + 1 << "条边的两个顶点以及该边的权值：";
	std::cin >> x >> y >> weight;
	getchar();
	if (if_vex_in(G, x)){
	if (if_vex_in(G, y))
	{
	p = (struct ArcNode *)malloc(sizeof(struct  ArcNode));
	q = (struct ArcNode *)malloc(sizeof(struct  ArcNode));

	p->adjvex = LocateVex(G, x);   //保存该弧所指向的顶点位置
	q->adjvex = LocateVex(G, y);   // 保存该弧所指向的顶点位置


	p->weight = weight;   // 保存权值到一个结点里
	q->weight = weight;   //保存权值到一个结点里

	p->nextarc = G.vertices[LocateVex(G, y)].firstarc;
	G.vertices[LocateVex(G, y)].firstarc = p;

	q->nextarc = G.vertices[LocateVex(G, x)].firstarc;
	G.vertices[LocateVex(G, x)].firstarc = q;

	}
	else if (!if_vex_in(G, y)){
	std::cout << "请重新输入\n";
	i--;
	continue;
	}
	}
	else if (!if_vex_in(G, x)){
	std::cout << "请重新输入\n";
	i--;
	}
	}
	*/
}
