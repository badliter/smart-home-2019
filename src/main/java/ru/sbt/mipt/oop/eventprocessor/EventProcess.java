package ru.sbt.mipt.oop.eventprocessor;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.eventprocessor.CollectionEventProcessor;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;

public class EventProcess {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        System.out.println("Got event: " + event);
        for (EventHandler eventProcessor : CollectionEventProcessor.collection) {
            eventProcessor.handle(smartHome, event);
        }
    }
}
