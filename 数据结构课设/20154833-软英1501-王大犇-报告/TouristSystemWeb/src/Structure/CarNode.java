package Structure;
/*
 * �����Ľڵ㣬������������
 */
public class CarNode {
	private Car car; //������Ϣ
	private CarNode next; //��������һ����������ָ��
	
	public CarNode(Car car, CarNode next) {
		this.car = car;
		this.next = next;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public CarNode getNext() {
		return next;
	}

	public void setNext(CarNode next) {
		this.next = next;
	}

}
