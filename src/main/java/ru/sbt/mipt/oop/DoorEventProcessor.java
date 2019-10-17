package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorEventProcessor implements EventHandler {
    private void processDoorEvent(boolean isOpen, SmartHome smartHome, SensorEvent event) {
        smartHome.execute(new DoorIsOpen(isOpen, event.getObjectId()));
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