#pragma once
#include "Command.h"

class Invoker {
public:
	void setCommand(Command* command) {
		this->command = command;
	}
	void sendCommand() {
		command->execute();
	}
private:
	Command* command;
};
