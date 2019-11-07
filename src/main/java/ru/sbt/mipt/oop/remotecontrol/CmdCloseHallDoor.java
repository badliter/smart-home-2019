package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;

public class CmdCloseHallDoor implements Command{
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
                        writeToConsole(false, (Door) door, (Room) room);
                    }
                });
            }
        });
    }

    private void writeToConsole(boolean isOpen, Door door, Room room) {
        if (isOpen) {
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        } else {
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
        }
    }


}
