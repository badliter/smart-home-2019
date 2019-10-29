package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.WorkWithActionableCollection;
import ru.sbt.mipt.oop.home.Door;

import java.util.Collection;

public class OpenDoor implements Action {
    private boolean isOpen;
    private String id;

    public OpenDoor(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    @Override
    public void act(Collection<Actionable> collection) {
        Actionable actionable = new WorkWithActionableCollection(collection).getLast();
        Room room = new WorkWithActionableCollection(collection).getRoom();
        if (actionable.getClass().equals(Door.class) && ((Door) actionable).getId().equals(id)) {
            ((Door) actionable).setOpen(isOpen);
            writeToConsole(isOpen, ((Door) actionable), room);
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
