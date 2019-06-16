#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <fstream>
#include "string.h"
using namespace std;
int next[32] = {-999};

char* outc(char c)
{
    //�ַ���תΪ�����Ʊ���
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

void getNextValue(char* T,int *nextVal, int size_T)          //��ģʽ����next���鲢����nextVal��
{
	int i,j;
	i = 1;                                         //��׺�α�
	j = 0;                                         //ǰ׺�α�
	nextVal[1] = 0;

	while(i < size_T)
	{
		if(j==0 || T[i] == T[j])                  //T[i]��ʾ��׺�ĵ����ַ���T[j]��ʾǰ׺�ĵ����ַ�
		{
			++i;
			++j;

			nextVal[i] = j;
		}
		else
		{
			j = nextVal[j];             //ǰ׺���׺��ͬ���α�j���ݵ�nextVal[j]��λ�ã�������ʧ���iλ�õ��ַ��Ա�
		}
	}

}

int index_KMP(char* S,char* T,int pos, int size_S, int size_T)    //�����ַ���S��posλ�ÿ�ʼƥ��ģʽ��T
{
	int i = pos;
	int j = 1;
	int *next = new int[size_T];
	next[0] = size_T;

	getNextValue(T,next, size_T);                        //��ȡnext����

	while(i<size_S && j<size_T)                           //��ʼƥ��������ģʽ��
	{
		if(j==0 || S[i]==T[j])
		{
			i++;
			j++;
		}
		else
		{
			j = next[j];                  //ƥ��ʧ�ܣ��α�j���ݵ�next[j]��λ�ã�������ʧ���iλ�õ��ַ��Ա�
		}
	}

	if(j >= size_T)
	{
		return i-size_T+1;                         //ƥ��ɹ�������ƥ��ɹ��Ŀ�ʼλ��
	}
	else
	{
		return -1;                                 //ƥ��ʧ�ܣ�����0
	}
}


//int argc, char ** argv
int main()
{
    ofstream out("out.bin");
    ifstream in("in.bin");//argv[1]
    if(out.is_open() && in.is_open()){
        in.seekg(0, in.end);   //׷�ݵ�����β��
        int length = in.tellg();  //��ȡ���ĳ���
        in.seekg(0, in.beg);  //�ص�����ͷ��

        char* res = new char[999];

        in.read(res,length);
        cout<<"�ļ�����:"<<length<<endl;
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
        cout<<"Error!�ļ�δ�����򿪣�"<<endl;
    }
    system("pause");
    return 0;
}
