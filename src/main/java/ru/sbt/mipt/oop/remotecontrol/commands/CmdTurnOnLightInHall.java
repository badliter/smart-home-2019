package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.LogWriter;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.Command;

public class CmdTurnOnLightInHall implements Command {
    SmartHome smartHome;

    public CmdTurnOnLightInHall(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void doCommand() {
        smartHome.execute(room -> {
            if (room instanceof Room && ((Room)room).getName().equals("hall")){
                room.execute(light -> {
                    if (light instanceof Light){
                        ((Light)light).setOn(false);
                        LogWriter.writeToConsoleAboutLightEvent(false, (Light) light, (Room) room);
                    }
                });
            }
        });
    }
}



