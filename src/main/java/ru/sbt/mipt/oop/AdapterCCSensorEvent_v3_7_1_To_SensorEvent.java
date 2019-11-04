package ru.sbt.mipt.oop;

import library_v3_7_1.events.CCSensorEvent;
import ru.sbt.mipt.oop.sensor.LightSensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import static ru.sbt.mipt.oop.sensor.SensorEventType.*;

public class AdapterCCSensorEvent_v3_7_1_To_SensorEvent implements SensorEvent{
    private final CCSensorEvent ccSensorEvent;

    public AdapterCCSensorEvent_v3_7_1_To_SensorEvent(CCSensorEvent ccSensorEvent) {
        this.ccSensorEvent = ccSensorEvent;
    }

    @Override
    public SensorEventType getType() {
        switch (ccSensorEvent.getEventType()){
            case "LightIsOn": {
                return LIGHT_ON;
            }
            case "LightIsOff": {
                return LIGHT_OFF;
            }
            case "DoorIsOpen": {
                return DOOR_OPEN;
            }
            case "DoorIsClosed": {
                return  DOOR_CLOSED;
            }
            default:{
                return null;
            }
        }
    }

    @Override
    public String getObjectId() {
        return ccSensorEvent.getObjectId();
    }

    @Override
    public String toString() {
        String answer = "";
        if (ccSensorEvent.getEventType().contains("Light")){
            answer = "LightSensorEvent{";
        } else {
            answer = "DoorSensorEvent{";
        }
        return answer +
                "type=" + getType() +
                ", objectId='" + getObjectId() + '\'' +
                '}';
    }
}
