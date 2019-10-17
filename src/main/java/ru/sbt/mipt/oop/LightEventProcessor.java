package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightEventProcessor implements EventHandler {
    private void processLightEvent(boolean turnOn, SmartHome smartHome, SensorEvent event) {
        smartHome.execute(new LightTurnOn(turnOn, event.getObjectId()));
    }

    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == LIGHT_ON) {
            processLightEvent(true, smartHome, event);
        } else if (event.getType() == LIGHT_OFF) {
            processLightEvent(false, smartHome, event);
        }
    }
}