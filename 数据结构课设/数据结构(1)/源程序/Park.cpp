#include "stdafx.h"
#include "Struct.h"


void EnterPark(stackhead &st, linkqueue &q)   //�Խ���ͣ�����������Ĵ���
{
	int number, time_a;
	cout << "����Ϊ��";
	cin >> number;
	cout << "������ʱ��:";
	cin >> time_a;
	if (st.stacksize_curren<2)
	{
		zanInode e;
		e.number = number;
		e.ar_time = time_a;
		push(st, e);
		cout << " �ó��ѽ���ͣ������: " << st.stacksize_curren << " �ų���" << endl << endl;
	}
	else
	{
		enqueue(q, number, time_a);
		cout << "ͣ�����������ó���ͣ�ڱ���ĵ�" << q.length << "��λ����" << endl;
	}

}
void likai(stackhead &st, stackhead &sl, linkqueue &q) //���뿪�������Ĵ���
{                                                    //st��ջΪͣ������sl��ջΪ������
	int number, time_d, flag = 1, money, arrivaltime;       //qΪ�������
	cout << "����Ϊ��";
	cin >> number;
	cout << "������ʱ�̣�";
	cin >> time_d;
	zanInode e, q_to_s;
	queueptr w;
	while (flag)   //�ҵ�Ҫ�����ĳ���������ͣ����ջ
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
	pop(sl, e);   //����ʱ��ջ�ĵ�һ������Ҫ�뿪�ģ�ȥ����
	while (sl.stacksize_curren) //�ѵ������ĳ�����ͣ����
	{
		pop(sl, e);
		push(st, e);
	}
	if (st.stacksize_curren<2 && q.length != 0) //ͣ�����п�λ������ϵĳ�������ͣ����
	{
		popqueue(q, w);
		q_to_s.ar_time = time_d;
		q_to_s.number = w->number;
		push(st, q_to_s);
		cout << "����Ϊ" << q_to_s.number << "�ĳ��Ѵ�ͨ������ͣ����,���ڵ�ͣ��λΪ:" << st.stacksize_curren << endl << endl;
	}

	cout << "/n           ��       ��" << endl;
	cout << "========================================== ���ƺ�: " << number << endl;
	cout << "===================================================" << endl;
	cout << "| ������ʱ�� | ������ʱ�� | ͣ��ʱ�� | Ӧ����Ԫ��|" << endl;
	cout << "===================================================" << endl;
	cout << "|     " << arrivaltime << "     |      " << time_d << "    |    " << time_d - arrivaltime << "      |       " << money << "     |" << endl;
	cout << "===================================================" << endl << endl;

}

void Car(){
	char choice;
	stackhead spark,stemporary ;   //ͣ��������ʱ��������ջ�Ķ��壻
	linkqueue  line;       //�ŶӶ���
	initstack(spark);       //����ͣ������ջsting
	initstack(stemporary);       //���쵹������ջslinshi
	initqueue(line);         //����������line
	
	ParkMain();   //������();
	while (true)
	{
	
		char flag = true;
		cout << "\t\t��ѡ�� :(A,D,E): ";
		while (flag)
		{
			cin.clear();
			cin.sync();
			cin >> choice;

			switch (choice)
			{
			case 'A':
				EnterPark(spark, line);
				break;         //����������
			case 'D':
				likai(spark, stemporary, line);
				break; //����������
			case 'E':
				returnMainFace();
			}
			flag = false;
		}
		
	}
}


	

