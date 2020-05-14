#pragma once
#include "Executer.h"
#include <stack>

class Command {
public:
	virtual void execute() = 0;
	virtual void undo() = 0;
	static std::stack<Command> CommandStack;
};

class FileMenuCommand :public Command {
public:
	FileMenuCommand(Executer* _executer) :executer(_executer) {}
	void execute();
	void undo();
private:
	Executer* executer;
};

class EditMenuCommand :public Command {
public:
	EditMenuCommand(Executer* _executer) :executer(_executer) {}
	void execute();
	void undo();
private:
	Executer* executer;
};

class OpenCommand :public Command {
public:
	OpenCommand(Executer* _executer) :executer(_executer) {}
	void execute();
	void undo();
private:
	Executer* executer;
};

class CreateCommand :public Command {
public:
	CreateCommand(Executer* _executer) :executer(_executer) {}
	void execute();
	void undo();
private:
	Executer* executer;
};

class CopyCommand :public Command {
public:
	CopyCommand(Executer* _executer) :executer(_executer) {}
	void execute();
	void undo();
private:
	Executer* executer;
};

class PasteCommand :public Command {
public:
	PasteCommand(Executer* _executer) :executer(_executer) {}
	void execute();
	void undo();
private:
	Executer* executer;
};

class UndoCommand :public Command {
public:
	UndoCommand(Executer* _executer) :executer(_executer) {}
	void execute();
	void undo();
private:
	Executer* executer;
};
