package ru.sbt.mipt.oop;

import library_v3_7_1.events.CCSensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.DoorSensorEvent;
import ru.sbt.mipt.oop.sensor.LightSensorEvent;

import static ru.sbt.mipt.oop.sensor.SensorEventType.*;

public class AdapterEventHandlerToEventProcess implements library_v3_7_1.events.EventHandler {
    private final ru.sbt.mipt.oop.EventProcess eventProcess;
    private final SmartHome smartHome;

    public AdapterEventHandlerToEventProcess(EventProcess eventProcess, SmartHome smartHome) {
        this.eventProcess = eventProcess;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventProcess.processEvent(smartHome, getSensorEventFromCCSensorEvent(event));
    }

    private SensorEvent getSensorEventFromCCSensorEvent(CCSensorEvent ccSensorEvent) {
        switch (ccSensorEvent.getEventType()) {
            case "LightIsOn": {
                return new LightSensorEvent(LIGHT_ON, ccSensorEvent.getObjectId());
            }
            case "LightIsOff": {
                return new LightSensorEvent(LIGHT_OFF, ccSensorEvent.getObjectId());
            }
            case "DoorIsOpen": {
                return new DoorSensorEvent(DOOR_OPEN, ccSensorEvent.getObjectId());
            }
            case "DoorIsClosed": {
                return new DoorSensorEvent(DOOR_CLOSED, ccSensorEvent.getObjectId());
            }
            default: {
                return null;
            }
        }
    }
}
