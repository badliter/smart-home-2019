package ru.sbt.mipt.oop.eventprocessor;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.action.AllLightTurnOff;
import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.DoorSensorEvent;

import static ru.sbt.mipt.oop.sensor.SensorEventType.*;

public class HallEventProcessor implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_CLOSED && checkRoomIsHall(smartHome, event.getObjectId())) {
            smartHome.execute(new AllLightTurnOff());
        }
    }

    private boolean checkRoomIsHall(SmartHome smartHome, String id){
        for (Room room : smartHome.getRooms()){
            for (Door door : room.getDoors()){
                if (door.getId().equals(id) && room.getName().equals("hall")) return true;
            }
        }
        return false;
    }
}
