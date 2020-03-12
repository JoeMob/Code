package com.joemob;

public class Controller {
    private Command turnOnTVCommand;
    private Command turnOffTVCommand;
    private Command switchChannelCommand;

    public void setTurnOnTVCommand(Command turnOnTVCommand) {
        this.turnOnTVCommand = turnOnTVCommand;
    }

    public void setTurnOffTVCommand(Command turnOffTVCommand) {
        this.turnOffTVCommand = turnOffTVCommand;
    }

    public void setSwitchChannelCommand(Command switchChannelCommand) {
        this.switchChannelCommand = switchChannelCommand;
    }

    public void turnOn() {
        turnOnTVCommand.execute();
    }

    public void turnOff() {
        turnOffTVCommand.execute();
    }

    public void switchChannel() {
        switchChannelCommand.execute();
    }
}
