
#include "stdafx.h"
#include "Struct.h"


int ReturnVex(ALGraph G, int a){
	int i;
	for (i = 0; i < G.vexnum; i++){
		if (G.vertices[i].popular == a){
			return i;
		}
	}
	return -1;
}

void BubbleSort(ALGraph G){
	int ary[MAXNUM];
	int i, j, tmp;
	for (i = 0; i<G.vexnum; i++){
		ary[i] = G.vertices[i].popular;
	}
	for (i = 0; i < G.vexnum-1; i++){
		tmp = ary[i];
		for (j = i+1; j<G.vexnum; j++){
			if (tmp > ary[j]){
				ary[i] = ary[j];
				ary[j] = tmp;
				tmp = ary[i];
			}
		}
	}
	std::cout << "景点按照欢迎度排序为：" << endl;
	for (i = 0; i < G.vexnum; i++){
		if (ReturnVex(G, ary[i] != -1)){
			std::cout << G.vertices[ReturnVex(G, ary[i])].name << " " << G.vertices[ReturnVex(G, ary[i])].popular << endl;
		}
	}

}