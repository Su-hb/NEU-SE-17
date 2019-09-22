package Tour;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Controller.Tourist;
import Tour.TourGraph;
import Tour.AttractNode;
import Structure.Stack;
import Tour.VNode;

/*
 * @author �����
 * ����·��ͼ
 * DFS�㷨
 */

public class TourMap {
	private TourGraph graph;
	private TourGraph directGraph;
	private boolean[] visited;
	private List<Integer> tourIndexList;
	private int startIndex;
	
	public TourMap(TourGraph graph){
		this.graph=graph;
		directGraph=new TourGraph(graph.getAttractNum());
		visited=new boolean[graph.getAttractNum()];
		tourIndexList=new ArrayList<Integer>();
	}
	
	
	/**
	 * ��ȱ����㷨
	 * ѡ����ʼ�� start
	 */
	public List<Integer> DFS(String start){
		Stack<Integer> dfsNodes=new Stack<Integer>(Integer.class,graph.getAttractNum());
		
		startIndex=getPos(start);
		
		dfsNodes.push(startIndex);//�õ���ʼλ��
		System.out.println(startIndex);
		while(!dfsNodes.isEmpty() && !isAllVisited()){//�㷨��������������-���е㶼��������
			int attractNodeNum=dfsNodes.peek();
			System.out.println(attractNodeNum);
			visited[attractNodeNum]=true;//
			VNode vNode=graph.getNodes().get(attractNodeNum).getFirst();
			while((vNode!=null)&&(vNode.getIndex()!=-1)){//�ж���һ���߽ڵ��Ƿ�Ϊ��
				//System.out.println(vNode.getIndex());
				if(!visited[vNode.getIndex()]){
					dfsNodes.push(vNode.getIndex());
					break;
				}
				vNode=vNode.getNext();
			}
			if((vNode==null)||(vNode.getIndex()==-1)){//�����һ���߽���ǿ�
				dfsNodes.pop();
			}
			tourIndexList.add(attractNodeNum);
		}
		return tourIndexList;
	}
	
	//�Լ�д�Ľ���ȱ����͵Ͻ���˹���㷨�νӣ�������ˣ�
	public List<Integer> Prim(String start,String end){
		//Stack<Integer> primNodes=new Stack<Integer>(Integer.class,graph.getAttractNum());
		List<Integer> tourIndexList = DFS(start);
		int last=tourIndexList.get(tourIndexList.size()-1);
		System.out.println("last"+last);
		String start2=getName(last);
		System.out.println("last name"+start2);
		
		ShortestPath shortestPath = new ShortestPath(Tourist.getGraph());
		shortestPath.Dijkstra(start2, end);
		List<Integer> result = shortestPath.outputShortestPath();
		
		List<Integer> path = new ArrayList<Integer>();
		for(int i=result.size()-1; i>0; i--){
			//System.out.println(result.get(i));
			path.add(result.get(i));
		}
		
		for(int i=1;i<path.size();i++){
			
			tourIndexList.add(path.get(i));
		}
		
		return tourIndexList;
	}
	
	//�õ����������Ƶ�λ��
	public int getPos(String name){
		int pos = -1;
		for(int i=0; i<graph.getAttractNum(); i++){
			if(name.equals(graph.getNodes().get(i).getName())){
				pos = i;
				break;
			}
		}
		return pos;
	}
	
	public String getName(int pos){
		String name=null;
		for(int i=0;i<graph.getAttractNum();i++){
			if(i==pos){
				name=graph.getNodes().get(i).getName();
			}
		}
		return name;
	}
	
	//�ж��Ƿ����о��㶼������
	public boolean isAllVisited(){
		for(boolean visit:visited){
			if(!visit){
				return false;
			}
		}
		return true;
	}
	
	
	/**
     * ����Floyd�㷨��������֮�����̾���
     * ûд�ɹ�
     * @return ���·��
     */
    public String Floyd() {

        int distance[][] = new int[graph.getAttractNum()][graph.getAttractNum()], path[][] = new int[graph.getAttractNum()][graph.getAttractNum()];

        for (int i = 0; i < graph.getAttractNum(); i++) {
            for (int j = 0; j < graph.getAttractNum(); j++) {
                distance[i][j] = getLength(i, j);
                path[i][j] = j;
            }
        }

        for (int k = 0; k < graph.getAttractNum(); k++) {
            for (int i = 0; i < graph.getAttractNum(); i++) {
                for (int j = 0; j < graph.getAttractNum(); j++) {
                    int tmp_value;
                    if(distance[i][k] == 32767 || distance[k][j]==32767)
                        tmp_value = 32767;
                    else tmp_value = distance[i][k] + distance[k][j];
                    if (distance[i][j] > tmp_value) {
                        distance[i][j] = tmp_value;
                        path[i][j] = path[i][k];
                    }
                }
            }
        }
        
        //���ɽ������
        String floydString = "";
        for (int i = 0; i < 32767; i++) {
            for (int j = 0; j < 32767; j++)
                floydString += distance[i][j] + " ";
            floydString += "\n";
        }
        
        return floydString;
    }
    
    /**
	 * ������ʼ��λ�ú���ֹ��λ�õõ������·������
	 * 
	 * @param fromIndex ��ʼ��λ��
	 * @param toIndex ��ֹ��λ��
	 * @return ����䳤��
	 */
	private int getLength(int fromIndex, int toIndex){
		VNode t = graph.getNodes().get(fromIndex).getFirst();
		int length = 32767;
		while(t != null){
			if(t.getIndex() == toIndex){
				length = t.getDist();
				break;
			}
			t = t.getNext();
		}
		
		return length;
	}
	
	
	
	
	

	
	
	/**
	 * ��ȡ�߽����Ϣ
	 * 
	 * @param fromIndex ��ʼ��λ��
	 * @param toIndex �յ�λ��
	 * @return �߽����Ϣ
	 */
	private VNode getVNode(int fromIndex, int toIndex){
		VNode newVNode = null;
		
		VNode node = graph.getNodes().get(fromIndex).getFirst();
		while(node != null){
			if(node.getIndex() == toIndex){
				newVNode = new VNode(node.getIndex(), node.getDist(), null);
				break;
			}
			node = node.getNext();
		}
		
		return newVNode;
	}

}
