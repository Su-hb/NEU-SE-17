#include <stdio.h>
#include <windows.h>
#include <conio.h>
int main(){
    char ch;
    int i = 0;
    //ѭ��������ֱ����Esc���˳�
    while(1){
        if(kbhit()){
            ch = getch();
            if(ch == 27){
                break;
            }
        }
        printf("Number: %d\n", ++i);
        Sleep(1000); //��ͣ1��
    }
return 0;
}

