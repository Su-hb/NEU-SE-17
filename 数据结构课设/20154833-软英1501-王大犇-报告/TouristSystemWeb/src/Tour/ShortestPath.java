package Tour;

import java.util.ArrayList;
import java.util.List;

import Tour.TourGraph;
import Tour.VNode;

/*
 *@author:王大犇
 * 最短路径的算法
 * 
 */

public class ShortestPath {
	public static int INF = 32767;
	
	private TourGraph graph;
	private int dis[]; //顶点与起始点之间的最短距离
	private int vis[]; //判断顶点是否被访问
	private int fath[]; //父结点的位置
	private int startIndex; //起始点
	private int desIndex; //目标点
	
	public ShortestPath(TourGraph graph){
		this.graph=graph;
		dis=new int[graph.getAttractNum()];//初始化最短距离
		vis=new int[graph.getAttractNum()];//初始化是否访问值
		fath=new int[graph.getAttractNum()];//初始化父节点位置，都为空
	}
	
	
	/**
	 * 算法：Dijkstra,求两个节点之间最短的路径和最短距离
	 * @param start index
	 * @param des index
	 */
	public void Dijkstra(String start,String des){//输入起点和终点的景点名称
		startIndex=getPos(start);
		desIndex=getPos(des);
		
		//初始化最短距离数组为最大值
		for(int i=0;i<graph.getAttractNum();i++){
			dis[i]=(i==startIndex?0:INF);//不同景点间距离为MAX
		}
		
		for(int i=0;i<graph.getAttractNum();i++){
			int minPos=-1;
			int m=INF;
			for(int j=0;j<graph.getAttractNum();j++){
				if(vis[j]==0 && dis[j]<m){
					m=dis[minPos=j];
				}
			}
			vis[minPos]=1;//表示以及被访问
			for(int j=0;j<graph.getAttractNum();j++){
				if(vis[j]==0 && dis[minPos]+getLength(minPos,j)<dis[j]){//比较各个节点之间的距离，取最短
					dis[j]=dis[minPos]+getLength(minPos,j);//在上一个距离上加上新的边来计算最短距离
					fath[j]=minPos;//添加父节点，并移动父节点到下一个节点
				}
			}
		}
	}
	
	
	//根据景点名称返回景点位置
	public int getPos(String name){
		int pos=-1;
		for(int i=0;i<graph.getAttractNum();i++){
			if(name.equals(graph.getNodes().get(i).getName())){
				pos=i;
				break;
			}
		}
		return pos;
	}
	
	/**
	 * 输出现在节点到其他节点的距离
	 * @return 两点之间的长度
	 */
	public int getLength(int fromIndex,int toIndex){
		VNode v =graph.getNodes().get(fromIndex).getFirst();
		int length=INF;
		while(v!=null){
			if(v.getIndex()==toIndex){
				length=v.getDist();
				break;
			}
			v=v.getNext();
		}
		return length;
	}
	
	//输出最短路径里景点的位置列表
	public List<Integer> outputShortestPath(){
		List<Integer> path = new ArrayList<Integer>();
		//放入最短路径长度
		
		path.add(dis[desIndex]);
		//逆序放入最短路径中结点的位置
		int t = desIndex;
		while(t != startIndex){
			path.add(t);
			t = fath[t];
		}
		path.add(t);
		
		return path;
	}

}
