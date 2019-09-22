package ViewPoint;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @创建人
 * @创建时间 2018/12/11
 * @描述 数据结构
 */
public class SortAtt {
	private static  easyView[] needToSort;

	/**
	 * @Author:
	 * @Description：内部静态类
	 * @Date： 16:57 2019/1/14
	 */
	static class easyView{
		String name;
		int population;

		public easyView(String name, int population) {
			this.name = name;
			this.population = population;
		}
	}

	/**
	 * @Author:
	 * @Description：初始化
	 * @Date： 16:58 2019/1/14
	 */
	public static easyView[] Sortinit(ArrayList<Attractions> adjList){
		int len=  adjList.size() ;
		needToSort = new easyView[len];
		for (int i = 0; i <adjList.size() ; i++) {
			if (adjList.get(i).getAttractionsWelcome() <0){
				System.out.println("错误！景点热度存在空值");
				return null;
			}
			easyView tmpEasyView = new easyView(adjList.get(i).getSpotName(),adjList.get(i).getAttractionsWelcome());
			needToSort[i] = tmpEasyView;
		}
		return needToSort;
	}

	/**
	 * @Author:
	 * @Description：set（）&&get（）
	 * @Date： 16:59 2019/1/14
	 */
	public easyView[] getNeedToSort() {
		return needToSort;
	}

	public void setNeedToSort(easyView[] needToSort) {
		this.needToSort = needToSort;
	}

	/**
	 * @Author:
	 * @Description：插入排序   直接插入排序 时间复杂度 n**2 空间复杂度 1 稳定排序
	 * @Date： 11:46 2018/12/16
	 */
	public static  void  InsertSort(easyView[] A){
		int n = A.length;
		int i,j;
		for(i = 2;i <n;i++){
			if(A[i].population <A[i-1].population){
				A[0] = A[i];
				for(j = i-1;A[0].population < A[j].population;--j){
					A[j+1] = A[j];
				}
				A[j+1] = A[0];
			}
		}
	}

	/**
	 * @Author:
	 * @Description：折半插入排序 时间复杂度 n**2 稳定排序
	 * @Date： 11:46 2018/12/16
	 */
	public static void  halfInsertSort(easyView[] A){
		int n = A.length;
		int i,j,low,high,mid= 0;
		for( i =2;i < n;i++){
			A[0] = A[i];
			low = 1;high = i -1;
			//二分查找过程
			while (low <= high){
				mid = (low+high)/2;
				if(A[mid] == A[0] ) break;
				else if(A[mid].population > A[0].population ) high = mid - 1;
				else low = mid+1;
			}
			for(j = i -1;j>=mid;j--){
				A[j+1] = A[j];
			}
			A[high+1] = A[0];
		}
	}

	/**
	 * @Author:
	 * @Description： 希尔排序  时间复杂度 n**2 空间复杂度 1
	 * @Date： 11:46 2018/12/16
	 */
	public static void  ShellSort(easyView[] arr){
		//已知的最好步长序列是由Sedgewick提出的(1, 5, 19, 41, 109,...)
		// 用这样步长序列的希尔排序比插入排序要快，甚至在小数组中比快速排序和堆排序还快
		// 但是在涉及大量数据时希尔排序还是比快速排序慢。
		//另一个在大数组中表现优异的步长序列是（斐波那契数列除去0和1将剩余的数以黄金分割比的两倍的幂
		// 进行运算得到的数列）：(1, 9, 34, 182, 836, 4025, 19001, 90358, 428481,
		// 2034035, 9651787, 45806244, 217378076, 1031612713,…)
		//增量gap，并逐步缩小增量
		for(int gap=arr.length/2;gap>0;gap/=2){
			//从第gap个元素，逐个对其所在组进行直接插入排序操作
			for(int i=gap;i<arr.length;i++){
				int j = i;
				int temp = arr[j].population ;
				easyView tempeasyView = arr[j];

				if(arr[j].population <arr[j-gap].population ){
					while(j-gap>=0 && temp<arr[j-gap].population ){
						//移动法
						arr[j] = arr[j-gap];
						j-=gap;
					}
					arr[j] = tempeasyView;
				}

			}

		}
	}

	/**
	 * @Author:
	 * @Description： 冒泡排序 时间复杂度n**2 空间复杂度 1
	 * @Date： 11:48 2018/12/16
	 */
	public  static  void BubbleSort(easyView[] A){
		int length = A.length;
		for(int i = 0;i<length-1;i++){
			for(int j = length - 1;j > i ;j--){
				if(A[j-1].population>A[j].population){
					easyView tmp = A[j];
					A[j] = A[j-1];
					A[j-1] = tmp;
				}
			}

		}
	}

	/**
	 * @Author:
	 * @Description：快速排序 时间复杂度 n*logn 空间复杂度 平均情况 log2n 最坏情况n
	 * @Date： 22:17 2018/12/16
	 */
	public static int Partition(easyView[] A,int low,int high){
		//划分算法
		int pivot = A[low].population;
		easyView pivotEasyView = A[low];
		while (low < high){
			while (low < high &&A[high].population >= pivot) --high;
			A[low] = A[high];
			while (low < high && A[low].population <= pivot) ++low;
			A[high] = A[low];
		}
		A[low] = pivotEasyView;
		return  low;
	}
	public  static void quickSort(easyView[] A,int low,int high){
		if(low > high){
			return;
		}
		int pivotpos = Partition(A, low, high);
		quickSort(A,low,pivotpos-1);
		quickSort(A,pivotpos+1,high);
	}

	/**
	 * @Author:
	 * @Description：选择排序 时间复杂度 n**2 空间复杂度 1
	 * @Date： 20:14 2018/12/17
	 */
	public static void SeleteSort(easyView[] A){
		int n = A.length;
		for(int i = 0;i < n;i++){
			int min = i;
			for(int j = i+1;j<n;j++){
				if(A[j].population < A[min].population) min = j;
			}
			if (min != i){
				easyView tmp = A[i];
				A[i] = A[min];
				A[min] = tmp;
			}
		}
	}

	/**
	 * @Author:
	 * @Description：堆排序 时间复杂度 n*logn 空间复杂度 n
	 * @Date： 20:24 2018/12/17
	 */
	public static void HeapSort(easyView []arr){
		//1.构建小顶堆
		for(int i=arr.length/2-1;i>=0;i--){
			//从第一个非叶子结点从下至上，从右至左调整结构
			adjustHeap(arr,i,arr.length);
		}
		//2.调整堆结构+交换堆顶元素与末尾元素

		for(int j=arr.length-1;j>0;j--){
			swap(arr,0,j);//将堆顶元素与末尾元素进行交换
			adjustHeap(arr,0,j);//重新对堆进行调整
		}

	}
	/**
	 * 调整小顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
	 * @param arr
	 * @param i
	 * @param length
	 */
	public static void adjustHeap(easyView []arr,int i,int length){
		easyView temp = arr[i];//先取出当前元素i
		for(int k=i*2+1;k<length;k=k*2+1){//从i结点的左子结点开始，也就是2i+1处开始
			if(k+1<length && arr[k].population<arr[k+1].population){//如果左子结点小于右子结点，k指向右子结点
				k++;
			}
			if(arr[k].population >temp.population){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
				arr[i] = arr[k];
				i = k;
			}else{
				break;
			}
		}
		arr[i] = temp;//将temp值放到最终的位置
	}
	/**
	 * 交换元素
	 * @param arr
	 * @param a
	 * @param b
	 */
	public static void swap(easyView []arr,int a ,int b){
		easyView temp=arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	/**
	 * @Author:
	 * @Description：归并排序 时间复杂度 nlogn 空间复杂度 n
	 * @Date： 21:25 2018/12/17
	 */
	public static void Mergesort(easyView []arr){
		easyView[] temp = new easyView[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
		mmmsort(arr,0,arr.length-1,temp);
	}
	private static void mmmsort(easyView[] arr,int left,int right,easyView []temp){
		if(left<right){
			int mid = (left+right)/2;
			mmmsort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
			mmmsort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
			merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
		}
	}
	private static void merge(easyView[] arr,int left,int mid,int right,easyView[] temp){
		int i = left;//左序列指针
		int j = mid+1;//右序列指针
		int t = 0;//临时数组指针
		while (i<=mid && j<=right){
			if(arr[i].population<=arr[j].population){
				temp[t++] = arr[i++];
			}else {
				temp[t++] = arr[j++];
			}
		}
		while(i<=mid){//将左边剩余元素填充进temp中
			temp[t++] = arr[i++];
		}
		while(j<=right){//将右序列剩余元素填充进temp中
			temp[t++] = arr[j++];
		}
		t = 0;
		//将temp中的元素全部拷贝到原数组中
		while(left <= right){
			arr[left++] = temp[t++];
		}
	}

	/**
	 * @Author:
	 * @Description：输出结果
	 * @Date： 14:42 2019/1/15
	 */
	public static void display(ArrayList<Attractions> attractionsArrayList){
		needToSort = Sortinit(attractionsArrayList);
		if (needToSort==null)return;
		HeapSort(needToSort);
		System.out.println("景点名称"+" "+"热度");
		for (int i = needToSort.length-1; i > 0; i--) {
			System.out.println(needToSort[i].name+" "+needToSort[i].population);
		}
	}
	/**
	 * @Author:
	 * @Description：初始化根据边数的结果
	 * @Date： 23:08 2019/1/16
	 */
	public static easyView[] SortEdgeinit(ArrayList<Attractions> adjList){
		int len=  adjList.size() ;
		needToSort = new easyView[len];
		for (int i = 0; i <adjList.size() ; i++) {

			easyView tmpEasyView = new easyView(adjList.get(i).getSpotName(),adjList.get(i).getEdgeArrayList().size());
			needToSort[i] = tmpEasyView;
		}
		return needToSort;
	}
	/**
	 * @Author:
	 * @Description：输出根据边数的结果
	 * @Date： 14:42 2019/1/15
	 */
	public static void displayEdges(ArrayList<Attractions> attractionsArrayList){
		needToSort = SortEdgeinit(attractionsArrayList);
		if (needToSort==null)return;
		HeapSort(needToSort);
		System.out.println("景点名称"+" "+"边数");
		for (int i = needToSort.length-1; i > 0; i--) {
			System.out.println(needToSort[i].name+" "+needToSort[i].population);
		}
	}
	/**
	 * @Author:
	 * @Description：测试代码
	 * @Date： 22:55 2019/1/14
	 */
	public static void main(String[] args) {
		needToSort = new easyView[6];
		needToSort[0] = new easyView("z",Integer.MIN_VALUE);
		needToSort[1] = new easyView("a",9);
		needToSort[2] = new easyView("t",1);
		needToSort[3] = new easyView("c",6);
		needToSort[4] = new easyView("y",7);
		needToSort[5] = new easyView("q",3);
		Mergesort(needToSort);
		for (int i = 1; i <= 5; i++) {
			System.out.println(needToSort[i].name+" "+needToSort[i].population);
		}
	}
}
