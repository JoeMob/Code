package com.joemob;

public class User {

    public static void main(String[] args) {
        TV television = new TV();

        Command turnOnTVCommand = new TurnOnTVCommand(television);
        Command turnOffTVCommand = new TurnOffTVCommand(television);
        Command switchChannel = new SwitchChannelCommand(television);

        Controller controller = new Controller();

        controller.setTurnOnTVCommand(turnOnTVCommand);
        controller.setTurnOffTVCommand(turnOffTVCommand);
        controller.setSwitchChannelCommand(switchChannel);

        controller.turnOn();
        controller.switchChannel();
        controller.turnOff();
    }
}
