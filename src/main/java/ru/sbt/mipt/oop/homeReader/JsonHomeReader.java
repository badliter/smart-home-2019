package ru.sbt.mipt.oop.homeReader;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.HomeReader;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.alarm.HomeAlarm;
import ru.sbt.mipt.oop.home.remotecontrol.*;
import ru.sbt.mipt.oop.home.remotecontrol.commands.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class JsonHomeReader implements HomeReader {
    @Override
    public SmartHome readHome() {
        Gson gson = new Gson();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SmartHome smartHome = new SmartHome(gson.fromJson(json, SmartHome.class), new HomeAlarm(gson.fromJson(json, SmartHome.class).getHomeAlarm().getCode()));
        createCollectionRC(smartHome);
        return smartHome;
    }

    private void createCollectionRC(SmartHome smartHome) {
        HomeRemoteControl rc1 = new HomeRemoteControl("1");
        rc1.addCommand("A", new CmdActivateAlarm(smartHome));
        rc1.addCommand("B", new CmdAllLightTurnOn(smartHome, true));
        rc1.addCommand("C", new CmdAllLightTurnOn(smartHome, false));
        rc1.addCommand("D", new CmdCloseHallDoor(smartHome));
        rc1.addCommand("1", new CmdDangerAlarmTurnOn(smartHome));
        rc1.addCommand("2", new CmdTurnOnLightInHall(smartHome));
        smartHome.addRemoteControl(rc1);

        HomeRemoteControl rc2 = new HomeRemoteControl("2");
        rc2.addCommand("C", new CmdActivateAlarm(smartHome));
        rc2.addCommand("D", new CmdAllLightTurnOn(smartHome, true));
        rc2.addCommand("1", new CmdAllLightTurnOn(smartHome, false));
        rc2.addCommand("2", new CmdCloseHallDoor(smartHome));
        rc2.addCommand("3", new CmdDangerAlarmTurnOn(smartHome));
        rc2.addCommand("4", new CmdTurnOnLightInHall(smartHome));
        smartHome.addRemoteControl(rc2);
    }
}