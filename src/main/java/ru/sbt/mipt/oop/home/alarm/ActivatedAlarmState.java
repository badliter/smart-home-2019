package ru.sbt.mipt.oop.home.alarm;

import ru.sbt.mipt.oop.AlarmState;

public class ActivatedAlarmState implements AlarmState {
    private HomeAlarm homeAlarm;

    public ActivatedAlarmState(HomeAlarm homeAlarm) {
        this.homeAlarm = homeAlarm;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (homeAlarm.getCode().equals(code)) {
            homeAlarm.changeState(new DeactivatedAlarmState(homeAlarm));
        } else {
            danger();
        }
    }

    @Override
    public void danger() {
        homeAlarm.changeState(new DangerAlarmState(homeAlarm));
        homeAlarm.getAlarmState().danger();
    }
}
