package ViewPoint;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Vector;
import org.w3c.dom.Attr;

/**
 * @创建人
 * @创建时间 10:47 2019/1/12
 * @描述 景区信息管理系统
 */
public class Minidistance {
	private HashMap<String,Boolean> judge = new HashMap<>();//判断是否访问过
	private HashMap<String,Integer> len = new HashMap<>();//存储最短距离
	private static final int MAXN = Integer.MAX_VALUE/2;//定义最大值，/2的原因是防止溢出
	private HashMap<String ,String> path = new HashMap<>();//存储路径
	//利用优先队列和比较器，实现最小堆。
	private PriorityQueue<Attractions> que = new PriorityQueue<>(
			//实现比较器
			new Comparator<Attractions>() {
				@Override
				public int compare(Attractions o1, Attractions o2) {
					return o1.getCost()-o2.getCost();
				}
			}
	);
	/**
	 * @Author:
	 * @Description：增加边
	 * @Date： 15:16 2019/1/14
	 */
	public void addEdge(Attractions a){
		que.add(a);
	}

	/**
	 * @Author:
	 * @Description：从邻接表中找出相应的景点
	 * @Date： 15:16 2019/1/14
	 */
	public Attractions findOfAdj(String name,ArrayList<Attractions> AdjacencyList){
		for (Attractions a:AdjacencyList
		) {
			if(a.getSpotName() .equals( name)) return a;
		}
		return null;
	}

	/**
	 * @Author:
	 * @Description：SPFA算法的全称是：Bellman-Ford
	 * 为了避免最坏情况的出现，在正权图上应使用效率更高的Dijkstra算法。通常用于求含负权边的单源最短路径，
	 * 以及判负权环。SPFA 最坏情况下复杂度和朴素 Bellman-Ford 相同，为 O(VE)。
	 * 建立一个队列q，初始时队列里只有一个起始点，在建立一个数组dis记录起始点到所有点的最短路径
	 * ，并且初始化这个数组。然后进行松弛操作，用 队列里面的点去刷新起始点到所有点的最短路，
	 * 如果刷新成功且刷新点不再队列中则把该点加入到队列最后，重复执行直到队列为空。如果存在负环的
	 * 话，需要建立一个数组来判断每个点进入队列了多少次，否则队列一直都不为空。
	 * @Date： 13:36 2019/1/16
	 */
	public void SPFA(String start ,ArrayList<Attractions>AdjacencyList){
		for (Attractions a:AdjacencyList
				) {
			len.put(a.getSpotName(),MAXN);
			judge.put(a.getSpotName(),false);//初始化HashMap
		}
		Queue<String> que = new LinkedList<String>();

		//起点大小为0
		len.remove(start);
		len.put(start,0);
		que.offer(start);

		while (!que.isEmpty()){
			String tmp = que.poll();
			Attractions tmpAtt = findOfAdj(tmp,AdjacencyList);
			judge.remove(tmp);
			judge.put(tmp,false);//从queue中退出
			for (Edge edges:tmpAtt.getEdgeArrayList()){
				String to = edges.to;
				if (len.get(to) == MAXN||len.get(to)>(len.get(tmp)+edges.cost)){
					len.remove(to);
					len.put(to,len.get(tmp)+edges.cost);//松弛(RELAX)操作
					if (!judge.get(to)){
						judge.remove(to);
						judge.put(to,true);
						que.offer(to);
					}

				}
			}
		}

	}
	/**
	 * @Author:
	 * @Description：堆优化的dijkstra算法 时间复杂度为O((n + e)log(n))
	 * 堆优化的主要思想就是使用一个优先队列（就是每次弹出的元素一定是整个队列中最小的元素）
	 * 来代替最近距离的查找，用邻接表代替邻接矩阵，这样可以大幅度节约时间开销。
	 * @Date： 15:17 2019/1/14
	 */
	public void dijkstra(String start,ArrayList<Attractions> AdjacencyList){
		for (Attractions a:AdjacencyList
		) {
			judge.put(a.getSpotName(),false);//初始化HashMap
			len.put(a.getSpotName(),MAXN);
		}
		while (!que.isEmpty()) que.poll();//初始化堆

		//起点大小为0
		len.remove(start);
		len.put(start,0);
		//将起点加入堆中
		que.add(findOfAdj(start,AdjacencyList));
		Attractions tmp = null;

		while (!que.isEmpty()){
			//当队列（堆）不为空时
			tmp = que.poll();
			String tmpName = tmp.getSpotName();
			if(judge.get(tmpName)) continue;
			//设置节点已经访问
			judge.remove(tmpName);
			judge.put(tmpName,true);

			for (Edge edges:tmp.getEdgeArrayList()
			) {
				String next = edges.to;
				int cost =edges.cost;
				if(!judge.get(next) && len.get(next)>len.get(tmpName)+cost){
					len.remove(next);
					len.put(next,len.get(tmpName)+cost);
					//松弛操作
					Attractions tmpA = findOfAdj(next,AdjacencyList);
					tmpA.setCost(len.get(tmpName)+cost);
					que.add(tmpA);
					path.put(next,tmpName);
				}
			}
		}
	}
	/**
	 * @Author:
	 * @Description：获得从start到end的最短距离
	 * @Date： 15:19 2019/1/14
	 */
	public int getLen(String end) {

		return len.get(end);
	}

	/**
	 * @Author:
	 * @Description：获得从from到to的最短路径
	 * @Date： 15:18 2019/1/14
	 */
	public String getPath(String from,String to) {
		if(from.equals(path.get(to))){
			return from+"->"+to;
		}
		return getPath(from,path.get(to))+"->"+to;
	}

	public HashMap<String, Boolean> getJudge() {
		return judge;
	}

	public void setJudge(HashMap<String, Boolean> judge) {
		this.judge = judge;
	}

	public void setLen(HashMap<String, Integer> len) {
		this.len = len;
	}

	public static int getMAXN() {
		return MAXN;
	}

	public void setPath(HashMap<String, String> path) {
		this.path = path;
	}

	public PriorityQueue<Attractions> getQue() {
		return que;
	}

	public void setQue(PriorityQueue<Attractions> que) {
		this.que = que;
	}

	/**
	 * @Author:
	 * @Description：进行测试的代码
	 * @Date： 15:18 2019/1/14
	 */
	public static void main(String[] args) {
		ArrayList<Attractions> arrayList = new ArrayList<>();
		Attractions a = new Attractions("a",0);
		ArrayList<Edge> eee = new ArrayList<>();
		eee.add(new Edge("c",30));
		eee.add(new Edge("b",10));


		a.setEdgeArrayList(eee);
		arrayList.add(a);

		Attractions b = new Attractions("b",0);
		ArrayList<Edge> ddd = new ArrayList<>();
		ddd.add(new Edge("c",10));
		b.setEdgeArrayList(ddd);
		arrayList.add(b);
		arrayList.add(new Attractions("c",0));

		Minidistance minidistance = new Minidistance();

		minidistance.SPFA("a",arrayList);

		System.out.println(minidistance.len);

	}
}
