package carParking;

import java.util.Date;

/**
 * @创建人
 * @创建时间 16:10 2019/1/14
 * @描述 景区信息管理系统
 */
public class MycarManagement {

	//设置停车场大小最大为10
	private static final int MAXSIZE = 10;
	//每分钟花费的钱数
	private static final int COSTPERSEC = 4 ;

	public static MyStack myparkingLot = new MyStack(MAXSIZE);

	public static QueueArrays mysidewalk = new QueueArrays();

	public static MyStack mytmpPark = new MyStack(MAXSIZE);

	public static QueueArrays tmpsidewalk = new QueueArrays();

	/**
	 * @Author:
	 * @Description：初始化
	 * @Date： 10:27 2019/1/20
	 */
	public MycarManagement(){
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
	//时间差的结果
	private String resOfTimePoor = "";
	//价格的结果
	private long resOfCost = 0;

	/**
	 * @Author:
	 * @Description：车辆进停车场
	 * @Date： 16:19 2019/1/1
	 */
	public void addCar(String numberOfCar) {
		//如果停车场不满 直接加入停车场中
		if (!myparkingLot.isFull()) {
			myparkingLot.push(new zanlind(numberOfCar));
		}
		//如果停车场满了 则加入到便道中
		else {
			mysidewalk.inQueue(new zanlind(numberOfCar));
		}
	}

	/**
	 * @Author:
	 * @Description：打印结果
	 * @Date： 9:26 2019/1/12
	 */
	public void display() throws Exception {
		String resOfparkingLot = "";

		while (!myparkingLot.isEmpty()) {
			mytmpPark.push(myparkingLot.pop());
		}
		while (!mytmpPark.isEmpty()) {
			zanlind tmp = mytmpPark.pop();
			resOfparkingLot += (tmp.numberOfCar + " ");
			myparkingLot.push(tmp);
		}
		System.out.println("停车场" + resOfparkingLot);
		System.out.print("便道");
		mysidewalk.display();
		//换行
		System.out.println();
	}

	/**
	 * @Author:
	 * @Description：查找车辆
	 * @Date： 18:46 2019/1/1
	 */
	public zanlind find(String numberOfCar) throws Exception {
		zanlind resCar = null;
		while (!myparkingLot.isEmpty()) {
			mytmpPark.push(myparkingLot.pop());
		}
		while (!mytmpPark.isEmpty()) {
			zanlind tmp = mytmpPark.pop();
			if (tmp.numberOfCar.equals(numberOfCar)) {
				return tmp;
			}
			myparkingLot.push(tmp);
		}
		resCar = find(numberOfCar);
		return resCar;
	}

	/**
	 * @Author:
	 * @Description：车辆出停车场
	 * @Date： 16:34 2019/1/1
	 */
	public void removeCar(String numberOfCar) throws Exception {
		zanlind resOfFind = find(numberOfCar);
		if (resOfFind != null&&(!myparkingLot.isEmpty())) {
			//计算时间差
			String TimePoor = getDatePoor(new Date(), resOfFind.getAr_time());
			resOfTimePoor = TimePoor;
			//获得价格
			long Cost = getCost(new Date(), resOfFind.getAr_time());
			resOfCost = Cost;

			//尝试删除停车场中车牌号为numberOfCar的汽车
			while (!myparkingLot.isEmpty()) {
				mytmpPark.push(myparkingLot.pop());
			}
			while (!mytmpPark.isEmpty()) {
				zanlind tmp = mytmpPark.pop();
				if (tmp.numberOfCar.equals(numberOfCar)) {
					continue;
				}
				myparkingLot.push(tmp);
			}

			while (!mysidewalk.isEmpty()) {
				zanlind zan = mysidewalk.outQueue();
				if (zan.numberOfCar.equals(numberOfCar)) {
					resOfCost = 0;
					continue;
				}
				tmpsidewalk.inQueue(zan);
			}
			while (!tmpsidewalk.isEmpty()){
				zanlind zan = tmpsidewalk.outQueue();
				mysidewalk.inQueue(zan);
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

	public static void main(String[] args) {
		MycarManagement m = new MycarManagement();
		try {
			m.display();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addCar("001");
		m.addCar("002");
		m.addCar("003");
		m.addCar("004");
		m.addCar("005");
		m.addCar("006");
		m.addCar("007");
		m.addCar("008");
		m.addCar("009");
		m.addCar("010");
		m.addCar("011");
		m.addCar("012");
		m.addCar("013");
		m.addCar("014");
		m.addCar("015");
		try {
			m.display();
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			m.removeCar("002");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			m.display();
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
