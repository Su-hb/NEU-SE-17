package Tour;

import java.util.ArrayList;
import java.util.List;

import Tour.TourGraph;
import Tour.VNode;
import Tour.VInfo;

/*
 * @author:王大犇
 * 最小生成树的算法
 * 
 */

public class GiveRecommend {

private TourGraph graph;
	public static int  INF=32767;
	public GiveRecommend(TourGraph graph) {
		this.graph = graph;
	}
	
	/**
	 * 克鲁斯卡尔算法获取最小生成树
	 * 
	 * @return 边信息的集合
	 */
	public List<VInfo> kruskal(){
		int index = 0;
		int[] ends = new int[graph.getRNum()]; //用于保存已有最小生成树中每个顶点在该最小生成树中的终点
		VInfo[] results = new VInfo[graph.getRNum()]; //用于保存结果最小生成树的边
		
		//获取图中所有的边
		VInfo[] edges = getEdges();
		//将边按照权的大小进行排序（从小到大）
		sortEdges(edges);
		//对所有边进行遍历
		for(int i=0; i<graph.getRNum(); i++){
			int m = getEnd(ends, edges[i].getStart()); //获取该边起点在已有最小生成树中的终点
			int n = getEnd(ends, edges[i].getEnd()); //获取该边终点在已有最小生成树中的终点
			//如果m!=n，说明在已有最小生成树中添加该边不会形成回路
			if(m != n){
				ends[m] = n;
				results[index++] = edges[i];
			}
		}
		
		List<VInfo> result = new ArrayList<VInfo>();
		for(int i=0; i<index; i++){
			result.add(results[i]);
		}
		
		return result;
	}
	
	
	/**
	 * 获取所有边的信息
	 * 
	 * @return 边信息的数组
	 */
	private VInfo[] getEdges(){
		int index = 0; 
		VInfo[] edges;
		
		edges = new VInfo[graph.getRNum()];
		for(int i=0; i<graph.getAttractNum(); i++){
			VNode node = graph.getNodes().get(i).getFirst();
			while(node != null){
				if(node.getIndex() > i){
					edges[index++] = new VInfo(i, node.getIndex(), node.getDist());
				}
				node = node.getNext();
			}
		}
		
		return edges;
	}
	
	/**
	 * 按照边权的大小对边进行排序
	 * 
	 * @param edges 边信息的集合
	 */
	private void sortEdges(VInfo[] edges){
		for(int i=0; i<edges.length; i++){
			for(int j=i+1; j<edges.length; j++){
				if(edges[i].getWeight() > edges[j].getWeight()){
					VInfo tmp = edges[i];
					edges[i] = edges[j];
					edges[j] = tmp;
				}
			}
		}
	}
	
	
	/**
	 * 得到固定起始点在最小生成树中的终点位置
	 * 
	 * @param ends 最小生成树中每个顶点在该最小生成树中的终点
	 * @param arcIndex 起始点位置
	 * @return
	 */
	private int getEnd(int[] ends, int arcIndex){
		while(ends[arcIndex] != 0){
			arcIndex = ends[arcIndex];
		}
		
		return arcIndex;
	}
}
