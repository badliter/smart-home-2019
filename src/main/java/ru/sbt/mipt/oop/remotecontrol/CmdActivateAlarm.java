package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.alarm.HomeAlarm;
import ru.sbt.mipt.oop.home.SmartHome;

public class CmdActivateAlarm implements Command{
    SmartHome smartHome;

    public CmdActivateAlarm(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void doCommand() {
        smartHome.execute(alarm -> {
            if (alarm instanceof HomeAlarm){
                ((HomeAlarm) alarm).activate(((HomeAlarm) alarm).getCode());
            }
        });
    }
}
