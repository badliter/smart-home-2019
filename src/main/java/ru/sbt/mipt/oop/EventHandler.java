package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;

public interface EventHandler {
    void handle(SmartHome smartHome, SensorEvent event);
}
