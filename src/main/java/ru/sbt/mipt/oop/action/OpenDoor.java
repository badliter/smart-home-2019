package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.home.Door;

public class OpenDoor implements Action {
    private boolean isOpen;
    private String id;

    public OpenDoor(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    @Override
    public void act(Actionable actionable) {
        if (actionable.getClass().equals(Door.class) && ((Door) actionable).getId().equals(id)) {
            ((Door) actionable).setOpen(isOpen);
            writeToConsole(isOpen, ((Door) actionable));
        }
    }

    private void writeToConsole(boolean isOpen, Door door) {
        if (isOpen) {
            System.out.println("Door " + door.getId() + " was opened.");
        } else {
            System.out.println("Door " + door.getId() + " was closed.");
        }
    }
}
