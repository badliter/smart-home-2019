package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.home.SmartHome;

public class LoopEventHandler {
    public void performLoopEventHandle(SmartHome smartHome, SensorEventReader sensorEventReader, EventProcess eventProcess) {
        SensorEvent event = sensorEventReader.getNextSensorEvent();
        while (event != null) {
            eventProcess.processEvent(smartHome, event);
            event = sensorEventReader.getNextSensorEvent();
        }
    }
}
