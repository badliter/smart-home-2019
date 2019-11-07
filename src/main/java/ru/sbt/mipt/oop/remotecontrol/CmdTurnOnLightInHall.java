package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.action.AllLightTurnOff;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;

public class CmdTurnOnLightInHall implements Command {
    SmartHome smartHome;

    public CmdTurnOnLightInHall(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void doCommand() {
//        smartHome.execute(room -> {
//            if (room instanceof Room && ((Room)room).getName().equals("hall")){
//                room.execute(light -> {
//                    if (light instanceof Light){
//                        ((Light)light).setOn(false);
//                        writeToConsole(false, (Light) light, (Room) room);
//                    }
//                });
//            }
//        });
    }

    private void writeToConsole(boolean turnOn, Light light, Room room) {
        if (turnOn) {
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
        } else {
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
        }
    }
}



