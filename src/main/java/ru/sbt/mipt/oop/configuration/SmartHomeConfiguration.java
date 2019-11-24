package ru.sbt.mipt.oop.configuration;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.EventProcess;
import ru.sbt.mipt.oop.adapters.AdapterEventHandlerToEventProcess;
import ru.sbt.mipt.oop.adapters.converter.ccsensorevent.*;
import ru.sbt.mipt.oop.eventprocessor.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.homereader.JsonHomeReader;
import ru.sbt.mipt.oop.remotecontrol.RemoteControl;

import java.util.Collection;

@Configuration
@Import(RemoteControlConfiguration.class)
public class SmartHomeConfiguration {
    @Bean
    public SensorEventsManager sensorEventsManager(EventProcess eventProcess, SmartHome smartHome, ConverterCCSensorEvent converterCCSensorEvent) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new AdapterEventHandlerToEventProcess(eventProcess, smartHome, converterCCSensorEvent));
        return sensorEventsManager;
    }

    @Bean
    public SmartHome smartHome() {
        return new JsonHomeReader().readHome();
    }

    @Bean
    public EventProcess eventProcess(Collection<EventHandler> collectionEventProcess) {
        return new DecoratorDangerAlarmState(new EventProcessor(collectionEventProcess));
    }

    @Bean
    public EventHandler lightEventProcessor(){
        return new LightEventProcessor();
    }

    @Bean
    public EventHandler doorEventProcessor(){
        return new DoorEventProcessor();
    }

    @Bean
    public EventHandler hallEventProcessor(){
        return new HallEventProcessor();
    }

    @Bean
    public EventHandler alarmEventProcessor(){
        return new AlarmEventProcessor();
    }

    @Bean
    public ConverterCCSensorEvent converterCCSensorEvent(ConverterLightIsOff converterLightIsOff){
        return new ConverterLightIsOn(converterLightIsOff);
    }

    @Bean
    public ConverterLightIsOff converterLigthIsOff(ConverterDoorIsOpen converterDoorIsOpen){
        return new ConverterLightIsOff(converterDoorIsOpen);
    }

    @Bean
    public ConverterDoorIsOpen converterDoorIsOpen(ConverterDoorIsClosed converterDoorIsClosed){
        return new ConverterDoorIsOpen(converterDoorIsClosed);
    }

    @Bean
    public ConverterDoorIsClosed converterDoorIsClosed(){
        return new ConverterDoorIsClosed(null);
    }
}
