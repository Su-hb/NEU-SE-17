package admin;

import ViewPoint.Attractions;
import ViewPoint.Edge;
import ViewPoint.FindViewPoint;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @创建人
 * @创建时间 12:23 2019/1/15
 * @描述 景区信息管理系统
 */
public class adminstratorMethod {

	private static final String changeInformationCMD = "+++++++++++++++++++++++\n"
			+ "   ***管理员修改景点信息***   \n"
			+ "       选择菜单  \n"
			+ "+++++++++++++++++++++++\n"
			+ "  1.修改景点名称\n"
			+ "  2.修改景点欢迎度\n"
			+ "  3.修改是否有休息区\n"
			+ "  4.修改是否有公测\n"
			+ "  5.修改景点描述\n"
			+ "  0.退出修改景点信息模块\n"
			+ "+++++++++++++++++++++++\n";

	/**
	 * @Author:
	 * @Description：增加节点
	 * @param attractionsArrayList 邻接表 from 起点 to 终点 cost 花费
	 * @Date： 12:26 2019/1/15
	 */
	public static void addEdge(ArrayList<Attractions> attractionsArrayList, String from, String to,
			int cost) {
		//用于判断是否在图中已经包含这个景点
		boolean flagOffrom = true;
		boolean flagOfto = true;
		for (Attractions att : attractionsArrayList
				) {
			//图中包含景点
			if (att.getSpotName().equals(from)) {
				att.getEdgeArrayList().add(new Edge(to, cost));
				flagOffrom = false;
			}
			if (att.getSpotName().equals(to)) {
				att.getEdgeArrayList().add(new Edge(from, cost));
				flagOfto = false;
			}
		}
		//图中未包含景点
		if (flagOffrom) {
			Attractions tmp = new Attractions(from, 0);
			tmp.getEdgeArrayList().add(new Edge(to, cost));
			attractionsArrayList.add(tmp);
		}
		if (flagOfto) {
			Attractions tmp = new Attractions(to, 0);
			tmp.getEdgeArrayList().add(new Edge(from, cost));
			attractionsArrayList.add(tmp);
		}
	}

	/**
	 * @Author:
	 * @Description：删除路
	 * @Date： 9:41 2019/1/20
	 */
	public static void removeEdge(ArrayList<Attractions> attractionsArrayList, String from,
			String to) {
		boolean fromFlag = false;
		boolean toFlag = false;
		for (Attractions att : attractionsArrayList
				) {
			//删除起点的边
			if (att.getSpotName().equals(from)) {
				Edge tmp = null;
				for (Edge e : att.getEdgeArrayList()) {
					if (e.to.equals(to)) {
						tmp = e;
						break;
					}
				}
				att.getEdgeArrayList().remove(tmp);
				fromFlag = true;
				continue;
			}
			//删除终点的边
			if (att.getSpotName().equals(to)) {
				Edge tmp = null;
				for (Edge e : att.getEdgeArrayList()) {
					if (e.to.equals(from)) {
						tmp = e;
						break;
					}
				}
				att.getEdgeArrayList().remove(tmp);
				toFlag = true;
				continue;
			}
		}
		if (toFlag && fromFlag) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！请检查该路径是否存在");
		}
	}

	/**
	 * @Author:
	 * @Description：删除节点
	 * @Date： 12:26 2019/1/15
	 */
	public static void removeNode(ArrayList<Attractions> attractionsArrayList, String node) {
		//暂时存储要删除节点的边 以便于删除其他与之相邻的节点
		ArrayList<Edge> edges = new ArrayList<>();
		Attractions tmp = new Attractions();
		for (Attractions att : attractionsArrayList
				) {
			if (att.getSpotName().equals(node)) {
				edges = att.getEdgeArrayList();
				tmp = att;
				break;
			}
		}
		for (Attractions att : attractionsArrayList
				) {
			for (Edge tmpe : edges) {
				if (att.getSpotName().equals(tmpe.to)) {
					att.getEdgeArrayList().removeIf(Edge -> Edge.to.equals(node));
				}
			}
		}
		attractionsArrayList.remove(tmp);
	}

	/**
	 * @Author:
	 * @Description：景点信息修改
	 * @Date： 14:22 2019/1/16
	 */
	public static void changeInformation(String needToChange, ArrayList<Attractions> adjList) {

		if (FindViewPoint.Find(needToChange, adjList) == null) {
			System.out.println("未找到该节点；");
			return;
		}
		changecmd:
		while (true) {
			System.out.println(changeInformationCMD);
			System.out.printf("(当前修改的景点是 %s）输入操作：", needToChange);
			Scanner change = new Scanner(System.in);
			String changeMethod = change.nextLine();

			switch (changeMethod) {
				case "0":
					System.out.println("退出系统");
					break changecmd;
				case "1":
					System.out.println("输入新名字：");
					Scanner name = new Scanner(System.in);
					String newName = name.nextLine();
					FindViewPoint.Find(needToChange, adjList).setSpotName(newName);
					System.out.println("修改成功!");
					needToChange = newName;
					break;
				case "2":
					System.out.println("输入欢迎度：");
					Scanner welcome = new Scanner(System.in);
					int newwelcome = welcome.nextInt();
					FindViewPoint.Find(needToChange, adjList).setAttractionsWelcome(newwelcome);
					System.out.println("修改成功!");
					break;
				case "3":
					System.out.println("输入是否有休息区(true/false)： ");
					Scanner seat = new Scanner(System.in);
					String newSeat = seat.nextLine();
					boolean newseat = Boolean.parseBoolean(newSeat);
					FindViewPoint.Find(needToChange, adjList).setSeatingArea(newseat);
					System.out.println("修改成功!");
					break;
				case "4":
					System.out.println("输入是否有公厕(true/false)： ");
					Scanner toilet = new Scanner(System.in);
					String newToilet = toilet.nextLine();
					boolean newtoilet = Boolean.parseBoolean(newToilet);
					FindViewPoint.Find(needToChange, adjList).setPublicToilet(newtoilet);
					System.out.println("修改成功!");
					break;
				case "5":
					System.out.println("输入新描述：");
					Scanner des = new Scanner(System.in);
					String newDes = des.nextLine();
					FindViewPoint.Find(needToChange, adjList).setIntroductionToAttractions(newDes);
					System.out.println("修改成功!");
					break;
				default:
					System.out.println("操作数错误！");
					break;
			}
		}

	}


}
