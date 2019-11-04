package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.home.SmartHome;

public interface EventHandler {
    void handle(SmartHome smartHome, SensorEvent event);
}
