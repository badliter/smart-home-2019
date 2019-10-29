package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.command.SensorCommand;

public class CommandSender {
    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
