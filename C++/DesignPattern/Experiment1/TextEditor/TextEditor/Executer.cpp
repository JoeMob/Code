#include "Executer.h"
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
	HWND OpenHWND = CreateWindow(L"Button", L"Open", WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, 20, 220, 200, 100, hWnd, (HMENU)OPENBUTTON, (HINSTANCE)GetWindowLongPtr(hWnd, GWLP_HINSTANCE), NULL);
	SendMessage(OpenHWND, WM_SETFONT, (WPARAM)hFont, true);
	HWND CreateHWND = CreateWindow(L"Button", L"Create", WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, 20, 420, 200, 100, hWnd, (HMENU)CREATEBUTTON, (HINSTANCE)GetWindowLongPtr(hWnd, GWLP_HINSTANCE), NULL);
	SendMessage(CreateHWND, WM_SETFONT, (WPARAM)hFont, true);
}

void Executer::CloseEditMenu() {

}

void Executer::OpenEditMenu() {
	CloseFileMenu();
	HWND CopyHWND = CreateWindow(L"Button", L"Open", WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, 320, 220, 200, 100, this->hWnd, (HMENU)OPENBUTTON, (HINSTANCE)GetWindowLongPtr(this->hWnd, GWLP_HINSTANCE), NULL);
	SendMessage(CopyHWND, WM_SETFONT, (WPARAM)hFont, true);
	HWND PasteHWND = CreateWindow(L"Button", L"Create", WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, 320, 420, 200, 100, hWnd, (HMENU)CREATEBUTTON, (HINSTANCE)GetWindowLongPtr(hWnd, GWLP_HINSTANCE), NULL);
	SendMessage(PasteHWND, WM_SETFONT, (WPARAM)hFont, true);
}

void Executer::CloseFileMenu() {}
void Executer::Open() {}
void Executer::Create() {}
void Executer::Copy() {}
void Executer::Paste() {}