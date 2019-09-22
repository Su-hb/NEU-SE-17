#include "stdafx.h"
#include "Struct.h"


void EnterPark(stackhead &st, linkqueue &q)   //对进入停车场的汽车的处理；
{
	int number, time_a;
	cout << "车牌为：";
	cin >> number;
	cout << "进场的时刻:";
	cin >> time_a;
	if (st.stacksize_curren<2)
	{
		zanInode e;
		e.number = number;
		e.ar_time = time_a;
		push(st, e);
		cout << " 该车已进入停车场在: " << st.stacksize_curren << " 号车道" << endl << endl;
	}
	else
	{
		enqueue(q, number, time_a);
		cout << "停车场已满，该车先停在便道的第" << q.length << "个位置上" << endl;
	}

}
void likai(stackhead &st, stackhead &sl, linkqueue &q) //对离开的汽车的处理；
{                                                    //st堆栈为停车场，sl堆栈为倒车场
	int number, time_d, flag = 1, money, arrivaltime;       //q为便道队列
	cout << "车牌为：";
	cin >> number;
	cout << "出场的时刻：";
	cin >> time_d;
	zanInode e, q_to_s;
	queueptr w;
	while (flag)   //找到要开出的车，并弹出停车场栈
	{
		pop(st, e);
		push(sl, e);
		if (e.number == number)
		{
			flag = 0;
			money = (time_d - e.ar_time) * 2;
			arrivaltime = e.ar_time;
		}
	}
	pop(sl, e);   //把临时堆栈的第一辆车（要离开的）去掉；
	while (sl.stacksize_curren) //把倒车场的车倒回停车场
	{
		pop(sl, e);
		push(st, e);
	}
	if (st.stacksize_curren<2 && q.length != 0) //停车场有空位，便道上的车开进入停车场
	{
		popqueue(q, w);
		q_to_s.ar_time = time_d;
		q_to_s.number = w->number;
		push(st, q_to_s);
		cout << "车牌为" << q_to_s.number << "的车已从通道进入停车场,所在的停车位为:" << st.stacksize_curren << endl << endl;
	}

	cout << "/n           收       据" << endl;
	cout << "========================================== 车牌号: " << number << endl;
	cout << "===================================================" << endl;
	cout << "| 进车场时刻 | 出车场时刻 | 停留时间 | 应付（元）|" << endl;
	cout << "===================================================" << endl;
	cout << "|     " << arrivaltime << "     |      " << time_d << "    |    " << time_d - arrivaltime << "      |       " << money << "     |" << endl;
	cout << "===================================================" << endl << endl;

}

void Car(){
	char choice;
	stackhead spark,stemporary ;   //停车场和临时倒车场堆栈的定义；
	linkqueue  line;       //排队队列
	initstack(spark);       //构造停车场堆栈sting
	initstack(stemporary);       //构造倒车场堆栈slinshi
	initqueue(line);         //构造便道队列line
	
	ParkMain();   //主界面();
	while (true)
	{
	
		char flag = true;
		cout << "\t\t请选择 :(A,D,E): ";
		while (flag)
		{
			cin.clear();
			cin.sync();
			cin >> choice;

			switch (choice)
			{
			case 'A':
				EnterPark(spark, line);
				break;         //汽车进车场
			case 'D':
				likai(spark, stemporary, line);
				break; //汽车出车场
			case 'E':
				returnMainFace();
			}
			flag = false;
		}
		
	}
}


	

