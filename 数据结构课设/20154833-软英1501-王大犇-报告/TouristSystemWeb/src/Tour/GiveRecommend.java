package Tour;

import java.util.ArrayList;
import java.util.List;

import Tour.TourGraph;
import Tour.VNode;
import Tour.VInfo;

/*
 * @author:�����
 * ��С���������㷨
 * 
 */

public class GiveRecommend {

private TourGraph graph;
	public static int  INF=32767;
	public GiveRecommend(TourGraph graph) {
		this.graph = graph;
	}
	
	/**
	 * ��³˹�����㷨��ȡ��С������
	 * 
	 * @return ����Ϣ�ļ���
	 */
	public List<VInfo> kruskal(){
		int index = 0;
		int[] ends = new int[graph.getRNum()]; //���ڱ���������С��������ÿ�������ڸ���С�������е��յ�
		VInfo[] results = new VInfo[graph.getRNum()]; //���ڱ�������С�������ı�
		
		//��ȡͼ�����еı�
		VInfo[] edges = getEdges();
		//���߰���Ȩ�Ĵ�С�������򣨴�С����
		sortEdges(edges);
		//�����б߽��б���
		for(int i=0; i<graph.getRNum(); i++){
			int m = getEnd(ends, edges[i].getStart()); //��ȡ�ñ������������С�������е��յ�
			int n = getEnd(ends, edges[i].getEnd()); //��ȡ�ñ��յ���������С�������е��յ�
			//���m!=n��˵����������С����������Ӹñ߲����γɻ�·
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
	 * ��ȡ���бߵ���Ϣ
	 * 
	 * @return ����Ϣ������
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
	 * ���ձ�Ȩ�Ĵ�С�Ա߽�������
	 * 
	 * @param edges ����Ϣ�ļ���
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
	 * �õ��̶���ʼ������С�������е��յ�λ��
	 * 
	 * @param ends ��С��������ÿ�������ڸ���С�������е��յ�
	 * @param arcIndex ��ʼ��λ��
	 * @return
	 */
	private int getEnd(int[] ends, int arcIndex){
		while(ends[arcIndex] != 0){
			arcIndex = ends[arcIndex];
		}
		
		return arcIndex;
	}
}
