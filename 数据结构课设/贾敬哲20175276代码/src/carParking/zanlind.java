package carParking;

import java.util.Date;

/**
 * @创建人
 * @创建时间 23:55 2019/1/4
 * @描述 景区信息管理系统
 */
public class zanlind{
	/**
	 * @Author:
	 * @Description： 汽车类 包括车牌号、到达时间(用当前时间代替）
	 * @Date： 15:51 2019/1/1
	 */
	String numberOfCar = "";
	Date ar_time ;

	public zanlind(String numberOfCar) {
		this.numberOfCar = numberOfCar;
		this.ar_time = new Date();
	}

	public String getNumberOfCar() {
		return numberOfCar;
	}

	public void setNumberOfCar(String numberOfCar) {
		this.numberOfCar = numberOfCar;
	}

	public Date getAr_time() {
		return ar_time;
	}

	public void setAr_time(Date ar_time) {
		this.ar_time = ar_time;
	}
}
