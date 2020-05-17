package com.joemob;

public class SwitchChannelCommand implements Command {
    private TV television;

    public SwitchChannelCommand(TV television) {
        this.television = television;
    }

    @Override
    public void execute() {
        television.switchChannel();
    }
}
