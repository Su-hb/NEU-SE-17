// Win32Project1.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include "Struct.h"
using namespace std;
extern int edges[MAX_VERTEX_NUM][MAX_VERTEX_NUM];//定义存储原图顶点矩阵的全局数组
void ss(ALGraph G){
	for (int i = 0; i < G.vexnum; i++){
		for (int j = 0; j < G.vexnum; j++){
			edges[i][j] = 0;
		}
	}
}
int main()
{
	
	//path用来存储经过的路径，D用来存储两个顶点之间的距离  
	int path[MAX_VERTEX_NUM][MAX_VERTEX_NUM];
	int D[MAX_VERTEX_NUM][MAX_VERTEX_NUM];

	bool Is = true;
	ALGraph G, G1;
	G.vexnum = 0;
	G1.vexnum = 0;
	MainFace();   //主界面();
	while (true)
	{
		std::cout << endl << endl;
		std::cout << "请输入您要选择的菜单项：";
		int c;
		
		std::cin >> c;

		while(!(c >= 0&&c <= 9))
		{
			std::cout << "\t\t\t*输入有误，请重新输入0-9：";
			
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
				break;//导游线路
			case 4:
				checked1(G1);
				TopoSort(G1);
				break;//判断回路 
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



