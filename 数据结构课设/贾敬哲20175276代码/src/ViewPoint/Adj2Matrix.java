package ViewPoint;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @创建人
 * @创建时间 15:31 2019/1/14
 * @描述 景区信息管理系统
 */
public class Adj2Matrix {
	private static final int MAXN = Integer.MAX_VALUE/2;//定义最大值，/2的原因是防止溢出

	/**
	 * @Author:
	 * @Description：打印邻接矩阵
	 * @Date： 20:16 2019/1/14
	 */
	public static void display(ArrayList<Attractions> attractionsArrayList,int[][] matrix){
		System.out.print("    ");
		for (Attractions aaa:attractionsArrayList
				) {
			System.out.print(aaa.getSpotName()+"\t");
		}
		System.out.println();
		int[][] aaa = Adj2Matrix.getMatrix(attractionsArrayList);
		for (int i = 0; i < aaa.length; i++) {
			System.out.print(attractionsArrayList.get(i).getSpotName()+"   ");
			for (int j = 0; j < aaa[0].length; j++) {
				if (aaa[i][j]>9999){
					System.out.print("-1    ");
					continue;
				}
				System.out.print(aaa[i][j]+"    ");
			}
			System.out.println();
		}
	}

	/**
	 * @Author:
	 * @Description：初始化邻接矩阵
	 * @Date： 20:17 2019/1/14
	 */
	public static void reset(int[][] matrix){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = MAXN;
			}
		}
	}

	/**
	 * @Author:
	 * @Description：邻接表转换成邻接矩阵
	 * @Date： 20:17 2019/1/14
	 */
	public static int[][] getMatrix(ArrayList<Attractions> adjList) {
		HashMap<String,Integer> name2Id = new HashMap<>();//name -> id
		int len = adjList.size();
		//设置邻接矩阵大小
		int[][] matrix = new int[len][len];
		//初始化
		reset(matrix);
		//实现name->id
		for (int i = 0; i <len; i++) {
			Attractions tmp = adjList.get(i);
			name2Id.put(tmp.getSpotName(),i);
		}
		//赋值
		for (int i = 0; i <len; i++) {
			Attractions tmp = adjList.get(i);
			matrix[i][i] = 0;
			for (Edge edge:tmp.getEdgeArrayList()
			) {
				matrix[i][name2Id.get(edge.to)] = edge.cost;
			}
		}
		return matrix;
	}
}
