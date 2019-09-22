// Win32Project1.cpp : �������̨Ӧ�ó������ڵ㡣
//

#include "stdafx.h"
#include "Struct.h"
using namespace std;
extern int edges[MAX_VERTEX_NUM][MAX_VERTEX_NUM];//����洢ԭͼ��������ȫ������
void ss(ALGraph G){
	for (int i = 0; i < G.vexnum; i++){
		for (int j = 0; j < G.vexnum; j++){
			edges[i][j] = 0;
		}
	}
}
int main()
{
	
	//path�����洢������·����D�����洢��������֮��ľ���  
	int path[MAX_VERTEX_NUM][MAX_VERTEX_NUM];
	int D[MAX_VERTEX_NUM][MAX_VERTEX_NUM];

	bool Is = true;
	ALGraph G, G1;
	G.vexnum = 0;
	G1.vexnum = 0;
	MainFace();   //������();
	while (true)
	{
		std::cout << endl << endl;
		std::cout << "��������Ҫѡ��Ĳ˵��";
		int c;
		
		std::cin >> c;

		while(!(c >= 0&&c <= 9))
		{
			std::cout << "\t\t\t*������������������0-9��";
			
			std::cin >> c;
		}
		switch (c)
		{
			case 1:
				CreatGraph(G);
				break;

			case 2:
				checked(G);
				OutputGraph(G);
				break;

			case 3:
				checked(G);
				CreatTourSortGraph(G, G1);
				break;//������·
			case 4:
				checked1(G1);
				TopoSort(G1);
				break;//�жϻ�· 
			case 5:
				checked(G);
				MiniDistanse(G, path, D);
				break;
			case 6:
				checked(G);
				MiniSpanTree(G, G.vertices[0].name);
				break;
			case 7:
				checked(G);
				Search(G);
				break;
			case 8:
				checked(G);
				BubbleSort(G);
				break;
			case 9:
				system("cls");
				Car();
				break;
			case 0:
				ss(G);
				exit(0);
			}
			

	}
	return 0;
}



