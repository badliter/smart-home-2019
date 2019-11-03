package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventprocessor.EventProcess;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;

public class LoopEventHandler {
    public void performLoopEventHandle(SmartHome smartHome, SensorEventReader sensorEventReader) {
        SensorEvent event = sensorEventReader.getNextSensorEvent();
        EventProcess eventProcess = new EventProcess();
        while (event != null) {
            eventProcess.processEvent(smartHome, event);
            event = sensorEventReader.getNextSensorEvent();
        }
    }
}
