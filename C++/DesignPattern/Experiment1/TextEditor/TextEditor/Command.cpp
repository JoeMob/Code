#include "Command.h"

void FileMenuCommand::exectue() {
	executer->OpenFileMenu();
}

void FileMenuCommand::undo() {}

void EditMenuCommand::exectue() {
	executer->OpenEditMenu();
}

void EditMenuCommand::undo() {}
