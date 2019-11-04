package ru.sbt.mipt.oop.eventprocessor;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.action.TurnOnLight;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.LightSensorEvent;

import static ru.sbt.mipt.oop.sensor.LightEventType.*;

public class LightEventProcessor implements EventHandler {
    private void processLightEvent(boolean turnOn, SmartHome smartHome, LightSensorEvent event) {
        smartHome.execute(new TurnOnLight(turnOn, event.getObjectId()));
    }

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event instanceof LightSensorEvent) {
            if (((LightSensorEvent) event).getType() == LIGHT_ON) {
                processLightEvent(true, smartHome, (LightSensorEvent) event);
            } else if (((LightSensorEvent) event).getType() == LIGHT_OFF) {
                processLightEvent(false, smartHome, (LightSensorEvent) event);
            }
        }
    }
}