#pragma once
#include "Command.h"

class Invoker {
public:
	void setCommand(Command* NewCommand) {
		command = NewCommand;
	}
	void sendCommand() {
		command->execute();
	}
private:
	Command* command;
};
