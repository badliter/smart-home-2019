package ru.sbt.mipt.oop.remotecontrol;

import java.util.HashMap;

public class HomeRemoteControl implements RemoteControl {
    private HashMap<String, Command> map;

    public HomeRemoteControl(){
        map = new HashMap<>();
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (map.containsKey(buttonCode)){
            map.get(buttonCode).doCommand();
        }
    }

    public void addCommand(String buttonCode, Command command){
        map.put(buttonCode, command);
    }
}
