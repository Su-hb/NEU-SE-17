#include <windows.h>
#include <stdio.h>

LRESULT CALLBACK MyProc(
  HWND hwnd,      // handle to window
  UINT uMsg,      // message identifier
  WPARAM wParam,  // first message parameter
  LPARAM lParam   // second message parameter
);

int WINAPI WinMain(
  HINSTANCE hInstance,      // handle to current instance
  HINSTANCE hPrevInstance,  // handle to previous instance
  LPSTR lpCmdLine,          // command line
  int nCmdShow              // show state
)
{ 
	WNDCLASS wndcls;
	wndcls.cbClsExtra=0;
	wndcls.cbWndExtra=0;
	wndcls.hbrBackground=(HBRUSH)GetStockObject(WHITE_BRUSH);
	wndcls.hCursor=LoadCursor(NULL,IDC_ARROW);
	wndcls.hIcon=LoadIcon(NULL,IDI_QUESTION);
	wndcls.hInstance = hInstance;
	wndcls.lpfnWndProc=MyProc;
	wndcls.lpszClassName="SoftwareCollege";
	wndcls.lpszMenuName=NULL;
	wndcls.style=CS_HREDRAW | CS_VREDRAW;

	RegisterClass(&wndcls);

	HWND hwnd;
	hwnd=CreateWindow("SoftwareCollege","软件学院",WS_OVERLAPPEDWINDOW ,
		0,0,600,400,NULL,NULL,hInstance,NULL);

	ShowWindow(hwnd,SW_SHOWNORMAL);
	UpdateWindow(hwnd);

	MSG msg;

	while(GetMessage(&msg,NULL,0,0))
	{
		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}
	return 0;
}

LRESULT CALLBACK MyProc(
  HWND hwnd,      // handle to window
  UINT uMsg,      // message identifier
  WPARAM wParam,  // first message parameter
  LPARAM lParam   // second message parameter
)
{
	switch(uMsg)
	{
	case WM_CHAR:
		char szChar[20];
		sprintf(szChar,"the pressed char is %c",wParam);
		MessageBox(hwnd,szChar,"东北大学",MB_OK|MB_ICONINFORMATION);
		break;

	case WM_LBUTTONDOWN:
		MessageBox(hwnd,"mouse clicked","neu",0);
		HDC hdc;
		hdc=GetDC(hwnd);
		TextOut(hdc,0,50,"C++与MFC语言学习",strlen("C++与MFC语言学习"));
		ReleaseDC(hwnd,hdc);
		break;

	case WM_PAINT:
		HDC hDC;
		PAINTSTRUCT ps;
		hDC=BeginPaint(hwnd,&ps);
		TextOut(hDC,0,5,"东北大学",strlen("东北大学"));
		EndPaint(hwnd,&ps);
		break;

	case WM_CLOSE:
		if(IDYES==MessageBox(hwnd,"是否真的结束？","neu",MB_YESNO|MB_ICONQUESTION))
		{
			DestroyWindow(hwnd);
		}
		break;

	case WM_DESTROY:
		PostQuitMessage(0);
		break;

	default:
		return DefWindowProc(hwnd,uMsg,wParam,lParam);
	}
	return 0;
}