package ru.sbt.mipt.oop.sensor;

import ru.sbt.mipt.oop.SensorEvent;

public class AlarmSensorEvent implements SensorEvent {
    private final AlarmEventType type;
    private final String code;

    public AlarmSensorEvent(AlarmEventType type, String code) {
        this.type = type;
        this.code = code;
    }

    public AlarmEventType getType() {
        return type;
    }

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
