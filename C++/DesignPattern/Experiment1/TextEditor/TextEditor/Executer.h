#pragma once
#include "framework.h"
#include "Resource.h"



class Executer {
public:
	Executer(HWND _hWnd) :hWnd(_hWnd) {}

	void OpenFileMenu();

	void OpenEditMenu();

	void CloseFileMenu();

	void CloseEditMenu();

	void Open();

	void Create();

	void Copy();

	void Paste();

	void Undo();

	HWND hWnd;
};
