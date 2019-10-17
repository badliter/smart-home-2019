package ru.sbt.mipt.oop;
import java.util.List;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventProcess {
    public void processEvent(SmartHome smartHome, SensorEvent event){
        System.out.println("Got event: " + event);
        new LightEventProcessor().handle(smartHome, event);
        new DoorEventProcessor().handle(smartHome, event);
        new HallEventProcessor().handle(smartHome, event);
    }
}
