package ru.sbt.mipt.oop.sensor;

import ru.sbt.mipt.oop.SensorEvent;

public class LightSensorEvent implements SensorEvent {
    private final LightEventType type;
    private final String objectId;

    public LightSensorEvent(LightEventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    public LightEventType getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "LightSensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
