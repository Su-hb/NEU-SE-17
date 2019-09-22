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
	
	private Stack<Car> parkStack;//ͣ������ջ
	private Queue waitlane;//���е����±�ʾ���
	private Stack<Car> tempParkStack;//�����Ƴ�ʱ����ʱ����
	private Stack<Car> sameStack;//�����ж��Ƿ����Ѵ�����ͬ����
	
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
			//��ͣ���������ж��Ƿ��Ѿ���ͬ���ĳ���
			while(parkStack.peek()!=null){
				if(parkStack.peek().getNumber().equals(number)){
					break;//���ͬ���˳�
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
		
		//���ͣ����ջ������ӵ����
		if(!parkStack.isFull()){
			parkStack.push(new Car(number,new Date()));//ͣ����û�������뵽ջ
		}else{
			waitlane.add(new Car(number,null));//���ˣ��������
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
		//�ж�ͣ�������Ƿ�������복�ƺ�
		if(parkStack.size() == 0){
			return "{\"exist\":null}";
		}else{
			//��ͣ����ջ�ı���
			while(parkStack.peek() != null){
				if(parkStack.peek().getNumber().equals(number)){
					break;
				}
				sameStack.push(parkStack.peek());
				parkStack.pop();
			}
			//ͣ�����е��ж�
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
		
		List<String> parkList = new ArrayList<String>(); //�洢ͣ������Ϣ
		List<String> tempParkList = new ArrayList<String>(); //�洢��ʱͣ������Ϣ
		List<String> waitlaneList = new ArrayList<String>(); //�洢�����Ϣ
		//��һ�����������Ҫ�����������������ʱ��ջ��pop����
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
		
		//�ڶ���������ͣ��ʱ���Ӧ�ɷ��ã��������������ջ��pop��
		float parkTime = (float) ((ltime.getTime() - parkStack.peek().getAr_time().getTime())/1000.0/60.0);
		int cost = (int) (PARKING_FEE*parkTime);
		parkStack.pop();
		parkList.add(generateStackJSON(parkStack));
		tempParkList.add(generateStackJSON(tempParkStack));
		waitlaneList.add(generateQueueJSON(waitlane));
		
		//������������ʱջ��Ķ����Ƶ�ͣ����ջ����ջ����˳��
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
		
		//���Ĳ��жϱ���Ƿ��г�
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
	 * ��ջ���ݽṹ�е����ݽ���json��ʽ��
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
	 * �Զ������ݽṹ�е����ݽ���json��ʽ��
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
