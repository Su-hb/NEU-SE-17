package Tour;

public class AttractNode {

	private String name; //��������
	private String des; //��������
	private int pop; //���㻶ӭ��
	private boolean hasRest; //������Ϣ��
	private boolean hasToilet; //���޲���
	private VNode first; //����ĵ�һ����
	private VNode[] vNodes;
	
	public AttractNode(String name,String des,int pop,boolean hasRest,boolean hasToilet){
		this.name = name;
		this.des = des;
		this.pop = pop;
		this.hasRest = hasRest;
		this.hasToilet = hasToilet;
		//һ��ʼ����һ�����ĵ�
		this.first = new VNode(-1,-1,null);
		vNodes=new VNode[4];
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getPop() {
		return pop;
	}

	public void setPop(int pop) {
		this.pop = pop;
	}

	public boolean isHasRest() {
		return hasRest;
	}

	public void setHasRest(boolean hasRest) {
		this.hasRest = hasRest;
	}

	public boolean isHasToilet() {
		return hasToilet;
	}

	public void setHasToilet(boolean hasToilet) {
		this.hasToilet = hasToilet;
	}

	public VNode getFirst() {
		return first;
	}

	public void setFirst(VNode first) {
		this.first = first;
	}
	
	public void setVNodes(VNode[] vNodes){
		this.vNodes=vNodes;
	}
	
	public VNode[] getVNodes(){
		return vNodes;
	}
}
