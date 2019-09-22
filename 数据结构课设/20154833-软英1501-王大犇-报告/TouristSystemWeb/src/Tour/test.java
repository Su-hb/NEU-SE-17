package Tour;

/*
 *@author:�����
 * ��ֻ��һ��ʼ���Ե���
 * 
 */

import java.util.List;
import java.util.Map;

public class test {

	public  BuildGraph graph;//�˴���ָ�����ֲ�ͼ��������Ƕ��󣬲��Ǿ���ͼ����
	public Find findresault;
	public Order orderresult;
	public ShortestPath path;
	public TourMap map;
	
	public test(){
		 graph=new BuildGraph(12,17);
		 graph.createGraph();
		 
		 findresault=new Find(graph.getGraph());
		 
		 orderresult=new Order(graph.getGraph());
		 
		 path=new ShortestPath(graph.getGraph());
		 
		 map=new TourMap(graph.getGraph());
	}
	
	
	public static void main(String[] args){
		//�����ֲ�ͼ
		test t=new test();
		t.graph.outputGraph();
		
		//����
		String keyword="ʯ";
		
		List<Integer> findNodes=t.findresault.findArc(keyword);
		for(int i=0;i<findNodes.size();i++){
			//System.out.println("���:"+t.graph.getGraph().getOneNodes(findNodes.get(i)).getDes());
		}
		
		//����
		Map<String,Integer> orderResult=null;
		
		orderResult=t.orderresult.orderByPop();
		//System.out.println(orderResult);
		orderResult=t.orderresult.orderByPathnum();
		//System.out.println(orderResult);
		
		//���·��,getPosֵΪ-1��˵���ص����
		t.path.Dijkstra("����ʯ", "������");
		List<Integer> result=t.path.outputShortestPath();
		//System.out.println(result.get(0));
		
		String result2=t.map.Floyd();
		System.out.println(result2);
	}
}
