package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class HallEventProcessor implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_CLOSED) {
            smartHome.execute(new CheckIsHall(event.getObjectId()));
        }
    }
}
