package ru.sbt.mipt.oop;

public class DoorIsOpen implements Action {
    private boolean isOpen;
    private String id;
    private Room room;
    private SmartHome smartHome;

    public DoorIsOpen(boolean isOpen, String id) {
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

    @Override
    public SmartHome getSmartHome() {
        return smartHome;
    }

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private void writeToConsole(boolean isOpen, Door door) {
        if (isOpen) {
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        } else {
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
        }
    }

    @Override
    public Room getRoom() {
        return room;
    }

    @Override
    public void setRoom(Room room) {
        this.room = room;
    }
}
