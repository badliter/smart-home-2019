package ru.sbt.mipt.oop.eventprocessor;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.action.HallChecker;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.ArrayList;

import static ru.sbt.mipt.oop.sensor.SensorEventType.*;

public class HallEventProcessor implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_CLOSED) {
            smartHome.execute(new HallChecker(event.getObjectId()), new ArrayList<>());
        }
    }
}
