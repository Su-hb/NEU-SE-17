package carParking;

/**
 * @创建人
 * @创建时间 9:56 2019/1/14
 * @描述 景区信息管理系统
 */
public class QueueArrays<E> {

	/**
	 * 队尾插入
	 * 队首取出
	 */

	public int front = -1,real = -1;
	public int size = 100;
	public zanlind[] arrays;

	public QueueArrays(int size) {
		this.size = size;
		arrays = new zanlind[size];

	}
	public QueueArrays() {
		arrays = new zanlind[size];
	}

	public void inQueue(zanlind data) {
		/**
		 * 还有空余
		 */
		if(real < size) {
			arrays[++real] = data;
		}
	}

	public zanlind outQueue() {
		/**
		 * 队首不等于队尾
		 * 或者说
		 * 队首小于队尾
		 */
		if(front != real) {
			return arrays[++front];
		}
		return null;
	}

	public void display() {
		for (int i = front + 1; i <= real; i++) {
			System.out.print(arrays[i].numberOfCar+" ");
		}
	}
	public boolean isEmpty() {
		/**
		 * 队首和队尾的指针比较造成了假溢出的问题
		 */
		return front == real;
	}
	public zanlind find(String num){
		for (int i = front + 1; i <= real; i++) {
			if(arrays[i].numberOfCar.equals(num)){
				return arrays[i];
			}
		}
		return  null;
	}

}
