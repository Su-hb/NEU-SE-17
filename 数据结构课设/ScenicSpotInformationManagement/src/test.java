import car.zanlind;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class test{
	//设置停车场大小最大为10
	private static final int MAXSIZE = 10;
	//每分钟花费的钱数
	private static final int COSTPERSEC = 100;

	public static Stack<zanlind> parkingLot = new Stack<>();

	public static Queue<zanlind> sidewalk = new LinkedList<>();

	public static Stack<zanlind> tmpPark = new Stack<>();
	//时间差的结果
	private String resOfTimePoor = "";
	//价格的结果
	private long resOfCost = 0;

	public static String getDatePoor(Date endDate, Date nowDate) {

		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		long sec = diff % nd % nh % nm / ns;
		return day + "天" + hour + "小时" + min + "分钟"+sec+"秒";
	}


	public zanlind find(String numberOfCar){
		zanlind resCar = null;
		//查找停车场
		for (zanlind tmp:parkingLot
				) {
			if(tmp.getNumberOfCar() == numberOfCar){
				resCar = tmp;
			}
		}
		//查找便道
		for (zanlind tmp:sidewalk
				) {
			if(tmp.getNumberOfCar() == numberOfCar){
				resCar = tmp;
			}
		}

		return resCar;

	}
	/**
	 * @Author: 贾敬哲
	 * @Description：获得花费
	 * @Date： 18:53 2019/1/1
	 */
	public static long getCost(Date endDate, Date nowDate){
		long ns = 1000;
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少分钟
		long sec = diff / ns;
		return sec*COSTPERSEC;
	}
	public void removeCar(String numberOfCar){

		zanlind resOfFind = find(numberOfCar) ;
		if (resOfFind != null) {

			//计算时间差
			String TimePoor = getDatePoor(new Date(),resOfFind.getAr_time());
			resOfTimePoor = TimePoor;
			//获得价格
			long Cost = getCost(new Date(),resOfFind.getAr_time());
			resOfCost = Cost;

			//尝试删除停车场中车牌号为numberOfCar的汽车
			if (parkingLot.removeIf(zanlind -> zanlind.getNumberOfCar() == numberOfCar)) {
				zanlind tmpCar = sidewalk.poll();
				parkingLot.push(tmpCar);
			} else {
				//尝试删除便道中车牌号为numberOfCar的汽车
				if (sidewalk.removeIf(zanlind -> zanlind.getNumberOfCar() == numberOfCar)) {

				} else {
					return;
				}
			}
		}
	}
	//测试：
	public void addCar(String numberOfCar)
	{


		//如果停车场不满 直接加入停车场中
		if(parkingLot.size() < MAXSIZE){
			parkingLot.push(new zanlind(numberOfCar));
		}

		//如果停车场满了 则加入到便道中
		else{
			sidewalk.add(new zanlind(numberOfCar));
		}

	}
	public void display()
	{
		String resOfparkingLot = new String();
		String resOfsidewalk = new String();
		for (zanlind i:parkingLot
				) {
			resOfparkingLot+=i.getNumberOfCar()+" ";
		}
		for (zanlind i:sidewalk
				) {
			resOfsidewalk+=i.getNumberOfCar()+" ";
		}
		System.out.println("停车场"+resOfparkingLot);
		System.out.println("便道"+resOfsidewalk);
	}
	public static void main(String[] args) {
		test car = new test();




		car.display();



		car.display();
		System.out.println(car.resOfCost);
		System.out.println(car.resOfTimePoor);
	}
}