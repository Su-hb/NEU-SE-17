#include "stdafx.h"
#include "Struct.h"

void CreatGraph(ALGraph &G)//����ͼ���ڽӱ�洢
{


	//�ļ�����
	int i;
	string s, x, y;
	int weight;
	string filename = "map.txt";
	ifstream inf(filename.c_str());
	ArcNode *p, *q;

	std::cout << "�����붥�����ͱ�����";
	std::cin >> G.vexnum >> G.arcnum;
	std::cout << endl << "                " << "\"��������������Ϣ\"" << endl << endl;
	std::cout << "���������������֡�������ܡ���ӭ�ȣ�0-100����������" << endl;
	for (i = 0; i < G.vexnum; i++)     //���붥�� 
	{
		inf >> G.vertices[i].name;//���붥��
		inf >> G.vertices[i].introduce;
		inf >> s;
		G.vertices[i].popular = atoi(s.c_str());
		std::cout << G.vertices[i].name << " ";
		std::cout << G.vertices[i].introduce << " ";
		std::cout << G.vertices[i].popular << " " << endl;
		G.vertices[i].firstarc = NULL;   //���ȳ�ʼ��ΪNULL 

	}
	cout << endl;

	for (i = 0; i<G.arcnum; i++)
	{
		std::cout << "�������" << i + 1 << "���ߵ����������Լ��ñߵ�Ȩֵ��";
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

				p->adjvex = LocateVex(G, x);   //����û���ָ��Ķ���λ�� 
				q->adjvex = LocateVex(G, y);   // ����û���ָ��Ķ���λ�� 


				p->weight = weight;   // ����Ȩֵ��һ������� 
				q->weight = weight; //����Ȩֵ��һ�������

				p->nextarc = G.vertices[LocateVex(G, y)].firstarc;
				G.vertices[LocateVex(G, y)].firstarc = p;

				q->nextarc = G.vertices[LocateVex(G, x)].firstarc;
				G.vertices[LocateVex(G, x)].firstarc = q;

			}
			else if (!if_vex_in(G, y)){
				std::cout << "����������\n";
				i--;
				continue;
			}
		}
		else if (!if_vex_in(G, x)){
			std::cout << "����������\n";
			i--;
		}
	}
	inf.close();
	
	//��������
	/*
	int weight;
	string x, y;
	int i;
	ArcNode *p, *q;

	std::cout << "�����붥�����ͱ�����";
	std::cin >> G.vexnum >> G.arcnum;
	std::cout << endl << "                " << "\"��������������Ϣ\"" << endl << endl;
	std::cout << "���������������֡�������ܡ���ӭ�ȣ�0-100����" << endl;

	for (i = 0; i<G.vexnum; i++)     //���붥��
	{
	std::cin >> G.vertices[i].name;//���붥��
	std::cin >> G.vertices[i].introduce;
	std::cin >> G.vertices[i].popular;
	getchar();
	G.vertices[i].firstarc = NULL;   //���ȳ�ʼ��ΪNULL
	}

	for (i = 0; i<G.arcnum; i++)
	{
	std::cout << "�������" << i + 1 << "���ߵ����������Լ��ñߵ�Ȩֵ��";
	std::cin >> x >> y >> weight;
	getchar();
	if (if_vex_in(G, x)){
	if (if_vex_in(G, y))
	{
	p = (struct ArcNode *)malloc(sizeof(struct  ArcNode));
	q = (struct ArcNode *)malloc(sizeof(struct  ArcNode));

	p->adjvex = LocateVex(G, x);   //����û���ָ��Ķ���λ��
	q->adjvex = LocateVex(G, y);   // ����û���ָ��Ķ���λ��


	p->weight = weight;   // ����Ȩֵ��һ�������
	q->weight = weight;   //����Ȩֵ��һ�������

	p->nextarc = G.vertices[LocateVex(G, y)].firstarc;
	G.vertices[LocateVex(G, y)].firstarc = p;

	q->nextarc = G.vertices[LocateVex(G, x)].firstarc;
	G.vertices[LocateVex(G, x)].firstarc = q;

	}
	else if (!if_vex_in(G, y)){
	std::cout << "����������\n";
	i--;
	continue;
	}
	}
	else if (!if_vex_in(G, x)){
	std::cout << "����������\n";
	i--;
	}
	}
	*/
}
