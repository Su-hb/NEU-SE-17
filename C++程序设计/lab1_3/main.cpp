#include <iostream>

using namespace std;

int matrix[3][3];
void my_swap(int i,int j){
    int tmp = matrix[i][j];
    matrix[i][j] = matrix[j][i];
    matrix[j][i] = tmp;
}
void transposition(){
    for (int i = 0;i < 3;i ++){
        for(int j = 0;j < i;j++){
            my_swap(i,j);
        }
    }
}
int main()
{

    for (int i = 0;i < 3;i ++){
        for(int j = 0;j < 3;j++){
            cout<<"请输入"<<i+1<<"行"<<j+1<<"列"<<": ";
            cin >>matrix[i][j];
        }
    }
    transposition();
    cout<<"转置后："<<endl;
    for (int i = 0;i < 3;i ++){
        for(int j = 0;j < 3;j++){
            cout<<matrix[i][j]<<" ";
        }
        cout<<endl;
    }
    return 0;
}
