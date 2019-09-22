/**
 * @创建人 贾敬哲
 * @创建时间 14:39 2019/1/1
 * @描述 景区信息管理系统
 */
public class Attractions {

	private int spotID;
	private String spotName;
	private String introductiongToAttractions;
	private String attractionsWelcome;
	private Boolean seatingArea;
	private Boolean publicToilet;

	/**
	 * @Author: 贾敬哲
	 * @Description：全参构造函数
	 * @Date： 14:44 2019/1/1
	 */
	public Attractions(int spotID, String spotName, String introductiongToAttractions,
			String attractionsWelcome, Boolean seatingArea, Boolean publicToilet) {
		this.spotID = spotID;
		this.spotName = spotName;
		this.introductiongToAttractions = introductiongToAttractions;
		this.attractionsWelcome = attractionsWelcome;
		this.seatingArea = seatingArea;
		this.publicToilet = publicToilet;
	}

	/**
	 * @Author: 贾敬哲
	 * @Description：无参构造函数
	 * @Date： 14:44 2019/1/1
	 */
	public Attractions() {
	}
	/**
	 * @Author: 贾敬哲
	 * @Description：set()&&get()
	 * @Date： 14:43 2019/1/1
	 */
	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public String getIntroductiongToAttractions() {
		return introductiongToAttractions;
	}

	public void setIntroductiongToAttractions(String introductiongToAttractions) {
		this.introductiongToAttractions = introductiongToAttractions;
	}

	public String getAttractionsWelcome() {
		return attractionsWelcome;
	}

	public void setAttractionsWelcome(String attractionsWelcome) {
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

	public int getSpotID() {
		return spotID;
	}

	public void setSpotID(int spotID) {
		this.spotID = spotID;
	}

}
