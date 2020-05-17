package com.joemob;

public class TurnOffTVCommand implements Command {
    private TV television;

    public TurnOffTVCommand(TV television) {
        this.television = television;
    }

    @Override
    public void execute() {
        television.turnOff();
    }
}
