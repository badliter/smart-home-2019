package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.alarm.HomeAlarm;
import ru.sbt.mipt.oop.remotecontrol.Command;

public class CmdDangerAlarmTurnOn implements Command {
    private SmartHome smartHome;

    public CmdDangerAlarmTurnOn(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void doCommand() {
        smartHome.execute(homeAlarm -> {
            if (homeAlarm instanceof HomeAlarm){
                ((HomeAlarm) homeAlarm).danger();
            }
        });
    }
}
