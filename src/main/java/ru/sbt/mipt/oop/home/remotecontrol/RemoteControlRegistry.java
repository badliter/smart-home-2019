package ru.sbt.mipt.oop.home.remotecontrol;

import java.util.HashMap;

public class RemoteControlRegistry {
    private static HashMap<String,RemoteControl> mapOfRemoteControl;

    public void registerRemoteControl(RemoteControl remoteControl, String rcId){
        mapOfRemoteControl.put(rcId, remoteControl);
    }
}
