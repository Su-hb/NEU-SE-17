package Tour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Controller.Tourist;
import Tour.TourGraph;
import Tour.AttractNode;
import Tour.VNode;

/*
 * @author 王大犇
 * 初始化地图
 * 实例化图
 */

public class BuildGraph {
	public static int INF = 32767;
	
	private TourGraph graph;
    private Scanner reader;
    private Scanner inforeader;
    
    public BuildGraph(int attractNum,int vNum){
    	Tourist.setGraph(attractNum,vNum);
    	graph=Tourist.getGraph();
    	try{
    		File file=new File("F:/JavaWorkspaceEE/TouristSystemWeb/Map.txt");
    		File infofile=new File("F:/JavaWorkspaceEE/TouristSystemWeb/Info.txt");
    		reader=new Scanner(file);
    		inforeader=new Scanner(infofile);
    		
    	}catch(FileNotFoundException e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * 读取文件中的景点信息和道路信息，用邻接链表存储
     * 
     */
    public void createGraph(){
    	int attractNum,vNum;
    	String tmp,infos[];
    	String name,des;//景点姓名和简介
    	int pop;//受欢迎程度
    	boolean hasRest,hasToilet;
    	String from,to;
    	int dis;//路的距离
    	int count;
    	
    	//读取文件中景点信息，并存储为图
    	for(int i=1; i<=graph.getAttractNum(); i++){
			tmp = inforeader.nextLine();
			infos = tmp.split(" ");
			name = infos[0];
			des = infos[1];
			pop = Integer.parseInt(infos[2]);
			hasRest = ((infos[3].equals("1"))?true:false);
			hasToilet = ((infos[4].equals("1"))?true:false);
			graph.getNodes().add(new AttractNode(name, des, pop, hasRest, hasToilet));
		}
    	
    	//读取文件中边的信息
    	for(int i=1;i<=graph.getRNum();i++){
    		tmp=reader.nextLine();
    		
    		infos=tmp.split("-");
    		from=infos[0];
    		to=infos[1];
    		dis=Integer.parseInt(infos[2]);
    		
    		int fromIndex=-1, toIndex=-1;
    		count=0;
    		for(AttractNode node: graph.getNodes()){
    			if(to.equals(node.getName())){
    				toIndex=count;
    				break;
    			}
    			count++;
    		}
    		
    		count=0;
    		for(AttractNode node: graph.getNodes()){
    			if(from.equals(node.getName())){
    				fromIndex=count;
    				break;
    			}
    			count++;
    		}
    		
    		VNode node1=new VNode(toIndex,dis,graph.getNodes().get(fromIndex).getFirst());
    		graph.getNodes().get(fromIndex).setFirst(node1);
    		 //VNode[] v=new VNode[];
    		//graph.getNodes().get(fromIndex).setVNodes();
    		VNode node2=new VNode(fromIndex,dis,graph.getNodes().get(toIndex).getFirst());
    		graph.getNodes().get(toIndex).setFirst(node2);
    		
    	}
    }
    
    //输出景区图，以邻接矩阵的形势
    public void outputGraph(){
    	System.out.print("             ");
    	for(AttractNode node: graph.getNodes()){//矩阵第一行头
    		System.out.print(node.getName()+"     ");
    	}
    	System.out.println();
    	
    	for(int i=0; i<graph.getAttractNum();i++){
    		System.out.print(graph.getNodes().get(i).getName()+"     ");
    		for(int j=0; j<graph.getAttractNum();j++){
    			if(i==j){
    				System.out.print("0       ");
    				continue;
    			}
    			int dis=countDis(i,j);
    			System.out.print(dis+"      ");
    		}
    		System.out.println();
    	}
    }
    
    //获取Graph的实例，创建graph对象
    public TourGraph getGraph(){
		return graph;
	}
    
    /**
	 * 该函数用来获取两点间的距离大小，若不连接，距离大小为32767
	 * 
	 * @param 起点位置
	 * @param 终点位置
	 * @return 起点和终点间的距离
	 */
	private int countDis(int fromIndex, int toIndex){
		int dis = INF;
		VNode t = graph.getNodes().get(fromIndex).getFirst();
		while(t != null){
			if(t.getIndex() == toIndex){
				return t.getDist();
			}
			t = t.getNext();
		}
		return dis;
	}
     

}
