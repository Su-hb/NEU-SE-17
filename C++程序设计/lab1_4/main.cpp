#include <iostream>

using namespace std;

void my_swap(int* x,int* y){
    int tmp = *x;
    *x = *y;
    *y = tmp;


}
void transposition(int** matrix){
    for (int i = 0;i < 3;i ++){
        for(int j = 0;j < i;j++){

            my_swap(&matrix[i][j],&matrix[j][i]);
        }
    }
}
int main()
{
    int** matrix = new int*[3];
    for(int i=0;i<3;i++)
        matrix[i] = new int[3];
    for(int i = 0;i <3;i++){
        for(int j = 0;j < 3;j++){
            cout<<"������"<<i+1<<"��"<<j+1<<"��"<<": ";
            cin >>matrix[i][j];
        }
    }
    transposition(matrix);
    cout<<"ת�ú�"<<endl;
    for (int i = 0;i < 3;i ++){
        for(int j = 0;j < 3;j++){
            cout<<matrix[i][j]<<" ";
        }
        cout<<endl;
    }
    return 0;
}
