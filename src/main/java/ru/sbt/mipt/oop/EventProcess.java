package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.home.SmartHome;

public interface EventProcess {
    void processEvent(SmartHome smartHome, SensorEvent event);
}
