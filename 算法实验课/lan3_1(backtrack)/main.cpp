#include <iostream>
#include <climits>
#include <fstream>
using namespace std;
#define N 25
int d[N][N];
int x[N],best[N];
int cc,n;
int bestc = INT_MAX;
int sum;
void backtrack(int t)
{
	if(t>n)
	{
		if(d[x[n]][1] > 0 && cc+d[x[n]][1] < bestc || bestc==0)
		{
			bestc=cc+d[x[n]][1];
			sum++;
			for (int c = 1; c <= n; c++) {
				best[c] = x[c];
			}
		}
	}
	else
	{
		for(int i=t;i<=n;i++)
		{
			if(d[x[t-1]][x[i]] > 0 && cc + d[x[t-1]][x[i]] < bestc || bestc==0)
			{
				swap(x[t],x[i]);
				cc+=d[x[t-1]][x[t]];
				backtrack(t+1);
				cc-=d[x[t-1]][x[t]];
				swap(x[t],x[i]);
			}
		}
	}
}

int main()
{
    int i,j;
    cout<<"输入节点数"<<endl;
    cin >> n;
    sum = 0;

    ifstream in("input.txt");
    if( ! in.is_open() ) cout <<"打开文件失败"<<endl;
    cout<<"邻接矩阵为："<<endl;
    for(i = 1;i <= n ;i ++){
        for(j = 1; j <= n;j++){
            in >> d[i][j];
            cout<<d[i][j]<<" ";
        }
        cout<<endl;
    }

    for(i = 1;i <=n;i++){
        x[i] = i;
    }
    backtrack(2);
    if( sum == 0){
        cout<<"No Solution"<<endl;
    }
    else{
		ofstream out("output.txt");
        out <<"结果:"<< bestc << endl;
		out << "顺序为" << endl;
		for (int c = 1; c <= n; c++) {
			out << best[c] << " ";
		}
		out.close();
    }


    return 0;
}
