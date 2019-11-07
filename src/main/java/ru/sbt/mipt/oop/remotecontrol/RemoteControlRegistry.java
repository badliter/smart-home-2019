package ru.sbt.mipt.oop.remotecontrol;

import java.util.HashMap;

public class RemoteControlRegistry {
    private static HashMap<String,RemoteControl> mapOfRemoteControl;

    public void registerRemoteControl(RemoteControl remoteControl, String rcId){
        mapOfRemoteControl.put(rcId, remoteControl);
    }
}
