package ru.sbt.mipt.oop.eventprocessor;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.EventProcess;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.Collection;

public class EventProcessor implements EventProcess {
    private Collection<EventHandler> collectionEventProcessor;

    public EventProcessor(Collection<EventHandler> collectionEventProcessor){
        this.collectionEventProcessor = collectionEventProcessor;
    }

    public void processEvent(SmartHome smartHome, SensorEvent event) {
        for (EventHandler eventProcessor : collectionEventProcessor) {
            eventProcessor.handle(smartHome, event);
        }
    }
}
