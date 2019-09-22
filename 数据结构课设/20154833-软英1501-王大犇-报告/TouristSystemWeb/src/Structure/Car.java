package Structure;

import java.util.Date;

public class Car {
	private String number; //汽车车号
	private Date reach_time; //汽车到达时间
	
	public Car(String number, Date reach_time) {
		this.number = number;
		this.reach_time = reach_time;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getAr_time() {
		return reach_time;
	}

	public void setAr_time(Date reach_time) {
		this.reach_time = reach_time;
	}

}
