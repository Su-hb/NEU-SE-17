package Tour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Controller.Tourist;
import Tour.TourGraph;
import Tour.AttractNode;
import Tour.VNode;

/*
 * @author �����
 * ��ʼ����ͼ
 * ʵ����ͼ
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
     * ��ȡ�ļ��еľ�����Ϣ�͵�·��Ϣ�����ڽ�����洢
     * 
     */
    public void createGraph(){
    	int attractNum,vNum;
    	String tmp,infos[];
    	String name,des;//���������ͼ��
    	int pop;//�ܻ�ӭ�̶�
    	boolean hasRest,hasToilet;
    	String from,to;
    	int dis;//·�ľ���
    	int count;
    	
    	//��ȡ�ļ��о�����Ϣ�����洢Ϊͼ
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
    	
    	//��ȡ�ļ��бߵ���Ϣ
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
    
    //�������ͼ�����ڽӾ��������
    public void outputGraph(){
    	System.out.print("             ");
    	for(AttractNode node: graph.getNodes()){//�����һ��ͷ
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
    
    //��ȡGraph��ʵ��������graph����
    public TourGraph getGraph(){
		return graph;
	}
    
    /**
	 * �ú���������ȡ�����ľ����С���������ӣ������СΪ32767
	 * 
	 * @param ���λ��
	 * @param �յ�λ��
	 * @return �����յ��ľ���
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
