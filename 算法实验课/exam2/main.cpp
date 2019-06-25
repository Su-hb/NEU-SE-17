#include <iostream>
using namespace std;
int main()
{
    int s[5]={5,3,7,1,9};
    int num = 5;


    int dp[num+1][num+1];
    int sum[num+1];//记录合并成本
    sum[0] = 0;

    for(int i = 1;i <= num;i ++){
        for(int j = 1;j <= num;j++){
            dp[i][j] = 1e9;
        }
    }
    for(int i =1; i <= num; i++){
			sum[i] = sum[i-1]+s[i];//前i个餐厅合并的成本
			dp[i][i] = 0;
    }

    for(int i = 1;i < num;i ++){
        for(int j = 1;j <= num;j++){
            for(int k = i;k <= j;k++){
                dp[i][j] = min(dp[i][j],dp[i][k] + dp[k+1][j] + sum[j] - sum[i-1]);

            }
        }
    }
    for(int i = 1;i <= num;i ++){
        for(int j = 1;j <= num;j++){
            cout<<dp[i][j]<<" ";
        }
        cout<<endl;
    }
    return 0;
}
