package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightEvent {
    public static void setLightEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        lightTurnOn(room,light);
                    } else {
                        lightTurnOff(room,light);
                    }
                }
            }
        }
    }

    public static void lightTurnOn(Room room, Light light){
        light.setOn(true);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
    }

    public static void lightTurnOff(Room room, Light light){
        light.setOn(false);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
    }

    public static void turnOffAllLightsInHome(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                // здесь хочется поставить lightTurnOff, но тут почему-то нет вывода в консоль
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                CommandSender.sendCommand(command);
            }
        }
    }
}