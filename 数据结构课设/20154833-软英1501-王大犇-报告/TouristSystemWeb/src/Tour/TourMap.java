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
 * @author 王大犇
 * 旅游路线图
 * DFS算法
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
	 * 深度遍历算法
	 * 选择起始点 start
	 */
	public List<Integer> DFS(String start){
		Stack<Integer> dfsNodes=new Stack<Integer>(Integer.class,graph.getAttractNum());
		
		startIndex=getPos(start);
		
		dfsNodes.push(startIndex);//得到起始位置
		System.out.println(startIndex);
		while(!dfsNodes.isEmpty() && !isAllVisited()){//算法遍历：遍历条件-所有点都被遍历到
			int attractNodeNum=dfsNodes.peek();
			System.out.println(attractNodeNum);
			visited[attractNodeNum]=true;//
			VNode vNode=graph.getNodes().get(attractNodeNum).getFirst();
			while((vNode!=null)&&(vNode.getIndex()!=-1)){//判断下一个边节点是否为空
				//System.out.println(vNode.getIndex());
				if(!visited[vNode.getIndex()]){
					dfsNodes.push(vNode.getIndex());
					break;
				}
				vNode=vNode.getNext();
			}
			if((vNode==null)||(vNode.getIndex()==-1)){//如果下一个边界点是空
				dfsNodes.pop();
			}
			tourIndexList.add(attractNodeNum);
		}
		return tourIndexList;
	}
	
	//自己写的将深度遍历和迪杰特斯拉算法衔接（起错名了）
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
	
	//得到按景点名称的位置
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
	
	//判断是否所有景点都被访问
	public boolean isAllVisited(){
		for(boolean visit:visited){
			if(!visit){
				return false;
			}
		}
		return true;
	}
	
	
	/**
     * 利用Floyd算法计算两点之间的最短距离
     * 没写成功
     * @return 最短路径
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
        
        //生成结果集合
        String floydString = "";
        for (int i = 0; i < 32767; i++) {
            for (int j = 0; j < 32767; j++)
                floydString += distance[i][j] + " ";
            floydString += "\n";
        }
        
        return floydString;
    }
    
    /**
	 * 根据起始点位置和终止点位置得到两点间路径长度
	 * 
	 * @param fromIndex 起始点位置
	 * @param toIndex 终止点位置
	 * @return 两点间长度
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
	 * 获取边结点信息
	 * 
	 * @param fromIndex 起始点位置
	 * @param toIndex 终点位置
	 * @return 边结点信息
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
