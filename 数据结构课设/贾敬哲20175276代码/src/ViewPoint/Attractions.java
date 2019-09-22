package ViewPoint;

import java.util.ArrayList;
import java.util.Random;

/**
 * @创建人
 * @创建时间 14:39 2019/1/1
 * @描述 景区信息管理系统
 */
public class Attractions {

	private String spotName;//景点名字
	private int cost;//用于实现堆优化
	private String introductionToAttractions;
	private int attractionsWelcome;
	private Boolean seatingArea;
	private Boolean publicToilet;

	private ArrayList<Edge> edgeArrayList = new ArrayList<>();//用于实现邻接表

	public Attractions(String spotName, int cost) {
		this.spotName = spotName;
		this.cost = cost;
		this.attractionsWelcome = new Random().nextInt(100);
		this.publicToilet = true;
		this.seatingArea = true;
	}

	/**
	 * @Author:
	 * @Description：全参构造函数
	 * @Date： 14:44 2019/1/1
	 */
	public Attractions( String spotName, String introductiongToAttractions,
			int attractionsWelcome, Boolean seatingArea, Boolean publicToilet) {

		this.spotName = spotName;
		this.introductionToAttractions = introductiongToAttractions;
		this.attractionsWelcome = attractionsWelcome;
		this.seatingArea = seatingArea;
		this.publicToilet = publicToilet;
	}

	/**
	 * @Author:
	 * @Description：无参构造函数
	 * @Date： 14:44 2019/1/1
	 */
	public Attractions() {
	}
	/**
	 * @Author:
	 * @Description：set()&&get()
	 * @Date： 14:43 2019/1/1
	 */
	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public String getIntroductionToAttractions() {
		return introductionToAttractions;
	}

	public void setIntroductionToAttractions(String introductionToAttractions) {
		this.introductionToAttractions = introductionToAttractions;
	}

	public ArrayList<Edge> getEdgeArrayList() {
		return edgeArrayList;
	}

	public void setEdgeArrayList(ArrayList<Edge> edgeArrayList) {
		this.edgeArrayList = edgeArrayList;
	}

	public int getAttractionsWelcome() {
		return attractionsWelcome;
	}

	public void setAttractionsWelcome(int attractionsWelcome) {
		this.attractionsWelcome = attractionsWelcome;
	}

	public Boolean getSeatingArea() {
		return seatingArea;
	}

	public void setSeatingArea(Boolean seatingArea) {
		this.seatingArea = seatingArea;
	}

	public Boolean getPublicToilet() {
		return publicToilet;
	}

	public void setPublicToilet(Boolean publicToilet) {
		this.publicToilet = publicToilet;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return
				"景点名称=" + spotName  +
				"\n景点简介=" + introductionToAttractions +
				"\n景点欢迎度=" + attractionsWelcome +
				"\n有无休息区=" + seatingArea +
				"\n有无公厕=" + publicToilet ;
	}
}
