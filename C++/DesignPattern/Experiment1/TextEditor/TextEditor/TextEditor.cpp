// TextEditor.cpp : Defines the entry point for the application.
//

#include "framework.h"
#include "TextEditor.h"
#include <iostream>

#define MAX_LOADSTRING 100

// Global Variables:
HINSTANCE hInst;                                // current instance
WCHAR szTitle[MAX_LOADSTRING];                  // The title bar text
WCHAR szWindowClass[MAX_LOADSTRING];            // the main window class name


// Forward declarations of functions included in this code module:
ATOM                MyRegisterClass(HINSTANCE hInstance);
BOOL                InitInstance(HINSTANCE, int);
LRESULT CALLBACK    WndProc(HWND, UINT, WPARAM, LPARAM);
INT_PTR CALLBACK    About(HWND, UINT, WPARAM, LPARAM);

int APIENTRY wWinMain(_In_ HINSTANCE hInstance,
	_In_opt_ HINSTANCE hPrevInstance,
	_In_ LPWSTR    lpCmdLine,
	_In_ int       nCmdShow)
{
	UNREFERENCED_PARAMETER(hPrevInstance);
	UNREFERENCED_PARAMETER(lpCmdLine);

	// TODO: Place code here.

	// Initialize global strings
	LoadStringW(hInstance, IDS_APP_TITLE, szTitle, MAX_LOADSTRING);
	LoadStringW(hInstance, IDC_TEXTEDITOR, szWindowClass, MAX_LOADSTRING);
	MyRegisterClass(hInstance);

	// Perform application initialization:
	if (!InitInstance(hInstance, nCmdShow))
	{
		return FALSE;
	}

	HACCEL hAccelTable = LoadAccelerators(hInstance, MAKEINTRESOURCE(IDC_TEXTEDITOR));

	MSG msg;

	// Main message loop:
	while (GetMessage(&msg, nullptr, 0, 0))
	{
		if (!TranslateAccelerator(msg.hwnd, hAccelTable, &msg))
		{
			TranslateMessage(&msg);
			DispatchMessage(&msg);
		}
	}

	return (int)msg.wParam;
}



//
//  FUNCTION: MyRegisterClass()
//
//  PURPOSE: Registers the window class.
//
ATOM MyRegisterClass(HINSTANCE hInstance)
{
	WNDCLASSEXW wcex;

	wcex.cbSize = sizeof(WNDCLASSEX);

	wcex.style = CS_HREDRAW | CS_VREDRAW;
	wcex.lpfnWndProc = WndProc;
	wcex.cbClsExtra = 0;
	wcex.cbWndExtra = 0;
	wcex.hInstance = hInstance;
	wcex.hIcon = LoadIcon(hInstance, MAKEINTRESOURCE(IDI_TEXTEDITOR));
	wcex.hCursor = LoadCursor(nullptr, IDC_ARROW);
	wcex.hbrBackground = (HBRUSH)(COLOR_WINDOW + 1);
	//wcex.lpszMenuName = MAKEINTRESOURCEW(IDC_TEXTEDITOR);
	wcex.lpszMenuName = NULL; //No need for origin menu.
	wcex.lpszClassName = szWindowClass;
	wcex.hIconSm = LoadIcon(wcex.hInstance, MAKEINTRESOURCE(IDI_SMALL));

	return RegisterClassExW(&wcex);
}

//
//   FUNCTION: InitInstance(HINSTANCE, int)
//
//   PURPOSE: Saves instance handle and creates main window
//
//   COMMENTS:
//
//        In this function, we save the instance handle in a global variable and
//        create and display the main program window.
//
BOOL InitInstance(HINSTANCE hInstance, int nCmdShow)
{
	hInst = hInstance; // Store instance handle in our global variable

	HWND hWnd = CreateWindowW(szWindowClass, szTitle, WS_OVERLAPPEDWINDOW, 400, 200, 855, 600, nullptr, nullptr, hInstance, nullptr);

	if (!hWnd)
	{
		return FALSE;
	}

	ShowWindow(hWnd, nCmdShow);
	UpdateWindow(hWnd);

	return TRUE;
}

//
//  FUNCTION: WndProc(HWND, UINT, WPARAM, LPARAM)
//
//  PURPOSE: Processes messages for the main window.
//
//  WM_COMMAND  - process the application menu
//  WM_PAINT    - Paint the main window
//  WM_DESTROY  - post a quit message and return
//
//
LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam)
{
	Invoker invoker;
	Command* command;
	Executer* executer = new Executer(hWnd);
	switch (message)
	{
	case WM_COMMAND:
	{
		int wmId = LOWORD(wParam);
		// Parse the menu selections:
		switch (wmId)
		{
		case IDM_ABOUT:
			DialogBox(hInst, MAKEINTRESOURCE(IDD_ABOUTBOX), hWnd, About);
			break;
		case IDM_EXIT:
			DestroyWindow(hWnd);
			break;
		case FILEBUTTON:
			command = new FileMenuCommand(executer);
			invoker.setCommand(command);
			invoker.sendCommand();
			break;
		case EDITBUTTON:
			command = new EditMenuCommand(executer);
			invoker.setCommand(command);
			invoker.sendCommand();
			break;
		case OPENBUTTON:
			command = new OpenCommand(executer);
			invoker.setCommand(command);
			invoker.sendCommand();
			break;
		case CREATEBUTTON:
			command = new CreateCommand(executer);
			invoker.setCommand(command);
			invoker.sendCommand();
			break;
		case COPYBUTTON:
			command = new CopyCommand(executer);
			invoker.setCommand(command);
			invoker.sendCommand();
			break;
		case PASTEBUTTON:
			command = new PasteCommand(executer);
			invoker.setCommand(command);
			invoker.sendCommand();
			break;
		case UNDOBUTTON:
			command = new UndoCommand(executer);
			invoker.setCommand(command);
			invoker.sendCommand();
			break;
		default:
			return DefWindowProc(hWnd, message, wParam, lParam);
		}
	}
	break;
	case WM_PAINT:
	{
		PAINTSTRUCT ps;
		HDC hdc = BeginPaint(hWnd, &ps);
		// TODO: Add any drawing code that uses hdc here...
		HFONT hFont = CreateFont
		(32, 0,    //高度20, 宽取0表示由系统选择最佳值
			0, 0,    //文本倾斜，与字体倾斜都为0
			0,    //标准
			0, 0, 0,        //非斜体，无下划线，无中划线
			GB2312_CHARSET,    //字符集
			OUT_DEFAULT_PRECIS,
			CLIP_DEFAULT_PRECIS,
			DEFAULT_QUALITY,        //一系列的默认值
			DEFAULT_PITCH | FF_DONTCARE,
			_T("Sarasa Mono Slab SC")    //字体名称
		);
		HWND FileHWND = CreateWindow(L"Button", L"File", WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, 20, 20, 200, 100, hWnd, (HMENU)FILEBUTTON, (HINSTANCE)GetWindowLongPtr(hWnd, GWLP_HINSTANCE), NULL);
		SendMessage(FileHWND, WM_SETFONT, (WPARAM)hFont, true);
		HWND EditHWND = CreateWindow(L"Button", L"Edit", WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, 320, 20, 200, 100, hWnd, (HMENU)EDITBUTTON, (HINSTANCE)GetWindowLongPtr(hWnd, GWLP_HINSTANCE), NULL);
		SendMessage(EditHWND, WM_SETFONT, (WPARAM)hFont, true);
		HWND UndoHWND = CreateWindow(L"Button", L"Undo", WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, 620, 20, 200, 100, hWnd, (HMENU)UNDOBUTTON, (HINSTANCE)GetWindowLongPtr(hWnd, GWLP_HINSTANCE), NULL);
		SendMessage(UndoHWND, WM_SETFONT, (WPARAM)hFont, true);
		EndPaint(hWnd, &ps);
	}
	break;
	case WM_DESTROY:
		PostQuitMessage(0);
		break;
	default:
		return DefWindowProc(hWnd, message, wParam, lParam);
	}
	return 0;
}

// Message handler for about box.
INT_PTR CALLBACK About(HWND hDlg, UINT message, WPARAM wParam, LPARAM lParam)
{
	UNREFERENCED_PARAMETER(lParam);
	switch (message)
	{
	case WM_INITDIALOG:
		return (INT_PTR)TRUE;

	case WM_COMMAND:
		if (LOWORD(wParam) == IDOK || LOWORD(wParam) == IDCANCEL)
		{
			EndDialog(hDlg, LOWORD(wParam));
			return (INT_PTR)TRUE;
		}
		break;
	}
	return (INT_PTR)FALSE;
}
