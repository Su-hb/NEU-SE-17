package Tour;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import Structure.Car;
import Structure.CarNode;
import Structure.Queue;
import Structure.Stack;

public class Park {
	public static int MAX_STACK_SIZE = 9;
	public static int PARKING_FEE = 5;
	
	private Stack<Car> parkStack;//停车场的栈
	private Queue waitlane;//队列的心事表示便道
	private Stack<Car> tempParkStack;//车辆推出时的临时车道
	private Stack<Car> sameStack;//用于判断是否有已存在相同车辆
	
	public Park(int maxParkingNum){
		parkStack = new Stack<Car>(Car.class, maxParkingNum);
		waitlane = new Queue();
		tempParkStack= new Stack<Car>(Car.class, maxParkingNum);
		sameStack = new Stack<Car>(Car.class, maxParkingNum);
	}
	
	/*
	 * car arrive
	 * add the car, with car number
	 */
	public String arriveCar(String number){
		if(parkStack.size()!=0){
			//对停车场遍历判断是否已经有同名的车辆
			while(parkStack.peek()!=null){
				if(parkStack.peek().getNumber().equals(number)){
					break;//如果同名退出
				}
				sameStack.push(parkStack.peek());
				parkStack.pop();
			}
			if(parkStack.size()==0){
				while(sameStack.size()!=0){
				parkStack.push(sameStack.peek());
				sameStack.pop();
			  }
			
			  if(waitlane.existNumber(number)){
				return "{\"exist\":true}";
			  }
			}else{
				while(sameStack.size()!=0){
					parkStack.push(sameStack.peek());
					sameStack.pop();
				}
				return "{\"exist\":true}";
			}
		}
		
		//如果停车场栈满，添加到便道
		if(!parkStack.isFull()){
			parkStack.push(new Car(number,new Date()));//停车场没满，加入到栈
		}else{
			waitlane.add(new Car(number,null));//满了，加入队列
		}
		
		String JSONString="{\"exist\":false,"
				          +"\"parkStack\":" +generateStackJSON(parkStack)
				          +",\"tempParkStack\":" + generateStackJSON(tempParkStack)
				          +",\"waitlane\":" + generateQueueJSON(waitlane) + "}";
	
	   return JSONString;
	}
	
	/*
	 * car leave
	 * delate the car, with car number
	 */
	public String leavePark(String number,Date ltime){
		//判断停车场中是否存在输入车牌号
		if(parkStack.size() == 0){
			return "{\"exist\":null}";
		}else{
			//对停车场栈的遍历
			while(parkStack.peek() != null){
				if(parkStack.peek().getNumber().equals(number)){
					break;
				}
				sameStack.push(parkStack.peek());
				parkStack.pop();
			}
			//停车场中的判断
			if(parkStack.size() == 0){
				while(sameStack.size() != 0){
					parkStack.push(sameStack.peek());
					sameStack.pop();
				}
				return "{\"exist\":false}";
			}
			while(sameStack.size() != 0){
				parkStack.push(sameStack.peek());
				sameStack.pop();
			}
		}
		
		List<String> parkList = new ArrayList<String>(); //存储停车场信息
		List<String> tempParkList = new ArrayList<String>(); //存储暂时停车场信息
		List<String> waitlaneList = new ArrayList<String>(); //存储便道信息
		//第一步：如果不是要出场的汽车则加入临时的栈，pop出来
		while(!parkStack.peek().getNumber().equals(number)){
			parkList.add(generateStackJSON(parkStack));
			tempParkList.add(generateStackJSON(tempParkStack));
			waitlaneList.add(generateQueueJSON(waitlane));
			tempParkStack.push(parkStack.peek());
			parkStack.pop();
		}
		parkList.add(generateStackJSON(parkStack));
		tempParkList.add(generateStackJSON(tempParkStack));
		waitlaneList.add(generateQueueJSON(waitlane));
		
		System.out.println(parkStack.peek().getNumber());
		
		//第二步：计算停车时间和应缴费用，将这两车对象从栈中pop出
		float parkTime = (float) ((ltime.getTime() - parkStack.peek().getAr_time().getTime())/1000.0/60.0);
		int cost = (int) (PARKING_FEE*parkTime);
		parkStack.pop();
		parkList.add(generateStackJSON(parkStack));
		tempParkList.add(generateStackJSON(tempParkStack));
		waitlaneList.add(generateQueueJSON(waitlane));
		
		//第三步，将临时栈里的对象移到停车场栈，按栈出的顺序
		if(tempParkStack.isEmpty()){
			System.out.println("tmp is empty");
		}
		while(!tempParkStack.isEmpty()){
			parkStack.push(tempParkStack.peek());
			tempParkStack.pop();
			parkList.add(generateStackJSON(parkStack));
			tempParkList.add(generateStackJSON(tempParkStack));
			waitlaneList.add(generateQueueJSON(waitlane));
		}
		
		if(waitlane.isEmpty()){
			System.out.println("waitlane is empty");
		}
		
		//第四步判断便道是否有车
		if(!waitlane.isEmpty()){
			waitlane.front().getCar().setAr_time(ltime);
			parkStack.push(waitlane.front().getCar());
			waitlane.pop();
			parkList.add(generateStackJSON(parkStack));
			tempParkList.add(generateStackJSON(tempParkStack));
			waitlaneList.add(generateQueueJSON(waitlane));
		}
		String JSONString = generateDynamicJSONString(parkList, tempParkList, waitlaneList, parkTime, cost);
		
		System.out.println(JSONString);

		return JSONString;
	}
	
	/**
	 * 对栈数据结构中的数据进行json格式化
	 * 
	 *
	 */
	private String generateStackJSON(Stack<Car> stack){
		Car[] cars = stack.getAll();
		List<Car> list = new ArrayList<Car>();
		for(int i=0; i<cars.length; i++){
			if(cars[i] != null){
				list.add(cars[i]);
			}
		}
		
		return JSON.toJSONString(list);
	}
	
	/**
	 * 对队列数据结构中的数据进行json格式化
	 * 
	 */
	private String generateQueueJSON(Queue queue){		
		CarNode carNode = queue.getHeadNode().getNext();
		List<Car> list = new ArrayList<Car>();
		while(carNode != null){
			list.add(carNode.getCar());
			carNode = carNode.getNext();
		}
		
		return JSON.toJSONString(list);
	}
	
	private String generateDynamicJSONString(List<String> parkList, List<String> tempParkList, 
			List<String> waitlaneList, float parkTime, double cost){
        String JSONString = "{" + "\"exist\":true,"
        + "\"length\":" + parkList.size() + ","
        + "\"parkStack\":[";

        for(int i=0; i<parkList.size()-1; i++){
            JSONString += parkList.get(i) + ",";
         }
         JSONString += parkList.get(parkList.size()-1) + "],";

         JSONString += "\"tempParkStack\":[";

         for(int i=0; i<tempParkList.size()-1; i++){
            JSONString += tempParkList.get(i) + ",";
         }
         JSONString += tempParkList.get(tempParkList.size()-1) + "],";

         JSONString += "\"waitlane\":[";

         for(int i=0; i<waitlaneList.size()-1; i++){
            JSONString += waitlaneList.get(i) + ",";
          }
         JSONString += waitlaneList.get(waitlaneList.size()-1) + "],";

         JSONString += "\"parkTime\":" + parkTime + ",";
         JSONString += "\"cost\":" + cost + "}";

         return JSONString;
	}

}
