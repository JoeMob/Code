#include "Executer.h"

HWND OpenHWND;
HWND CreateHWND;
HWND CopyHWND;
HWND PasteHWND;
HFONT hFont = CreateFont
(32, 0,    //�߶�20, ��ȡ0��ʾ��ϵͳѡ�����ֵ
	0, 0,    //�ı���б����������б��Ϊ0
	0,    //��׼
	0, 0, 0,        //��б�壬���»��ߣ����л���
	GB2312_CHARSET,    //�ַ���
	OUT_DEFAULT_PRECIS,
	CLIP_DEFAULT_PRECIS,
	DEFAULT_QUALITY,        //һϵ�е�Ĭ��ֵ
	DEFAULT_PITCH | FF_DONTCARE,
	_T("Sarasa Mono Slab SC")    //��������
);


void Executer::OpenFileMenu() {
	CloseEditMenu();
	OpenHWND = CreateWindow(L"Button", L"Open", WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, 20, 220, 200, 100, hWnd, (HMENU)OPENBUTTON, (HINSTANCE)GetWindowLongPtr(hWnd, GWLP_HINSTANCE), NULL);
	SendMessage(OpenHWND, WM_SETFONT, (WPARAM)hFont, true);
	CreateHWND = CreateWindow(L"Button", L"Create", WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, 20, 420, 200, 100, hWnd, (HMENU)CREATEBUTTON, (HINSTANCE)GetWindowLongPtr(hWnd, GWLP_HINSTANCE), NULL);
	SendMessage(CreateHWND, WM_SETFONT, (WPARAM)hFont, true);
}

void Executer::CloseEditMenu() {
	ShowWindow(CopyHWND, SW_HIDE);
	ShowWindow(PasteHWND, SW_HIDE);
}

void Executer::OpenEditMenu() {
	CloseFileMenu();
	CopyHWND = CreateWindow(L"Button", L"Copy", WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, 320, 220, 200, 100, hWnd, (HMENU)COPYBUTTON, (HINSTANCE)GetWindowLongPtr(hWnd, GWLP_HINSTANCE), NULL);
	SendMessage(CopyHWND, WM_SETFONT, (WPARAM)hFont, true);
	PasteHWND = CreateWindow(L"Button", L"Paste", WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, 320, 420, 200, 100, hWnd, (HMENU)PASTEBUTTON, (HINSTANCE)GetWindowLongPtr(hWnd, GWLP_HINSTANCE), NULL);
	SendMessage(PasteHWND, WM_SETFONT, (WPARAM)hFont, true);
}

void Executer::CloseFileMenu() {
	ShowWindow(OpenHWND, SW_HIDE);
	ShowWindow(CreateHWND, SW_HIDE);
}

void Executer::Open() {
	MessageBox(hWnd, L"File opened.", L"Excuted!", MB_OK | MB_ICONINFORMATION);
}

void Executer::UndoOpen() {}

void Executer::Create() {
	MessageBox(hWnd, L"File created.", L"Excuted!", MB_OK | MB_ICONINFORMATION);
}

void Executer::UndoCreate() {}

void Executer::Copy() {
	MessageBox(hWnd, L"Text copyed.", L"Excuted!", MB_OK | MB_ICONINFORMATION);
}

void Executer::UndoCopy() {}

void Executer::Paste() {
	MessageBox(hWnd, L"Text pasted.", L"Excuted!", MB_OK | MB_ICONINFORMATION);
}

void Executer::UndoPaste() {}