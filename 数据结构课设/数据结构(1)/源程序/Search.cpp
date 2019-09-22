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
		strcpy_s(str1, G.vertices[i].introduce.c_str());//��stringת��Ϊchar[]
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
	std::cout << "��������Ҫ�����Ĺؼ���:";
	cin >> s;
	strcpy_s(str2, s.c_str());//��stringת��Ϊchar[]
	if (if_vex_in(G, s)){
		i = LocateVex(G, s);
		cout << "�Ѳ��ҵ�" << endl;
		cout<<"�������ľ��������������Ϊ��";
		cout << G.vertices[i].name << " ";
		cout << G.vertices[i].introduce << endl;
	
		c = getchar();

	}
	else if (SearchIntro(G, str2) != -1){
		cout << "�Ѳ��ҵ�" << endl;
		cout << "�������ľ��������������Ϊ��";
		cout << G.vertices[SearchIntro(G, str2)].name << " ";
		cout << G.vertices[SearchIntro(G, str2)].introduce << endl;
	}
	else{
		cout << "δ���ҵ�";
		cout << "������Y/N��Y�����������ؼ��֣�N���������˵�";
		cin >> c;
		if (c == 'Y' || c == 'y'){
			Search(G);
		}
		else if (c == 'N' || c == 'n'){
			MainFace();
		}
		else{
			cout << "����������Y/N��Y�����������ؼ��֣�N���������˵���";
		}
		
	}

}