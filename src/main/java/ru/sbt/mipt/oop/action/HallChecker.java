package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.WorkWithActionableCollection;

import java.util.ArrayList;
import java.util.Collection;

public class HallChecker implements Action {
    private String id;

    public HallChecker(String id) {
        this.id = id;
    }

    @Override
    public void act(Collection<Actionable> collection) {
        Actionable actionable = new WorkWithActionableCollection(collection).getLast();
        SmartHome smartHome = new WorkWithActionableCollection(collection).getSmartHome();
        Room room = new WorkWithActionableCollection(collection).getRoom();
        if (actionable.getClass().equals(Door.class)) {
            if (((Door) actionable).getId().equals(id) && room.getName().equals("hall")) {
                smartHome.execute(new AllLightTurnOff(), new ArrayList<>());
            }
        }
    }
}
