package ViewPoint;

/**
 * @创建人
 * @创建时间 8:27 2018/12/27
 * @描述 景区信息管理系统
 */

/**
 * Java: prim算法生成最小生成树(邻接矩阵)
 *
 * @author skywang
 * @date 2014/04/23
 */

import java.io.IOException;
import java.util.Scanner;

public class MatrixUDG {

	private String[] mVexs;       // 顶点集合
	private int[][] mMatrix;    // 邻接矩阵
	private static final int INF = Integer.MAX_VALUE;   // 最大值

	/**
	 * @Author:
	 * @Description：创建图(用已提供的矩阵)
	 * 	 *
	 * 	 * 参数说明：
	 * 	 *     vexs  -- 顶点数组
	 * 	 *     matrix-- 矩阵(数据)
	 * @Date： 22:08 2019/1/14
	 */
	public MatrixUDG(String[] vexs, int[][] matrix) {

		// 初始化"顶点数"和"边数"
		int vlen = vexs.length;

		// 初始化"顶点"
		mVexs = new String[vlen];
		for (int i = 0; i < mVexs.length; i++)
			mVexs[i] = vexs[i];

		// 初始化"边"
		mMatrix = new int[vlen][vlen];
		for (int i = 0; i < vlen; i++)
			for (int j = 0; j < vlen; j++)
				mMatrix[i][j] = matrix[i][j];
	}

	/**
	 * @Author:
	 * @Description：返回ch位置
	 * @Date： 22:08 2019/1/14
	 */
	private int getPosition(String ch) {
		for(int i=0; i<mVexs.length; i++)
			if(mVexs[i].equals(ch))
				return i;
		return -1;
	}

	/**
	 * @Author:
	 * @Description：返回顶点v的第一个邻接顶点的索引，失败则返回-1
	 * @Date： 22:08 2019/1/14
	 */
	private int firstVertex(int v) {

		if (v<0 || v>(mVexs.length-1))
			return -1;

		for (int i = 0; i < mVexs.length; i++)
			if (mMatrix[v][i]!=0 && mMatrix[v][i]!=INF)
				return i;

		return -1;
	}

	/**
	 * @Author:
	 * @Description：返回顶点v相对于w的下一个邻接顶点的索引，失败则返回-1
	 * @Date： 22:08 2019/1/14
	 */
	private int nextVertex(int v, int w) {

		if (v<0 || v>(mVexs.length-1) || w<0 || w>(mVexs.length-1))
			return -1;

		for (int i = w + 1; i < mVexs.length; i++)
			if (mMatrix[v][i]!=0 && mMatrix[v][i]!=INF)
				return i;

		return -1;
	}

	/**
	 * @Author:
	 * @Description：深度优先搜索遍历图的递归实现
	 * @Date： 22:09 2019/1/14
	 */
	private void DFS(int i, boolean[] visited) {

		visited[i] = true;
		System.out.printf("%c ", mVexs[i]);
		// 遍历该顶点的所有邻接顶点。若是没有访问过，那么继续往下走
		for (int w = firstVertex(i); w >= 0; w = nextVertex(i, w)) {
			if (!visited[w])
				DFS(w, visited);
		}
	}

	/**
	 * @Author:
	 * @Description：深度优先搜索遍历图
	 * @Date： 22:09 2019/1/14
	 */
	public void DFS() {
		boolean[] visited = new boolean[mVexs.length];       // 顶点访问标记

		// 初始化所有顶点都没有被访问
		for (int i = 0; i < mVexs.length; i++)
			visited[i] = false;

		System.out.printf("DFS: ");
		for (int i = 0; i < mVexs.length; i++) {
			if (!visited[i])
				DFS(i, visited);
		}
		System.out.printf("\n");
	}

	/**
	 * @Author:
	 * @Description：广度优先搜索（类似于树的层次遍历）
	 * @Date： 22:09 2019/1/14
	 */
	public void BFS() {
		int head = 0;
		int rear = 0;
		int[] queue = new int[mVexs.length];            // 辅组队列
		boolean[] visited = new boolean[mVexs.length];  // 顶点访问标记

		for (int i = 0; i < mVexs.length; i++)
			visited[i] = false;

		System.out.printf("BFS: ");
		for (int i = 0; i < mVexs.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				System.out.printf("%c ", mVexs[i]);
				queue[rear++] = i;  // 入队列
			}

			while (head != rear) {
				int j = queue[head++];  // 出队列
				for (int k = firstVertex(j); k >= 0; k = nextVertex(j, k)) { //k是为访问的邻接顶点
					if (!visited[k]) {
						visited[k] = true;
						System.out.printf("%c ", mVexs[k]);
						queue[rear++] = k;
					}
				}
			}
		}
		System.out.printf("\n");
	}

	/**
	 * @Author:
	 * @Description：打印矩阵队列图
	 * @Date： 22:09 2019/1/14
	 */
	public void print() {
		System.out.printf("Martix Graph:\n");
		for (int i = 0; i < mVexs.length; i++) {
			for (int j = 0; j < mVexs.length; j++)
				System.out.printf("%10d ", mMatrix[i][j]);
			System.out.printf("\n");
		}
	}

	/**
	 * @Author:
	 * @Description：prim最小生成树
	 * 	 *
	 * 	 * 参数说明：
	 * 	 *   start -- 从图中的第start个元素开始，生成最小树
	 * @Date： 22:09 2019/1/14
	 */
	public void primNo(int start) {
		int num = mVexs.length;         // 顶点个数
		int index=0;                    // prim最小树的索引，即prims数组的索引
		String[] prims  = new String[num];  // prim最小树的结果数组
		int[] weights = new int[num];   // 顶点间边的权值

		// prim最小生成树中第一个数是"图中第start个顶点"，因为是从start开始的。
		prims[index++] = mVexs[start];

		// 初始化"顶点的权值数组"，
		// 将每个顶点的权值初始化为"第start个顶点"到"该顶点"的权值。
		for (int i = 0; i < num; i++ )
			weights[i] = mMatrix[start][i];
		// 将第start个顶点的权值初始化为0。
		// 可以理解为"第start个顶点到它自身的距离为0"。
		weights[start] = 0;

		for (int i = 0; i < num; i++) {
			// 由于从start开始的，因此不需要再对第start个顶点进行处理。
			if(start == i)
				continue;

			int j = 0;
			int k = 0;
			int min = INF;
			// 在未被加入到最小生成树的顶点中，找出权值最小的顶点。
			while (j < num) {
				// 若weights[j]=0，意味着"第j个节点已经被排序过"(或者说已经加入了最小生成树中)。
				if (weights[j] != 0 && weights[j] < min) {
					min = weights[j];
					k = j;
				}
				j++;
			}

			// 经过上面的处理后，在未被加入到最小生成树的顶点中，权值最小的顶点是第k个顶点。
			// 将第k个顶点加入到最小生成树的结果数组中
			prims[index++] = mVexs[k];
			// 将"第k个顶点的权值"标记为0，意味着第k个顶点已经排序过了(或者说已经加入了最小树结果中)。
			weights[k] = 0;
			// 当第k个顶点被加入到最小生成树的结果数组中之后，更新其它顶点的权值。
			for (j = 0 ; j < num; j++) {
				// 当第j个节点没有被处理，并且需要更新时才被更新。
				if (weights[j] != 0 && mMatrix[k][j] < weights[j])
					weights[j] = mMatrix[k][j];
			}

		}


		// 计算最小生成树的权值
		int sum = 0;
		for (int i = 1; i < index; i++) {
			int min = INF;
			// 获取prims[i]在mMatrix中的位置
			int n = getPosition(prims[i]);
			// 在vexs[0...i]中，找出到j的权值最小的顶点。
			for (int j = 0; j < i; j++) {
				int m = getPosition(prims[j]);
				if (mMatrix[m][n]<min)
					min = mMatrix[m][n];
			}
			sum += min;
		}
		//sum+=mMatrix[prims.length-1][start];
		// 打印最小生成树
		System.out.printf("总距离=%d: ", sum);
		for (int i = 0; i < index-1; i++)
			System.out.printf("%s->", prims[i]);
		System.out.printf("%s", prims[index-1]);
		//System.out.printf(mVexs[start]+"\n");
	}
	/**
	 * @Author:
	 * @Description：prim最小生成树
	 * 	 *　　邻接矩阵:O(v)
	 * 	 * 参数说明：
	 * 	 *   start -- 从图中的第start个元素开始，生成最小树
	 * @Date： 22:09 2019/1/14
	 */
	public void primYes(int start) {
		int num = mVexs.length;         // 顶点个数
		int index=0;                    // prim最小树的索引，即prims数组的索引
		String[] prims  = new String[num];  // prim最小树的结果数组
		int[] weights = new int[num];   // 顶点间边的权值

		// prim最小生成树中第一个数是"图中第start个顶点"，因为是从start开始的。
		prims[index++] = mVexs[start];

		// 初始化"顶点的权值数组"，
		// 将每个顶点的权值初始化为"第start个顶点"到"该顶点"的权值。
		for (int i = 0; i < num; i++ )
			weights[i] = mMatrix[start][i];
		// 将第start个顶点的权值初始化为0。
		// 可以理解为"第start个顶点到它自身的距离为0"。
		weights[start] = 0;

		for (int i = 0; i < num; i++) {
			// 由于从start开始的，因此不需要再对第start个顶点进行处理。
			if(start == i)
				continue;

			int j = 0;
			int k = 0;
			int min = INF;
			// 在未被加入到最小生成树的顶点中，找出权值最小的顶点。
			while (j < num) {
				// 若weights[j]=0，意味着"第j个节点已经被排序过"(或者说已经加入了最小生成树中)。
				if (weights[j] != 0 && weights[j] < min) {
					min = weights[j];
					k = j;
				}
				j++;
			}

			// 经过上面的处理后，在未被加入到最小生成树的顶点中，权值最小的顶点是第k个顶点。
			// 将第k个顶点加入到最小生成树的结果数组中
			prims[index++] = mVexs[k];
			// 将"第k个顶点的权值"标记为0，意味着第k个顶点已经排序过了(或者说已经加入了最小树结果中)。
			weights[k] = 0;
			// 当第k个顶点被加入到最小生成树的结果数组中之后，更新其它顶点的权值。
			for (j = 0 ; j < num; j++) {
				// 当第j个节点没有被处理，并且需要更新时才被更新。
				if (weights[j] != 0 && mMatrix[k][j] < weights[j])
					weights[j] = mMatrix[k][j];
			}

		}


		// 计算最小生成树的权值
		int sum = 0;
		for (int i = 1; i < index; i++) {
			int min = INF;
			// 获取prims[i]在mMatrix中的位置
			int n = getPosition(prims[i]);
			// 在vexs[0...i]中，找出到j的权值最小的顶点。
			for (int j = 0; j < i; j++) {
				int m = getPosition(prims[j]);
				if (mMatrix[m][n]<min)
					min = mMatrix[m][n];
			}
			sum += min;
		}
		sum+=mMatrix[prims.length-1][start];
		// 打印最小生成树
		if (sum>10000||sum<=0){
			System.out.println("图中无回路");
		}else{
		System.out.printf("总距离=%d: ", sum);
		for (int i = 0; i < index-1; i++)
			System.out.printf("%s->", prims[i]);
		System.out.printf("%s->", prims[index-1]);
		System.out.printf(mVexs[start]+"\n");}
		//增加最后节点
	}

	/**
	 * @Author:
	 * @Description：测试
	 * @Date： 22:10 2019/1/14
	 */
	public static void main(String[] args) {
		String[] vexs = {"A", "B", "C", "D", "E", "F", "G"};
		int matrix[][] = {
				/*A*//*B*//*C*//*D*//*E*//*F*//*G*/
				/*A*/ {   0,  12, INF, INF, INF,  16,  14},
				/*B*/ {  12,   0,  10, INF, INF,   7, INF},
				/*C*/ { INF,  10,   0,   3,   5,   6, INF},
				/*D*/ { INF, INF,   3,   0,   4, INF, INF},
				/*E*/ { INF, INF,   5,   4,   0,   2,   8},
				/*F*/ {  16,   7,   6, INF,   2,   0,   9},
				/*G*/ {  14, INF, INF, INF,   8,   9,   0}};
		MatrixUDG pG;

		// 自定义"图"(输入矩阵队列)
		//pG = new MatrixUDG();
		// 采用已有的"图"
		pG = new MatrixUDG(vexs, matrix);

		pG.print();   // 打印图
		//.DFS();     // 深度优先遍历
		//pG.BFS();     // 广度优先遍历
		pG.primNo(1);   // prim算法生成最小生成树
	}
}
