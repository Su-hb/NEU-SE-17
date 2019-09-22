package Controller;

import Tour.Park;
import Tour.TourGraph;

/*
 * @author �����
 * �ο��࣬Ϊ�˴���ͼ��ʵ��������������
 */

public class Tourist {
	private static TourGraph graph;
	private static Park parkplot;
	
	public static TourGraph getGraph(){
		return graph;
	}
	
	
	public static Park getPark(){
		return parkplot;
	}
	
	
	public static void setGraph(){
		graph = null;
	}
	
	public static void setGraph(int arcNum, int vetNum){
		graph = new TourGraph(arcNum, vetNum);
	}
	
	
	public static void setPark(int maxParkingNum){
		parkplot = new Park(maxParkingNum);
	}
	
}

