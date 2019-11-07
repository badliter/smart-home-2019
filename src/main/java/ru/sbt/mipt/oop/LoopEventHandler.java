package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.home.SmartHome;

public class LoopEventHandler {
    SmartHome smartHome;
    SensorEventReader sensorEventReader;
    EventProcess eventProcess;

    public LoopEventHandler(SmartHome smartHome, SensorEventReader sensorEventReader, EventProcess eventProcess){
        this.smartHome = smartHome;
        this.sensorEventReader = sensorEventReader;
        this.eventProcess = eventProcess;
    }

    public void performLoopEventHandle() {
        SensorEvent event = sensorEventReader.getNextSensorEvent();
        while (event != null) {
            eventProcess.processEvent(smartHome, event);
            event = sensorEventReader.getNextSensorEvent();
        }
    }
}
