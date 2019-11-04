package ru.sbt.mipt.oop.eventprocessor;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.EventProcess;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;

public class EventProcessor implements EventProcess {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        for (EventHandler eventProcessor : CollectionEventProcessor.collection) {
            eventProcessor.handle(smartHome, event);
        }
    }
}
