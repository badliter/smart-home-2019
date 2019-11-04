package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.sensor.SensorEventType;

public interface SensorEvent {
    SensorEventType getType();
    String getObjectId();
}
