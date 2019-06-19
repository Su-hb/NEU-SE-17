#include<iostream>
#include<stdio.h>
#include <fstream>
using namespace std;
int num;
int total = 0;
int c[99]={0};
int count = 0;
void search(int cur);

int main(){
	cout << "请输入皇后数" << endl;
    cin>>num;
    search(0);
	return 0;
}

void print(){

    total ++;
	ofstream out("output.txt", ios::app);
    out<<"#########第"<<total<<"种解法##########3"<<endl;
    out<<"\n-------------------------------------------------------------------|\n";
    for(int i = 0; i < num; i++){
        for(int j = 0; j < num; j++){
            if(c[i] == j){
                out<<("|*");
            }else{
                out<<("| ");
            }
        }
        out<<"|\n-------------------------------------------------------------------\n";
    }
}

void search(int cur){
    if(cur == num){
        print();
    }else{
        for(int i = 0; i < num; i++){
            c[cur] = i;
            int ok = 1;
            for(int j = 0; j < cur; j++){
                if(c[cur] == c[j] || j - c[j] == cur - c[cur] || j + c[j] == cur + c[cur]){
                    ok=0;
                    break;
                }
            }
            if(ok){
                search(cur+1);
            }
        }
    }
}
