#include "Command.h"

void FileMenuCommand::execute() {
	executer->OpenFileMenu();
}

void FileMenuCommand::undo() {}

void EditMenuCommand::execute() {
	executer->OpenEditMenu();
}

void EditMenuCommand::undo() {}

void OpenCommand::execute() {
	WriteLog("Open");
	executer->Open();
}

void OpenCommand::undo() {
	WriteLog("UndoOpen");
	executer->UndoOpen();
}

void CreateCommand::execute() {
	WriteLog("Create");
	executer->Create();
}

void CreateCommand::undo() {
	WriteLog("UndoCreate");
	executer->UndoCreate();
}

void CopyCommand::execute() {
	WriteLog("Copy");
	executer->Copy();
}

void CopyCommand::undo() {
	WriteLog("UndoCopy");
	executer->UndoCopy();
}

void PasteCommand::execute() {
	WriteLog("Paste");
	executer->Paste();
}

void PasteCommand::undo() {
	WriteLog("UndoPaste");
	executer->UndoPaste();
}

void UndoCommand::execute() {
	if (CommandStack->size() > 0) {
		CommandStack->top()->undo();
		CommandStack->pop();
	}
	else {
		executer->NoUndo();
	}
}

void UndoCommand::undo() {
}

void WriteLog(const char* szLog)
{
	SYSTEMTIME st;
	GetLocalTime(&st);
	FILE* fp;
	fopen_s(&fp, "./log.txt", "at");
	fprintf(fp, "Info: %d:%d:%d:%d ", st.wHour, st.wMinute, st.wSecond, st.wMilliseconds);
	fprintf(fp, szLog);
	fprintf(fp, "\n");
	fclose(fp);
	OutputDebugStringA(szLog);
}
