package ru.sbt.mipt.oop.eventprocessor;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.action.OpenDoor;
import ru.sbt.mipt.oop.home.SmartHome;

import static ru.sbt.mipt.oop.sensor.SensorEventType.*;


public class DoorEventProcessor implements EventHandler {
    private void processDoorEvent(boolean isOpen, SmartHome smartHome, SensorEvent event) {
        smartHome.execute(new OpenDoor(isOpen, event.getObjectId()));
    }

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_OPEN) {
            processDoorEvent(true, smartHome, event);
        } else if (event.getType() == DOOR_CLOSED) {
            processDoorEvent(false, smartHome, event);
        }
    }
}