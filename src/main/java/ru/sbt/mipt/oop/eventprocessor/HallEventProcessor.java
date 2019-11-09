package ru.sbt.mipt.oop.eventprocessor;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.action.AllLightTurnOff;
import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.DoorSensorEvent;

import static ru.sbt.mipt.oop.sensor.DoorEventType.*;

public class HallEventProcessor implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event instanceof DoorSensorEvent && ((DoorSensorEvent) event).getType() == DOOR_CLOSED) {
            smartHome.execute(room -> {
                if (room instanceof Room && ((Room) room).getName().equals("hall")) {
                    room.execute(door -> {
                        if (door instanceof Door && ((Door) door).getId().equals(((DoorSensorEvent) event).getObjectId())) {
                            smartHome.execute(new AllLightTurnOff());
                        }
                    });
                }
            });
        }
    }
}
