package ru.sbt.mipt.oop.sensor;

import ru.sbt.mipt.oop.SensorEvent;

public class DoorSensorEvent implements SensorEvent {
    private final DoorEventType type;
    private final String objectId;

    public DoorSensorEvent(DoorEventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    public DoorEventType getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "DoorSensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
