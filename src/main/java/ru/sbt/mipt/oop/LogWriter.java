package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;

public class LogWriter {
    public static void writeToConsoleAboutDoorEvent(boolean isOpen, Door door, Room room) {
        if (isOpen) {
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        } else {
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
        }
    }

    public static void writeToConsoleAboutLightEvent(boolean turnOn, Light light, Room room) {
        if (turnOn) {
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
        } else {
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
        }
    }
}
