package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.home.alarm.HomeAlarm;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Room> rooms;
    HomeAlarm homeAlarm;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public SmartHome(Collection<Room> rooms, HomeAlarm homeAlarm) {
        this.rooms = rooms;
        this.homeAlarm = homeAlarm;
    }

    public SmartHome(SmartHome smartHome, HomeAlarm homeAlarm) {
        rooms = new ArrayList<>();
        smartHome.execute(room -> {
            if (room instanceof Room) {
                addRoom((Room) room);
            }
        });
        this.homeAlarm = homeAlarm;
    }

    public HomeAlarm getHomeAlarm() {
        return homeAlarm;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void execute(Action action) {
        action.act(this);
        homeAlarm.execute(action);
        for (Room room : rooms) {
            room.execute(action);
        }
    }
}
