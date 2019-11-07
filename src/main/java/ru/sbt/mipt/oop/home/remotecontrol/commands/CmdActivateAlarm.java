package ru.sbt.mipt.oop.home.remotecontrol.commands;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.alarm.HomeAlarm;
import ru.sbt.mipt.oop.home.remotecontrol.Command;

public class CmdActivateAlarm implements Command {
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
