import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @创建人 贾敬哲
 * @创建时间 14:53 2019/1/1
 * @描述 景区信息管理系统_系统初始化
 */
public class init {
	public HashMap<String, Integer> name2id = new HashMap<String, Integer>();
	public HashMap<Integer, String> id2name = new HashMap<>();

	class edge{
		int from;
		int to;
		int value;

		public edge(int from, int to, int value) {
			this.from = from;
			this.to = to;
			this.value = value;
		}
	}

	//存储边
	public ArrayList<edge> edges = new ArrayList<>();

	public void readFile() throws IOException {


		//读取文件
		File file = new File("./src/data.txt");
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));
		String tempString = null;
		//编号
		int numberID = 0;
		while ((tempString = reader.readLine()) != null) {
			String[] tempList = tempString.split(" ");
			//如果出现不在HashMap的新景点名
			if(!name2id.containsKey(tempList[0])){
				name2id.put(tempList[0],numberID);
				id2name.put(numberID,tempList[0]);
				numberID++;
			}
			if(!name2id.containsKey(tempList[1])){
				name2id.put(tempList[1],numberID);
				id2name.put(numberID,tempList[1]);
				numberID++;
			}
			edges.add(new edge(name2id.get(tempList[0]),name2id.get(tempList[1]),Integer.parseInt(tempList[2])));
		}
		reader.close();

	}

	public static void main(String[] args) {
		init in = new init();
		try {
			in.readFile();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
