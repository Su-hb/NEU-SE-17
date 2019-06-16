#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <fstream>
#include "string.h"
using namespace std;
int next[32] = {-999};

char* outc(char c)
{
    //字符串转为二进制编码
    char res[8];
	unsigned char k = 0x80;

	for (int i=0; i<8; i++, k >>= 1){
		if (c & k){
			res[i] = '1';
        }else{
			res[i] = '0';
        }
	}
	return res;
}

void getNextValue(char* T,int *nextVal, int size_T)          //求模式串的next数组并存入nextVal中
{
	int i,j;
	i = 1;                                         //后缀游标
	j = 0;                                         //前缀游标
	nextVal[1] = 0;

	while(i < size_T)
	{
		if(j==0 || T[i] == T[j])                  //T[i]表示后缀的单个字符，T[j]表示前缀的单个字符
		{
			++i;
			++j;

			nextVal[i] = j;
		}
		else
		{
			j = nextVal[j];             //前缀与后缀不同，游标j回溯到nextVal[j]的位置，重新与失配的i位置的字符对比
		}
	}

}

int index_KMP(char* S,char* T,int pos, int size_S, int size_T)    //从主字符串S的pos位置开始匹配模式串T
{
	int i = pos;
	int j = 1;
	int *next = new int[size_T];
	next[0] = size_T;

	getNextValue(T,next, size_T);                        //获取next数组

	while(i<size_S && j<size_T)                           //开始匹配主串与模式串
	{
		if(j==0 || S[i]==T[j])
		{
			i++;
			j++;
		}
		else
		{
			j = next[j];                  //匹配失败，游标j回溯到next[j]的位置，重新与失配的i位置的字符对比
		}
	}

	if(j >= size_T)
	{
		return i-size_T+1;                         //匹配成功，返回匹配成功的开始位置
	}
	else
	{
		return -1;                                 //匹配失败，返回0
	}
}


//int argc, char ** argv
int main()
{
    ofstream out("out.bin");
    ifstream in("in.bin");//argv[1]
    if(out.is_open() && in.is_open()){
        in.seekg(0, in.end);   //追溯到流的尾部
        int length = in.tellg();  //获取流的长度
        in.seekg(0, in.beg);  //回到流的头部

        char* res = new char[999];

        in.read(res,length);
        cout<<"文件长度:"<<length<<endl;
        int pos = 0;
        int index = 0;
        int num = 1;
        char* tmp  = new char[999];
        char* nums = new char[999];

        while(length > 0){
            index = index_KMP(res,"00001010",pos,length,8);

            nums = outc((char)num );
            num++;
            for(int i = 0; i < 8;i++){
                tmp[i] = nums[i];
            }
            for(int i =0;i < index+8;i++){
                tmp[i+8] = res[i];
            }
            res = res + index + 8;
            length = length -index -8;
            out.write(tmp,strlen(tmp));
            out.write("00001010",8);
            out.flush();
        }


        out.close();
    }
    else{
        cout<<"Error!文件未正常打开！"<<endl;
    }
    system("pause");
    return 0;
}
