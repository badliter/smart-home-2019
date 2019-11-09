package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.remotecontrol.RemoteControl;

import java.util.HashMap;

public interface RemoteControlReader {
    HashMap<String, RemoteControl> readRemoteControl();
}
