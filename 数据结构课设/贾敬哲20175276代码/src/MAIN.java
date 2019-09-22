import admin.Login;
import ViewPoint.Adj2Matrix;
import ViewPoint.Attractions;
import ViewPoint.FindViewPoint;
import ViewPoint.MatrixUDG;
import ViewPoint.Minidistance;
import carParking.MycarManagement;
import carParking.carInformationManagement;
import com.sun.xml.internal.ws.wsdl.writer.document.Fault;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * @创建人
 * @创建时间 22:51 2019/1/14
 * @描述 景区信息管理系统
 */
public class MAIN {

	/**
	 * 停车场信息管理系统
	 */
	public static carParking.carInformationManagement carInformationManagement = new carInformationManagement();
	/**
	 * 景区管理系统菜单
	 */
	public static String startMenu = "=============================\n"
			+ "   欢迎使用景区信息管理系统  \n"
			+ "      ***请选择菜单***  \n"
			+ "=============================\n"
			+ "  1.输出景区景点分布图\n"
			+ "  2.输出导游路线图\n"
			+ "  3.输出导游路线图(回路)\n"
			+ "  4.景点间最短路径和最短距离\n"
			+ "  5.停车场进出信息管理系统\n"
			+ "  6.景点热度排名\n"
			+ "  7.景点边数排名\n"
			+ "  8.查找景点\n"
			+ "  9.管理员管理模块\n"
			+ "  0.退出系统\n" +
			"=============================\n";
	/**
	 * 停车场管理系统
	 */
	public static final String car = "——————————————————————————————————\n"
			+ "    欢迎使用景区停车场信息管理系统\n"
			+ "      ***请选择菜单***  \n"
			+ "——————————————————————————————————\n"
			+ "  1 --- 汽车 进 车场\n"
			+ "  2 --- 汽车 出 车场\n"
			+ "  3 --- 停车 场 情况\n"
			+ "  0 ---  退出  程序\n"
			+ "——————————————————————————————————\n";
	/**
	 *
	 */
	public static final String adminMethod = "*******************************\n"
			+ "   ##  管理员操作  ##\n"
			+ "*******************************\n"
			+ " 1.增加路线\n"
			+ " 2.删除节点\n"
			+ " 3.景点信息修改\n"
			+ " 4.发布通告\n"
			+ " 5.保存路径信息\n"
			+ " 6.删除路径\n"
			+ " 0.退出管理员系统\n"
			+ "*******************************\n";

	/**
	 * @Author:
	 * @Description：初始化系统操作
	 * @Date： 13:57 2019/1/15
	 */
	public static void init() {
		//读取文件
		ReadFile.Readfile("./src/data.txt");
	}

	/**
	 * @Author:
	 * @Description：清屏操作
	 * @Date： 13:57 2019/1/15
	 */
	public static void clear() {
		//打印20个换行符
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}

	/**
	 * @Author:
	 * @Description：无回路的导游路线图
	 * @Date： 13:56 2019/1/15
	 */
	public static void guideLineNo() {
		String[] nodes = new String[ReadFile.attractionsArrayList.size()];
		for (int i = 0; i < ReadFile.attractionsArrayList.size(); i++) {
			nodes[i] = ReadFile.attractionsArrayList.get(i).getSpotName();
		}
		int[][] matrix = Adj2Matrix.getMatrix(ReadFile.attractionsArrayList);
		MatrixUDG pG;
		pG = new MatrixUDG(nodes, matrix);
		pG.primNo(1);
	}

	/**
	 * @Author:
	 * @Description：有回路的导游路线图
	 * @Date： 13:56 2019/1/15
	 */
	public static void guideLineYes() {
		String[] nodes = new String[ReadFile.attractionsArrayList.size()];
		for (int i = 0; i < ReadFile.attractionsArrayList.size(); i++) {
			nodes[i] = ReadFile.attractionsArrayList.get(i).getSpotName();
		}
		int[][] matrix = Adj2Matrix.getMatrix(ReadFile.attractionsArrayList);
		MatrixUDG pG;
		// 采用已有的"图"
		pG = new MatrixUDG(nodes, matrix);

		pG.primYes(0);   // prim算法生成最小生成树
	}

	/**
	 * @Author:
	 * @Description：最短路径
	 * @Date： 13:59 2019/1/15
	 */
	public static void miniDistance(String start, String end) {
		//检查景点是否输入错误
		if (FindViewPoint.Find(start, ReadFile.attractionsArrayList) == null
				|| FindViewPoint.Find(end, ReadFile.attractionsArrayList) == null) {
			System.out.println("未找到景点，请检查是否输入错误！");

		} else {
			Minidistance minidistance = new Minidistance();
			minidistance.dijkstra(start, ReadFile.attractionsArrayList);
			System.out.println("最短路径为：" + minidistance.getPath(start, end));
			System.out.println("最短距离为: " + minidistance.getLen(end));
		}
	}

	/**
	 * @Author:
	 * @Description：停车场管理系统
	 * @Date： 14:14 2019/1/15
	 */
	public static void carManagement() {
		clear();//清屏操作
		carcmd:
		while (true) {
			System.out.println(car);
			System.out.println("输入操作：  ");
			Scanner Car = new Scanner(System.in);
			String method = Car.nextLine();
			switch (method) {
				case "0":
					//结束循环 退出系统
					System.out.println("退出系统");
					break carcmd;
				case "1":
					//汽车进入停车场
					Scanner inCar = new Scanner(System.in);
					System.out.println("(汽车入场)输入车牌号： ");
					String carNum = inCar.nextLine();
					carInformationManagement.addCar(carNum);
					System.out.println("添加成功！");
					System.out.println("现在停车场内情况: ");
					try {
						carInformationManagement.display();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				case "2":
					//汽车退出停车场
					Scanner outCar = new Scanner(System.in);
					System.out.println("(汽车出场)输入车牌号： ");
					String outNum = outCar.nextLine();
					try {
						carInformationManagement.removeCar(outNum);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("现在停车场内情况: ");
					try {
						carInformationManagement.display();

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;
				case "3":
					System.out.println("现在停车场内情况: ");
					try {
						carInformationManagement.display();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("操作数错误！");
					break;
			}
		}
	}

	/**
	 * @Author:
	 * @Description：景点热度排名
	 * @Date： 14:40 2019/1/15
	 */
	public static void sortViewPoint() {

		ViewPoint.SortAtt.display(ReadFile.attractionsArrayList);
	}

	/**
	 * @Author:
	 * @Description：管理员管理模块
	 * @Date： 14:50 2019/1/15
	 */
	public static void adminLogin() {
		clear();
		boolean logFlag = false;
		Scanner logScanner = new Scanner(System.in);

		System.out.println("  ***  管理员模块  ***");
		System.out.print("输入用户名： ");
		String userName = logScanner.nextLine();
		System.out.print("输入密码： ");
		String userPWD = logScanner.nextLine();
		try {
			logFlag = Login.login(userName, userPWD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (!logFlag) {
			System.out.println("用户名或密码错误！");
		}
		admincmd:
		while (logFlag) {
			System.out.println(adminMethod);
			Scanner adminScanner = new Scanner(System.in);
			System.out.print("请输入管理员操作：");
			String adminMethod = adminScanner.nextLine();
			switch (adminMethod) {
				case "0":
					break admincmd;
				case "1":
					//增加路线和景点
					Scanner addScanner = new Scanner(System.in);
					System.out.print("输入起始景点： ");
					String addStart = addScanner.nextLine();
					System.out.print("输入终止景点： ");
					String addEnd = addScanner.nextLine();
					System.out.print("输入路程： ");
					String addCost = addScanner.nextLine();
					admin.adminstratorMethod
							.addEdge(ReadFile.attractionsArrayList, addStart, addEnd,
									Integer.parseInt(addCost));
					System.out.println("当前景点结构：");
					Adj2Matrix.display(ReadFile.attractionsArrayList, Adj2Matrix.getMatrix(
							ReadFile.attractionsArrayList));
					break;
				case "2":
					//删除景点
					Scanner removeScanner = new Scanner(System.in);
					System.out.print("输入需要删除的景点名称： ");
					String removeNode = removeScanner.nextLine();
					admin.adminstratorMethod.removeNode(ReadFile.attractionsArrayList, removeNode);
					System.out.println("当前景点结构：");
					Adj2Matrix.display(ReadFile.attractionsArrayList,
							Adj2Matrix.getMatrix(ReadFile.attractionsArrayList));
					break;
				case "3":
					Scanner change = new Scanner(System.in);
					System.out.print("请输入需要修改的节点： ");
					String name = change.nextLine();
					admin.adminstratorMethod.changeInformation(name, ReadFile.attractionsArrayList);
					break;
				case "4":
					Scanner alerts = new Scanner(System.in);
					System.out.println("请输入新通告");
					String alertsstring = alerts.nextLine();
					startMenu += ("通告：" + alertsstring + "\n");
					break;
				case "5":
					ReadFile.writeFile(ReadFile.attractionsArrayList);
					System.out.println("写入文件成功！请重启程序！");
					break;
				case "6":
					Scanner RemoveScanner = new Scanner(System.in);
					System.out.print("输入起始景点： ");
					String removeStart = RemoveScanner.nextLine();
					System.out.print("输入终止景点： ");
					String removeEnd = RemoveScanner.nextLine();
					admin.adminstratorMethod
							.removeEdge(ReadFile.attractionsArrayList, removeStart, removeEnd);
					System.out.println("删除成功！");
					break;
				default:
					System.out.println("操作数错误！");
					break;
			}
		}
	}

	/**
	 * @Author:
	 * @Description：绘制图
	 * @Date： 15:59 2019/1/16
	 */
	public static void printGraph() {
		try {
			Runtime run = Runtime.getRuntime();
			//执行命令行指令 运行打包好的python程序
			Process p = run.exec("cmd /c printGraph.exe");
			p.waitFor();//等待上述程序执行完
			//获取命令行的返回情况
			BufferedReader br = new BufferedReader(
					new InputStreamReader(p.getInputStream(), "UTF-8"));
			String line = null;
			StringBuilder build = new StringBuilder();
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				build.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}


	public static void main(String[] args) {
		init();
		cmd:
		while (true) {
			//操作数输入
			System.out.println(startMenu);
			System.out.print("输入操作： ");
			Scanner in = new Scanner(System.in);
			String method = in.nextLine();

			switch (method) {
				case "0":
					//退出循环 即退出系统
					break cmd;
				case "1":
					//绘制图并展示
					clear();
					System.out.println("请等候》");
					printGraph();
					Adj2Matrix.display(ReadFile.attractionsArrayList, Adj2Matrix.getMatrix(
							ReadFile.attractionsArrayList));
					System.out.println();
					break;
				case "2":
					//非回路的导游图
					clear();
					guideLineNo();
					System.out.println();
					break;
				case "3":
					//回路的导游图
					clear();
					guideLineYes();
					System.out.println();
					break;
				case "4":
					//最短路径
					Scanner inName = new Scanner(System.in);
					System.out.print("请输入起点位置： ");
					String from = inName.nextLine();
					System.out.print("请输入终点位置： ");
					String to = inName.nextLine();
					miniDistance(from, to);
					break;
				case "5":
					//停车场管理系统
					clear();
					carManagement();
					break;
				case "6":
					//景点排序
					clear();
					sortViewPoint();
					break;
				case "9":
					//管理员登录
					clear();
					adminLogin();
					break;
				case "8":
					//查找景点
					clear();
					Scanner findeName = new Scanner(System.in);
					System.out.print("请输入查找位置： ");
					String findname = findeName.nextLine();
					System.out.println("景点信息： ");
					ArrayList<Attractions> res = FindViewPoint
							.Finds(findname, ReadFile.attractionsArrayList);
					if (res == null) {
						System.out.println("没有找到！");
						break;
					}
					for (Attractions att : res) {
						System.out.println(att.toString());
						System.out.println("——————————————————————————");
					}

					break;
				case "7":
					//根据边数进行景点排序
					clear();
					ViewPoint.SortAtt.displayEdges(ReadFile.attractionsArrayList);
					break;

				default:
					System.out.println("操作数错误！");
					break;

			}
		}

	}

}
