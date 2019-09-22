
import java.util.Scanner;

public class leetcode {

	public static final int N=100050;
	public static int n,x;
	public static long ans;
	public static int[] c=new int[N];


	public int maxSubArray(int[] nums) {
		if(nums.length < 1) {
			return 0;
		}
		else {
			for(int aaa = 0; aaa < nums.length;aaa++) {
				add(nums[aaa], 1);
			}

			int res = 0;
			for(int i = 1; i <= nums.length;i++) {
				for(int j = i+1; j <= nums.length;j++) {
					res = Math.max(res, sum(j)-sum(i));
				}
			}
			return res;
		}
	}
	public static void main(String[] args) {
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		leetcode le = new leetcode();
		System.out.println(le.maxSubArray(nums));
	}

	public static int lowbit(int x){
		return x&(-x);
	}
	public static void add(int x,int d){
		while(x<=n){
			c[x]+=d;
			x+=lowbit(x);
		}
	}
	public static int sum(int x){
		int res=0;
		while(x>0){
			res+=c[x];
			x-=lowbit(x);
		}
		return res;
	}

}
