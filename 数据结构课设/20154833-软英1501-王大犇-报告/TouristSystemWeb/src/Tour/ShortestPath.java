package Tour;

import java.util.ArrayList;
import java.util.List;

import Tour.TourGraph;
import Tour.VNode;

/*
 *@author:�����
 * ���·�����㷨
 * 
 */

public class ShortestPath {
	public static int INF = 32767;
	
	private TourGraph graph;
	private int dis[]; //��������ʼ��֮�����̾���
	private int vis[]; //�ж϶����Ƿ񱻷���
	private int fath[]; //������λ��
	private int startIndex; //��ʼ��
	private int desIndex; //Ŀ���
	
	public ShortestPath(TourGraph graph){
		this.graph=graph;
		dis=new int[graph.getAttractNum()];//��ʼ����̾���
		vis=new int[graph.getAttractNum()];//��ʼ���Ƿ����ֵ
		fath=new int[graph.getAttractNum()];//��ʼ�����ڵ�λ�ã���Ϊ��
	}
	
	
	/**
	 * �㷨��Dijkstra,�������ڵ�֮����̵�·������̾���
	 * @param start index
	 * @param des index
	 */
	public void Dijkstra(String start,String des){//���������յ�ľ�������
		startIndex=getPos(start);
		desIndex=getPos(des);
		
		//��ʼ����̾�������Ϊ���ֵ
		for(int i=0;i<graph.getAttractNum();i++){
			dis[i]=(i==startIndex?0:INF);//��ͬ��������ΪMAX
		}
		
		for(int i=0;i<graph.getAttractNum();i++){
			int minPos=-1;
			int m=INF;
			for(int j=0;j<graph.getAttractNum();j++){
				if(vis[j]==0 && dis[j]<m){
					m=dis[minPos=j];
				}
			}
			vis[minPos]=1;//��ʾ�Լ�������
			for(int j=0;j<graph.getAttractNum();j++){
				if(vis[j]==0 && dis[minPos]+getLength(minPos,j)<dis[j]){//�Ƚϸ����ڵ�֮��ľ��룬ȡ���
					dis[j]=dis[minPos]+getLength(minPos,j);//����һ�������ϼ����µı���������̾���
					fath[j]=minPos;//��Ӹ��ڵ㣬���ƶ����ڵ㵽��һ���ڵ�
				}
			}
		}
	}
	
	
	//���ݾ������Ʒ��ؾ���λ��
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
	 * ������ڽڵ㵽�����ڵ�ľ���
	 * @return ����֮��ĳ���
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
	
	//������·���ﾰ���λ���б�
	public List<Integer> outputShortestPath(){
		List<Integer> path = new ArrayList<Integer>();
		//�������·������
		
		path.add(dis[desIndex]);
		//����������·���н���λ��
		int t = desIndex;
		while(t != startIndex){
			path.add(t);
			t = fath[t];
		}
		path.add(t);
		
		return path;
	}

}
