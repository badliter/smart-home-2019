package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.WorkWithActionableCollection;
import ru.sbt.mipt.oop.home.Light;

import java.util.Collection;

public class TurnOnLight implements Action {
    private boolean turnOn;
    private String id;

    public TurnOnLight(boolean turnOn, String id) {
        this.turnOn = turnOn;
        this.id = id;
    }

    @Override
    public void act(Collection<Actionable> collection) {
        Actionable actionable = new WorkWithActionableCollection(collection).getLast();
        Room room = new WorkWithActionableCollection(collection).getRoom();
        if (actionable.getClass().equals(Light.class) && ((Light) actionable).getId().equals(id)) {
            ((Light) actionable).setOn(turnOn);
            writeToConsole(turnOn, ((Light) actionable), room);
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
