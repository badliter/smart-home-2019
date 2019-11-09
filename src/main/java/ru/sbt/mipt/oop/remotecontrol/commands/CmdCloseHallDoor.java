package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.LogWriter;
import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.Command;

public class CmdCloseHallDoor implements Command {
    SmartHome smartHome;

    public CmdCloseHallDoor(SmartHome smartHome){
        this.smartHome = smartHome;
    }
    @Override
    public void doCommand() {
        smartHome.execute(room -> {
            if (room instanceof Room && ((Room) room).getName().equals("hall")) {
                room.execute(door -> {
                    if (door instanceof Door){
                        ((Door)door).setOpen(false);
                        LogWriter.writeToConsoleAboutDoorEvent(false, (Door) door, (Room) room);
                    }
                });
            }
        });
    }
}
