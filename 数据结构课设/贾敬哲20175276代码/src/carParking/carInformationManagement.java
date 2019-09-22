package carParking;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @创建人
 * @创建时间 15:45 2019/1/1
 * @描述 景区信息管理系统
 */
public class carInformationManagement {

	//设置停车场大小最大为10
	private static final int MAXSIZE = 10;
	//每分钟花费的钱数
	private static final int COSTPERSEC = 4;

	public static Stack<zanlind> parkingLot = new Stack<>();

	public static Queue<zanlind> sidewalk = new LinkedList<>();

	public static Stack<zanlind> tmpPark = new Stack<>();

	//时间差的结果
	private String resOfTimePoor = "";
	//价格的结果
	private long resOfCost = 0;

	public carInformationManagement() {
		addCar("001");
		addCar("002");
		addCar("003");
		addCar("004");
		addCar("005");
		addCar("006");
		addCar("007");
		addCar("008");
		addCar("009");

		addCar("010");

		addCar("011");

		addCar("012");

	}

	/**
	 * @Author:
	 * @Description：打印结果
	 * @Date： 9:26 2019/1/12
	 */
	public void display() {
		String resOfparkingLot = "";
		String resOfsidewalk = "";
		if (parkingLot.empty()) {
			System.out.println("停车场内无车！");
		} else {
			for (zanlind i : parkingLot
					) {
				if (i == null || i.getNumberOfCar() == null) {
					break;
				}
				resOfparkingLot += i.getNumberOfCar() + " ";
			}
			for (zanlind i : sidewalk
					) {
				resOfsidewalk += i.getNumberOfCar() + " ";
			}
			System.out.println("停车场：" + resOfparkingLot);
			System.out.println("便道：" + resOfsidewalk);
		}
	}

	/**
	 * @Author:
	 * @Description：重置所有数据
	 * @Date： 16:03 2019/1/1
	 */
	public void reset() {
		parkingLot = new Stack<>();

		parkingLot.setSize(8);

		sidewalk = new LinkedList<>();

		tmpPark = new Stack<>();
	}

	/**
	 * @Author:
	 * @Description：车辆进停车场
	 * @Date： 16:19 2019/1/1
	 */
	public void addCar(String numberOfCar) {

		//如果停车场不满 直接加入停车场中
		if (parkingLot.size() < MAXSIZE) {
			parkingLot.push(new zanlind(numberOfCar));
		}

		//如果停车场满了 则加入到便道中
		else {
			sidewalk.add(new zanlind(numberOfCar));
		}
	}

	/**
	 * @Author:
	 * @Description：查找车辆
	 * @Date： 18:46 2019/1/1
	 */
	public zanlind find(String numberOfCar) {
		zanlind resCar = null;
		//查找停车场
		for (zanlind tmp : parkingLot
				) {
			if (tmp != null && tmp.getNumberOfCar() != null && tmp.getNumberOfCar() != "" && tmp
					.getNumberOfCar().equals(numberOfCar)) {
				resCar = tmp;
			}
		}
		//查找便道
		for (zanlind tmp : sidewalk
				) {
			if (tmp.getNumberOfCar().equals(numberOfCar)) {
				resCar = tmp;
			}
		}
		return resCar;
	}

	/**
	 * @Author:
	 * @Description：车辆出停车场
	 * @Date： 16:34 2019/1/1
	 */
	public void removeCar(String numberOfCar) {

		zanlind resOfFind = find(numberOfCar);

		if (resOfFind != null) {

			//计算时间差
			String TimePoor = getDatePoor(new Date(), resOfFind.getAr_time());
			resOfTimePoor = TimePoor;
			//获得价格
			long Cost = getCost(new Date(), resOfFind.getAr_time());
			resOfCost = Cost;

			//尝试删除停车场中车牌号为numberOfCar的汽车

			if (parkingLot.removeIf(zanlind -> numberOfCar.equals(zanlind.getNumberOfCar()))) {
				zanlind tmpCar = sidewalk.poll();
				parkingLot.push(tmpCar);
			} else {
				//尝试删除便道中车牌号为numberOfCar的汽车
				if (sidewalk.removeIf(zanlind -> numberOfCar.equals(zanlind.getNumberOfCar()))) {
					resOfCost = 0;
				} else {

					return;
				}
			}

			System.out.println("车牌号" + numberOfCar + " 停留时间为：" + TimePoor + " 费用(¥)" + resOfCost);
		} else {
			System.out.println("错误：该汽车不在停车场和便道中！");
		}
	}

	/**
	 * @Author:
	 * @Description：获得时间差
	 * @Date： 18:53 2019/1/1
	 */
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
		return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
	}

	/**
	 * @Author:
	 * @Description：获得花费
	 * @Date： 18:53 2019/1/1
	 */
	public static long getCost(Date endDate, Date nowDate) {
		long ns = 1000;
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少分钟
		long sec = diff / ns;
		return sec * COSTPERSEC;
	}

}
