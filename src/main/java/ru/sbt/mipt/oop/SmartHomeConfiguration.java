package ru.sbt.mipt.oop;

import library_v3_7_1.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.eventprocessor.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.homereader.JsonHomeReader;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class SmartHomeConfiguration {
    @Bean
    public SensorEventsManager getSensorEventsManager() {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new AdapterEventHandlerToEventProcess(getEventProcess(), getSmartHome()));
        return sensorEventsManager;
    }

    private HomeReader getHomeReader() {
        return new JsonHomeReader();
    }

    private SmartHome getSmartHome() {
        return getHomeReader().readHome();
    }

    private Collection<EventHandler> getCollectionEventProcess() {
        Collection<EventHandler> collection = new ArrayList<>();
        collection.add(new LightEventProcessor());
        collection.add(new DoorEventProcessor());
        collection.add(new HallEventProcessor());
        collection.add(new AlarmEventProcessor());
        return collection;
    }

    private EventProcess getEventProcess() {
        return new DecoratorDangerAlarmState(new EventProcessor(getCollectionEventProcess()));
    }
}
