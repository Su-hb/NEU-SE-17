package Tour;

/*
 *@author:王大犇
 * 这只是一开始测试的类
 * 
 */

import java.util.List;
import java.util.Map;

public class test {

	public  BuildGraph graph;//此处是指创建分布图这个动作是对象，不是景点图本身
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
		//创建分布图
		test t=new test();
		t.graph.outputGraph();
		
		//查找
		String keyword="石";
		
		List<Integer> findNodes=t.findresault.findArc(keyword);
		for(int i=0;i<findNodes.size();i++){
			//System.out.println("简介:"+t.graph.getGraph().getOneNodes(findNodes.get(i)).getDes());
		}
		
		//排序
		Map<String,Integer> orderResult=null;
		
		orderResult=t.orderresult.orderByPop();
		//System.out.println(orderResult);
		orderResult=t.orderresult.orderByPathnum();
		//System.out.println(orderResult);
		
		//最短路径,getPos值为-1，说明地点错误
		t.path.Dijkstra("仙云石", "飞流瀑");
		List<Integer> result=t.path.outputShortestPath();
		//System.out.println(result.get(0));
		
		String result2=t.map.Floyd();
		System.out.println(result2);
	}
}
