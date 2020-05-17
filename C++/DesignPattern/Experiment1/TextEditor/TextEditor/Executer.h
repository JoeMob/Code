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

	void UndoOpen();

	void Create();

	void UndoCreate();

	void Copy();

	void UndoCopy();

	void Paste();

	void UndoPaste();

	void NoUndo();

	HWND hWnd;
};
