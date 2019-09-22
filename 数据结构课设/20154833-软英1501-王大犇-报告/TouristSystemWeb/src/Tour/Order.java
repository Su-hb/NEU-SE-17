package Tour;

import java.util.LinkedHashMap;
import java.util.Map;

import Tour.TourGraph;
import Tour.AttractNode;
import Tour.VNode;

public class Order {
	private TourGraph graph;
	
	public Order(TourGraph graph){
		this.graph=graph;
	}
	
	/**
	 * ���ݻ�ӭ�̶Ƚ������򣬽���Խ����˳������
	 */
	public Map<String,Integer> orderByPop(){
		int index=0;
		AttractNode[] nodes=new AttractNode[graph.getAttractNum()];//�������о���ڵ��ʵ����nodes���鼴����
		for(AttractNode node : graph.getNodes()){
			nodes[index++]=new AttractNode(node.getName(),node.getDes(),node.getPop(),node.isHasRest(),node.isHasToilet());
		}
		
		//ʹ��ð������Ի�ӭ�̶�����
		for(int i=0;i<graph.getAttractNum();i++){
			for(int j=i+1;j<graph.getAttractNum();j++){
				if(nodes[i].getPop()<nodes[j].getPop()){
					AttractNode tmp=nodes[i];
					nodes[i]=nodes[j];
					nodes[j]=tmp;
				}
			}
		}
		
		Map<String,Integer> orderResult=new LinkedHashMap<String,Integer>();
		for(int i=0;i<graph.getAttractNum();i++){
			orderResult.put(nodes[i].getName(), nodes[i].getPop());
		}
		return orderResult;
	}
	
	/**
	 * ���ݾ���Ĳ�·��������������������������
	 */
	public Map<String,Integer> orderByPathnum(){
		int index=0;
		AttractNode[] nodes=new AttractNode[graph.getAttractNum()];
		int[] pathNums=new int[graph.getAttractNum()];
		//�Ծ����·���ļ���
		for(AttractNode attractNode: graph.getNodes()){
			nodes[index]=new AttractNode(attractNode.getName(),attractNode.getDes(),attractNode.getPop(),attractNode.isHasRest(),attractNode.isHasToilet());
			VNode vNode=attractNode.getFirst();
			while((vNode!=null)&&(vNode.getIndex()!=-1)){
				pathNums[index]++;
				vNode=vNode.getNext();
			}
			index++;
		}
		
		//�Բ�·����������
		for(int i=0;i<graph.getAttractNum();i++){
			for(int j=i+1;j<graph.getAttractNum();j++){
				if(pathNums[i]<pathNums[j]){
					int tmp=pathNums[i];
					pathNums[j]=pathNums[j];
					pathNums[j]=tmp;
					AttractNode tmpNode=nodes[i];
					nodes[i]=nodes[j];
					nodes[j]=tmpNode;
				}
			}
		}
		Map<String, Integer> orderResult = new LinkedHashMap<String, Integer>();
		for(int i=0;i<graph.getAttractNum();i++){
			orderResult.put(nodes[i].getName(), pathNums[i]);
		}
		System.out.println("��·��"+orderResult);
		return orderResult;
	}

}
