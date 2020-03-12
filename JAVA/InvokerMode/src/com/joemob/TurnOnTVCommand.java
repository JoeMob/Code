package com.joemob;

public class TurnOnTVCommand implements Command {
    private TV television;

    public TurnOnTVCommand(TV televison) {
        this.television = televison;
    }

    @Override
    public void execute() {
        television.turnOn();
    }
}
