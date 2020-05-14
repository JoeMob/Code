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
	//executer->Undo();
	//CommandStack.pop();
}
