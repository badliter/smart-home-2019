package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.home.alarm.HomeAlarm;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;
    private HomeAlarm homeAlarm;

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
