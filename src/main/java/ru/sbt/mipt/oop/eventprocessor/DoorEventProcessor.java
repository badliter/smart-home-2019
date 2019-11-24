package ru.sbt.mipt.oop.eventprocessor;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.action.OpenDoor;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.DoorSensorEvent;

import static ru.sbt.mipt.oop.sensor.DoorEventType.*;


public class DoorEventProcessor implements EventHandler {
    private void processDoorEvent(boolean isOpen, SmartHome smartHome, DoorSensorEvent event) {
        smartHome.execute(new OpenDoor(isOpen, event.getObjectId()));
    }

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event instanceof DoorSensorEvent) {
            if (((DoorSensorEvent) event).getType() == DOOR_OPEN) {
                processDoorEvent(true, smartHome, (DoorSensorEvent) event);
            } else if (((DoorSensorEvent) event).getType() == DOOR_CLOSED) {
                processDoorEvent(false, smartHome, (DoorSensorEvent) event);
            }
        }
    }
}