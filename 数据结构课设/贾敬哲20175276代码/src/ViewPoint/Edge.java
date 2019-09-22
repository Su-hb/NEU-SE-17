package ViewPoint;

/**
 * @Author:
 * @Description：edge
 * @Date： 23:38 2019/1/13
 */
public class Edge{
	public String to;
	public int cost;

	/**
	 * @Author:
	 * @Description：构造器
	 * @Date： 23:39 2019/1/13
	 */
	public Edge(String to, int cost) {
		this.to = to;
		this.cost = cost;
	}
	/**
	 * @Author:
	 * @Description：set和get方法
	 * @Date： 23:39 2019/1/13
	 */
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
