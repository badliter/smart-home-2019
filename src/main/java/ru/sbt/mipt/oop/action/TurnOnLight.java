package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.LogWriter;
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
                    LogWriter.writeToConsoleAboutLightEvent(turnOn, (Light)light, (Room) room);
                }
            });
        }
    }
}