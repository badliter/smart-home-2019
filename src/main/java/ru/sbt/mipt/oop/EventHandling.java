package ru.sbt.mipt.oop;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventHandling {
    public static void doEventHandling(SmartHome smartHome, SensorEvent event){
        System.out.println("Got event: " + event);
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            // событие от источника света
            LightEvent.setLightEvent(smartHome, event);
        }
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            DoorEvent.setDoorEvent(smartHome, event);
        }
    }
}
