package Tour;

public class VNode {

	private int index; //��һ�������ھ��������е�λ��
	private int dist; //��������ľ���
	//private int time; //����ʱ��
	private VNode next; //��ͷ�����������һ����
	
	public VNode(int index,int dist,VNode next){
		this.index = index;
		this.dist = dist;
		//this.time = time;
		this.next = next;
		
	}
	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}
/*
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
*/
	public VNode getNext() {
		return next;
	}

	public void setNext(VNode next) {
		this.next = next;
	}
}
