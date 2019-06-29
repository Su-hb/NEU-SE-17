// ping.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>
#include "ping.h"
USHORT ping::s_usPacketSeq = 0;

ping::ping() :m_szICMPData(NULL), m_bIsInitSucc(FALSE)
{
	WSADATA WSAData; // 用于初始化套接字环境
	if (WSAStartup(MAKEWORD(1, 1), &WSAData) != 0)
	{
		/*如果初始化不成功则报错，GetLastError()返回发生的错误信息*/
		printf("WSAStartup() failed: %d\n", GetLastError());
		return;
	}
	m_event = WSACreateEvent();
	m_usCurrentProcID = (USHORT)GetCurrentProcessId();
	m_sockRaw = WSASocket(AF_INET, SOCK_RAW, IPPROTO_ICMP, NULL, 0, 0);
	if (m_sockRaw == INVALID_SOCKET)
	{
		std::cerr << "WSASocket() failed:" << WSAGetLastError() << std::endl; 
		// 以一种访问权限不允许的方式做了一个访问套接字的尝试。
	}
	else
	{
		WSAEventSelect(m_sockRaw, m_event, FD_READ);
		m_bIsInitSucc = TRUE;

		m_szICMPData = (char*)malloc(DEF_PACKET_SIZE + sizeof(ICMPHeader));

		if (m_szICMPData == NULL)
		{
			m_bIsInitSucc = FALSE;
		}
	}
}

//计算校验和
USHORT ping::CalCheckSum(USHORT* pBuffer, int size) {
	unsigned long ulCheckSum = 0;
	while (size > 1) {
		ulCheckSum += *pBuffer++;
		size -= sizeof(USHORT);
	}
	if (size)
	{
		ulCheckSum += *(UCHAR*)pBuffer;
	}
	ulCheckSum = (ulCheckSum >> 16) + (ulCheckSum & 0xffff);
	ulCheckSum += (ulCheckSum >> 16);

	return (USHORT)(~ulCheckSum);
}

//计算毫秒级时间差，降低误差
ULONG GetTickCountCalibrate()
{
	static ULONG s_ulFirstCallTick = 0;
	static LONGLONG s_ullFirstCallTickMS = 0;

	SYSTEMTIME systemtime;
	FILETIME filetime;
	GetLocalTime(&systemtime);
	SystemTimeToFileTime(&systemtime, &filetime);
	LARGE_INTEGER liCurrentTime;
	liCurrentTime.HighPart = filetime.dwHighDateTime;
	liCurrentTime.LowPart = filetime.dwLowDateTime;
	LONGLONG llCurrentTimeMS = liCurrentTime.QuadPart / 10000;

	if (s_ulFirstCallTick == 0)
	{
		s_ulFirstCallTick = GetTickCount();
	}
	if (s_ullFirstCallTickMS == 0)
	{
		s_ullFirstCallTickMS = llCurrentTimeMS;
	}

	return s_ulFirstCallTick + (ULONG)(llCurrentTimeMS - s_ullFirstCallTickMS);
}
int main()
{
    std::cout << "Hello World!\n";
}

// 运行程序: Ctrl + F5 或调试 >“开始执行(不调试)”菜单
// 调试程序: F5 或调试 >“开始调试”菜单

// 入门使用技巧: 
//   1. 使用解决方案资源管理器窗口添加/管理文件
//   2. 使用团队资源管理器窗口连接到源代码管理
//   3. 使用输出窗口查看生成输出和其他消息
//   4. 使用错误列表窗口查看错误
//   5. 转到“项目”>“添加新项”以创建新的代码文件，或转到“项目”>“添加现有项”以将现有代码文件添加到项目
//   6. 将来，若要再次打开此项目，请转到“文件”>“打开”>“项目”并选择 .sln 文件
