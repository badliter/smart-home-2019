package ru.sbt.mipt.oop.home.remotecontrol.commands;

import ru.sbt.mipt.oop.LogWriter;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.remotecontrol.Command;

public class CmdAllLightTurnOn implements Command {
    boolean turnOn;
    SmartHome smartHome;

    public CmdAllLightTurnOn(SmartHome smartHome, boolean turnOn){
        this.smartHome = smartHome;
        this.turnOn = turnOn;
    }

    @Override
    public void doCommand() {
        smartHome.execute(light -> {
            if (light instanceof Light){
                ((Light)light).setOn(turnOn);
                LogWriter.writeToConsoleAboutLightEvent(turnOn, (Light) light);
            }
        });
    }
}
