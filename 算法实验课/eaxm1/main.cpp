#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int my_max(int a,int b,int c){
    return max(a,max(b,c));
}
int main()
{
    int p = 45 ;//经费
    int n = 5;//发票张数
    int w[n] ={20,32,47,10,9};//发票
    int v = 45; //单张发票上线

    sort(w,w+n);
    int length;
    for(int i = n-1;i >= 0;i--){
        if(w[i] > v){
            length = i;
        }
    }

    int dp[length+1][p+1];

    for(int i = 0;i <= length;i++){
        for(int j = 0;j <=p;j++){
            dp[i][j] = 0;
        }
    }

    for(int i = 1;i <= length;i++){

        for(int j = 1;j <=p;j++){
                if( j < w[i-1]){
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    dp[i][j] = max(dp[i-1][j-w[i-1]]+w[i-1],dp[i-1][j]);
                }


        }
    }
    for(int i = 0;i <= length;i++){
        for(int j = 0;j <=p;j++){

            cout<< dp[i][j]<<" ";
        }
        cout<<endl;
    }
    return 0;
}
