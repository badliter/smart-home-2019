package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;

public class OpenDoor implements Action {
    private boolean isOpen;
    private String id;

    public OpenDoor(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    @Override
    public void act(Actionable room) {
        if (room instanceof Room) {
            room.execute(door -> {
                if (door instanceof Door && ((Door) door).getId().equals(id)) {
                    writeToConsole(isOpen, (Door) door, (Room) room);
                }
            });
        }
    }

    private void writeToConsole(boolean isOpen, Door door, Room room) {
        if (isOpen) {
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        } else {
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
        }
    }
}
