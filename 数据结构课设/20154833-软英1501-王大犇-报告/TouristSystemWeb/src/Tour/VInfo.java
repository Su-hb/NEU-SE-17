package Tour;

public class VInfo {

	private int start; //�ߵ���ʼ��λ��
	private int end; //�ߵ��յ�λ��
	private int weight; //�ߵ�Ȩ��
	
	public VInfo(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
