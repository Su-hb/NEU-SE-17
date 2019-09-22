package Structure;

public class Queue {
	private CarNode phead; //������������ͷ
	private int size; //���е�Ԫ�ظ���
	
	public Queue(){
		this.phead = new CarNode(null, null);
		this.size = 0;
	}

	public void add(Car car){
		//�ҵ��������Ľ��
		CarNode tmpNode = phead;
		while(tmpNode.getNext() != null){
			tmpNode = tmpNode.getNext();
		}
		tmpNode.setNext(new CarNode(car, null));
		
		size++;
	}
	
	public void pop(){
		if(size == 0) return;
		
		//ɾ�������е�һ�����
		CarNode delNode = phead.getNext();
		phead.setNext(delNode.getNext());
		delNode = null;
		
		size--;
	}
	
	public CarNode front(){
		if(size == 0) return null;
		
		return phead.getNext();
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size==0 ? true : false;
	}
	
	public CarNode getHeadNode(){
		return phead;
	}
	
	public boolean existNumber(String number){
		boolean exist = false;
		
		CarNode node = phead.getNext();
		while(node != null){
			if(node.getCar().getNumber().equals(number)){
				exist = true;
				break;
			}
			node = node.getNext();
		}
		
		return exist;
	}

}
