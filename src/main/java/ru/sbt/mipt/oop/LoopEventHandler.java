package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.DangerAlarmState;
import ru.sbt.mipt.oop.eventprocessor.DecoratorDangerAlarmState;
import ru.sbt.mipt.oop.eventprocessor.EventProcessor;
import ru.sbt.mipt.oop.home.SmartHome;

public class LoopEventHandler {
    public void performLoopEventHandle(SmartHome smartHome, SensorEventReader sensorEventReader) {
        SensorEvent event = sensorEventReader.getNextSensorEvent();
        EventProcess eventProcess = new DecoratorDangerAlarmState(new EventProcessor());
        while (event != null) {
            System.out.println("Got event: " + event);
            eventProcess.processEvent(smartHome, event);
            event = sensorEventReader.getNextSensorEvent();
        }
    }
}
