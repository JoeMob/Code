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
	executer->Open();
	//CommandStack.push(*this);
}

void OpenCommand::undo() {
	executer->UndoOpen();
	//CommandStack.pop();
}

void CreateCommand::execute() {
	executer->Create();
}

void CreateCommand::undo() {
	executer->UndoCreate();
}

void CopyCommand::execute() {
	executer->Copy();
}

void CopyCommand::undo() {
	executer->UndoCopy();
}

void PasteCommand::execute() {
	executer->Paste();
}

void PasteCommand::undo() {
	executer->UndoPaste();
}

void UndoCommand::execute() {
}

void UndoCommand::undo() {}
