package ru.sbt.mipt.oop.sensor;

import ru.sbt.mipt.oop.SensorEvent;

public class AlarmSensorEvent implements SensorEvent {
    private final SensorEventType type;
    private final String code;

    public AlarmSensorEvent(SensorEventType type, String code) {
        this.type = type;
        this.code = code;
    }

    @Override
    public SensorEventType getType() {
        return type;
    }

    @Override
    public String getObjectId() {
        return null;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "AlarmSensorEvent{" +
                "type=" + type +
                ", code='" + code + '\'' +
                '}';
    }
}
