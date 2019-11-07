package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.action.AllLightTurnOff;
import ru.sbt.mipt.oop.home.SmartHome;

public class CmdAllLightTurnOff implements Command {
    SmartHome smartHome;

    public CmdAllLightTurnOff(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void doCommand() {
        smartHome.execute(new AllLightTurnOff());
    }
}
