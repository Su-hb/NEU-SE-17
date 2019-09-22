package carParking;

/**
 * @创建人
 * @创建时间 9:53 2019/1/14
 * @描述 景区信息管理系统
 */
public class MyStack {

	//存数据的数组
	zanlind[] data;

	//栈的最大长度
	private int size;
	//栈顶的位置
	private int top;

	public MyStack(int size) {
		this.size = size;
		data = new zanlind[size];
		top = -1;
	}

	public int getSize() {
		return size;
	}

	public int getTop() {
		return top;
	}

	/**
	 * 判断是否为空栈
	 * @return
	 */
	public boolean isEmpty()     {
		return top == -1;
	}

	/**
	 * 判断是否为满栈
	 * @return
	 */
	public boolean isFull() {
		return (top+1) == size;
	}

	/**
	 * 压栈操作
	 * @param data
	 * @return
	 */
	public boolean push(zanlind data) {
		if(isFull()) {
			System.out.println("the stack is full!");
			return false;
		} else {
			top++;
			this.data[top] = data;
			return true;
		}
	}
	/**
	 *  弹栈操作
	 * @return
	 * @throws Exception
	 */
	public zanlind pop() throws Exception {
		if(isEmpty()) {
			throw new Exception("the stack is empty!");
		} else {
			return this.data[top--];
		}
	}

	/**
	 * 获取栈顶的元素,但不弹栈
	 * @return
	 */
	public Object peek() {
		return this.data[getTop()];
	}


}
