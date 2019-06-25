#include <stdio.h>
#include <windows.h>
#include <conio.h>
int main(){
    char ch;
    int i = 0;
    //循环监听，直到按Esc键退出
    while(1){
        if(kbhit()){
            ch = getch();
            if(ch == 27){
                break;
            }
        }
        printf("Number: %d\n", ++i);
        Sleep(1000); //暂停1秒
    }
return 0;
}

