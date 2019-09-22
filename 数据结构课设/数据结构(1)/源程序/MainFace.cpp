#include "stdafx.h"
#include "Struct.h"

void MainFace()  //主界面
{
	std::cout << "\t\t====================================================\n";
	std::cout << "\t\t\t******欢迎使用景区信息管理系统******\n";
	std::cout << "\t\t\t          ***请选择菜单***\n";
	std::cout << "\t\t====================================================\n\n";
	std::cout << "\t\t\t1、创建景区景点分布图。\n\n";
	std::cout << "\t\t\t2、输出景区景点分布图。\n\n";
	std::cout << "\t\t\t3、输出导游线路图。\n\n";
	std::cout << "\t\t\t4、输出导游线路图中的回路\n\n";
	std::cout << "\t\t\t5、求两个景点间的最短路径和最短距离。\n\n";
	std::cout << "\t\t\t6、输出道路修建规划图；\n\n";
	std::cout << "\t\t\t7、搜索景点，查阅景点信息；\n\n";
	std::cout << "\t\t\t8、排序；\n\n";
	std::cout << "\t\t\t9、停车场车辆进出记录信息。\n\n";
	std::cout << "\t\t\t0、退出系统。\n\n";
	std::cout << "\t\t====================================================";
}

void returnMainFace() //返回主界面
{
	std::cout << "\n\t\t\t按任意键返回主菜单... ...";
	system("cls");
	MainFace();
}