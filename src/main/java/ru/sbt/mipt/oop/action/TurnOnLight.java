package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.home.Light;

public class TurnOnLight implements Action {
    private boolean turnOn;
    private String id;

    public TurnOnLight(boolean turnOn, String id) {
        this.turnOn = turnOn;
        this.id = id;
    }

    @Override
    public void act(Actionable actionable) {
        if (actionable.getClass().equals(Light.class) && ((Light) actionable).getId().equals(id)) {
            ((Light) actionable).setOn(turnOn);
            writeToConsole(turnOn, ((Light) actionable));
        }
    }

    private void writeToConsole(boolean turnOn, Light light) {
        if (turnOn) {
            System.out.println("Light " + light.getId() + " was turned on.");
        } else {
            System.out.println("Light " + light.getId() + " was turned off.");
        }
    }
}