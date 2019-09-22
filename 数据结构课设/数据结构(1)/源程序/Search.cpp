#include "stdafx.h"
#include "Struct.h"

int SearchIntro(ALGraph G, char str2[MAXNUM]){
	int i = 0;

	char str1[MAXNUM];
	while (i<G.vexnum){
		for (int m = 0; m < MAXNUM; m++){
			str1[m] = 0;
		}
		int k = 0;
		int j = 0, p = 0;
		strcpy_s(str1, G.vertices[i].introduce.c_str());//将string转换为char[]
		while (str1[k] != 0 && str2[j] != 0){
			if (str1[k] != str2[j]){
				k++;
			}
			else{
				p = k;
				while (str1[p] == str2[j] && str1[p] != 0 && str2[j] != 0){
					p++;
					j++;

				}
				if (str2[j] == 0){
					return i;
				}
				else{
					k++;
					j = 0;
				}

			}
		}
		i++;
	}
	return -1;
}

void Search(ALGraph G){
	string s;
	char c;
	char str2[MAXNUM];
	int i = 0;
	std::cout << "请输入你要搜索的关键字:";
	cin >> s;
	strcpy_s(str2, s.c_str());//将string转换为char[]
	if (if_vex_in(G, s)){
		i = LocateVex(G, s);
		cout << "已查找到" << endl;
		cout<<"搜索到的经典名及景点介绍为：";
		cout << G.vertices[i].name << " ";
		cout << G.vertices[i].introduce << endl;
	
		c = getchar();

	}
	else if (SearchIntro(G, str2) != -1){
		cout << "已查找到" << endl;
		cout << "搜索到的经典名及景点介绍为：";
		cout << G.vertices[SearchIntro(G, str2)].name << " ";
		cout << G.vertices[SearchIntro(G, str2)].introduce << endl;
	}
	else{
		cout << "未查找到";
		cout << "请输入Y/N，Y代表继续输入关键字，N代表返回主菜单";
		cin >> c;
		if (c == 'Y' || c == 'y'){
			Search(G);
		}
		else if (c == 'N' || c == 'n'){
			MainFace();
		}
		else{
			cout << "请重新输入Y/N，Y代表继续输入关键字，N代表返回主菜单：";
		}
		
	}

}