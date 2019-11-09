package ru.sbt.mipt.oop.remotecontrolreader;

import ru.sbt.mipt.oop.RemoteControlReader;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.HomeRemoteControl;
import ru.sbt.mipt.oop.remotecontrol.RemoteControl;
import ru.sbt.mipt.oop.remotecontrol.commands.*;

import java.util.HashMap;

public class RandomRemoteControlReader implements RemoteControlReader {
    private final SmartHome smartHome;

    public RandomRemoteControlReader(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public HashMap<String, RemoteControl> readRemoteControl() {
        HashMap<String, RemoteControl> mapRC = new HashMap<>();
        HomeRemoteControl rc1 = new HomeRemoteControl();
        rc1.addCommand("A", new CmdActivateAlarm(smartHome));
        rc1.addCommand("B", new CmdAllLightTurnOn(smartHome, true));
        rc1.addCommand("C", new CmdAllLightTurnOn(smartHome, false));
        rc1.addCommand("D", new CmdCloseHallDoor(smartHome));
        rc1.addCommand("1", new CmdDangerAlarmTurnOn(smartHome));
        rc1.addCommand("2", new CmdTurnOnLightInHall(smartHome));
        mapRC.put("1", rc1);

        HomeRemoteControl rc2 = new HomeRemoteControl();
        rc2.addCommand("C", new CmdActivateAlarm(smartHome));
        rc2.addCommand("D", new CmdAllLightTurnOn(smartHome, true));
        rc2.addCommand("1", new CmdAllLightTurnOn(smartHome, false));
        rc2.addCommand("2", new CmdCloseHallDoor(smartHome));
        rc2.addCommand("3", new CmdDangerAlarmTurnOn(smartHome));
        rc2.addCommand("4", new CmdTurnOnLightInHall(smartHome));
        mapRC.put("2", rc2);
        return mapRC;
    }
}
