package ru.sbt.mipt.oop;

public interface Action {
    void act(Actionable actionable);
    SmartHome getSmartHome();
    void setSmartHome(SmartHome smartHome);
    Room getRoom();
    void setRoom(Room room);
}
