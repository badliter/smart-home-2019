package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;

public class TurnOnLight implements Action {
    private boolean turnOn;
    private String id;

    public TurnOnLight(boolean turnOn, String id) {
        this.turnOn = turnOn;
        this.id = id;
    }

    @Override
    public void act(Actionable room) {
        if (room instanceof Room){
            room.execute( light -> {
                if (light instanceof Light && ((Light) light).getId().equals(id)){
                    writeToConsole(turnOn, (Light)light, (Room) room);
                }
            });
        }
    }

    private void writeToConsole(boolean turnOn, Light light, Room room) {
        if (turnOn) {
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
        } else {
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
        }
    }
}