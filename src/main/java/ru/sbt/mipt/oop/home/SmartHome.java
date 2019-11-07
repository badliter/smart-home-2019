package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.home.alarm.HomeAlarm;
import ru.sbt.mipt.oop.home.remotecontrol.HomeRemoteControl;
import ru.sbt.mipt.oop.home.remotecontrol.RemoteControl;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Room> rooms;
    Collection<RemoteControl> remoteControls;
    HomeAlarm homeAlarm;

    public SmartHome() {
        rooms = new ArrayList<>();
        remoteControls = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        remoteControls = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms, HomeAlarm homeAlarm) {
        this.rooms = rooms;
        this.homeAlarm = homeAlarm;
        remoteControls = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms, HomeAlarm homeAlarm, Collection<RemoteControl> remoteControls) {
        this.rooms = rooms;
        this.homeAlarm = homeAlarm;
        this.remoteControls = remoteControls;
    }

    public SmartHome(SmartHome smartHome, HomeAlarm homeAlarm) {
        rooms = new ArrayList<>();
        smartHome.execute(room -> {
            if (room instanceof Room) {
                addRoom((Room) room);
            }
        });
        this.homeAlarm = homeAlarm;
        this.remoteControls = new ArrayList<>();
    }

    public HomeAlarm getHomeAlarm() {
        return homeAlarm;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addRemoteControl(RemoteControl rc) {
        remoteControls.add(rc);
    }

    @Override
    public void execute(Action action) {
        action.act(this);
        homeAlarm.execute(action);
        for (Room room : rooms) {
            room.execute(action);
        }
        for (RemoteControl rc : remoteControls) {
            rc.execute(action);
        }
    }
}
