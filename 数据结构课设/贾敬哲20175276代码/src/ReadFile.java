import ViewPoint.Adj2Matrix;
import ViewPoint.Attractions;
import ViewPoint.Edge;
import ViewPoint.Minidistance;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @创建人
 * @创建时间 10:55 2019/1/12
 * @描述 景区信息管理系统
 */
public class ReadFile {

	public static ArrayList<Attractions> attractionsArrayList = new ArrayList<>();
	//存储数据

	/**
	 * @Author:
	 * @Description：增加路线
	 * @Date： 16:15 2019/1/18
	 */
	public static void addeles(String from, String to, Integer cost) {
		boolean flagOfFrom = true;
		boolean flagOfTo = true;
		//遍历 判断起止点 是否已经存储在内存中
		for (Attractions att : attractionsArrayList
				) {
			if (att.getSpotName().equals(from)) {
				att.getEdgeArrayList().add(new Edge(to, cost));
				flagOfFrom = false;
			}
			if (att.getSpotName().equals(to)) {
				att.getEdgeArrayList().add(new Edge(from, cost));
				flagOfTo = false;
			}
		}
		if (flagOfFrom) {
			Attractions tmp = new Attractions(from, 0);
			tmp.getEdgeArrayList().add(new Edge(to, cost));
			attractionsArrayList.add(tmp);
		}
		if (flagOfTo) {
			Attractions tmp = new Attractions(to, 0);
			tmp.getEdgeArrayList().add(new Edge(from, cost));
			attractionsArrayList.add(tmp);
		}
	}

	/**
	 * @Author:
	 * @Description：读取文件
	 * @Date： 15:13 2019/1/14
	 */
	public static void Readfile(String path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			UnicodeReader ur = new UnicodeReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(ur);
			String line = br.readLine();
			int id = 0;
			while (line != null && line != "") {
				String[] eles = line.split(" ");
				addeles(eles[0], eles[1], Integer.parseInt(eles[2]));
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	/**
	 * @Author:
	 * @Description：写入文件
	 * @Date： 14:58 2019/1/17
	 */
	public static void writeFile(ArrayList<Attractions> adjList) {
		HashMap<String, Boolean> judge = new HashMap<>();//判断是否访问过
		for (Attractions att : adjList
				) {
			judge.put(att.getSpotName(), true);
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("./src/data.txt");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			for (Attractions att : adjList) {
				for (Edge edge : att.getEdgeArrayList()) {
					if (judge.get(edge.to)) {
						System.out
								.print(att.getSpotName() + " " + edge.to + " " + edge.cost + "\n");
						osw.write(att.getSpotName() + " " + edge.to + " " + edge.cost
								+ "\r\n");//写入文件 并补上换行符
					}
				}
				judge.remove(att.getSpotName());
				judge.put(att.getSpotName(), false);//表明节点已经访问过了
			}

			osw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Readfile("./src/data.txt");
		admin.adminstratorMethod.addEdge(attractionsArrayList, "狮子山", "东北大学", 33);
		Adj2Matrix.display(attractionsArrayList, Adj2Matrix.getMatrix(attractionsArrayList));

		admin.adminstratorMethod.removeNode(attractionsArrayList, "北门");

		Adj2Matrix.display(attractionsArrayList, Adj2Matrix.getMatrix(attractionsArrayList));

	}
}
