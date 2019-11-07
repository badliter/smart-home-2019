package ru.sbt.mipt.oop.home.alarm;

import ru.sbt.mipt.oop.AlarmState;

public class DeactivatedAlarmState implements AlarmState {
    private HomeAlarm homeAlarm;

    public DeactivatedAlarmState(HomeAlarm homeAlarm) {
        this.homeAlarm = homeAlarm;
    }

    @Override
    public void activate(String code) {
        if (homeAlarm.getCode().equals(code)) {
            homeAlarm.changeState(new ActivatedAlarmState(homeAlarm));
        }
    }

    @Override
    public void deactivate(String code) {
    }

    @Override
    public void danger() {
        homeAlarm.changeState(new DangerAlarmState(homeAlarm));
        homeAlarm.danger();
    }
}
