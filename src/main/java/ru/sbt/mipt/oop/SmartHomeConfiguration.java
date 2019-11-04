package ru.sbt.mipt.oop;

import library_v3_7_1.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.alarmreader.RandomAlarmReader;
import ru.sbt.mipt.oop.eventprocessor.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.homeReader.JsonHomeReader;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class SmartHomeConfiguration {
    private AlarmReader getAlarmReader(){
        return new RandomAlarmReader();
    }

    private HomeReader getHomeReader(){
        return new JsonHomeReader();
    }

    private SmartHome getSmartHome(){
        SmartHome smartHome = new SmartHome(getHomeReader().readHome(), getAlarmReader().readAlarm());
        return smartHome;
    }

    private CollectionEventProcessor getCollectionEventProcess(){
        Collection<EventHandler> collection = new ArrayList<>();
        collection.add(new LightEventProcessor());
        collection.add(new DoorEventProcessor());
        collection.add(new HallEventProcessor());
        collection.add(new AlarmEventProcessor());
        return new CollectionEventProcessor(collection);
    }

    @Bean
    public SensorEventsManager getSensorEventsManager(){
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        getCollectionEventProcess();
        EventProcess eventProcess = new DecoratorDangerAlarmState(new EventProcessor());
        sensorEventsManager.registerEventHandler(new AdapterEventHandler_v3_7_1_To_EventProcess(eventProcess, getSmartHome()));
        return sensorEventsManager;
    }
}
