package ru.sbt.mipt.oop.home.remotecontrol;

import ru.sbt.mipt.oop.Actionable;

public interface RemoteControl extends Actionable {
    void onButtonPressed(String buttonCode, String rcId);

}
