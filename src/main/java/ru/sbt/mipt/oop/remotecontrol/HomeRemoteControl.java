package ru.sbt.mipt.oop.remotecontrol;

import java.util.HashMap;

public class HomeRemoteControl implements RemoteControl {
    private HashMap<String, Command> map = new HashMap<>();
    private String rcId;

    public HomeRemoteControl(String rcId){
        this.rcId = rcId;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (rcId.equals(this.rcId) && map.containsKey(buttonCode)){
            map.get(buttonCode).doCommand();
        }
    }

    public void addCommand(String buttonCode, Command command){
        map.put(buttonCode, command);
    }
}
