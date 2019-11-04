package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.AlarmState;

public class HomeAlarm implements AlarmState {
    private AlarmState alarmState;
    private final String code;

    public HomeAlarm(String code) {
        this.alarmState = new DeactivatedAlarmState(this);
        this.code = code;
    }

    public void changeState(AlarmState alarmState) {
        this.alarmState = alarmState;
    }

    public AlarmState getAlarmState() {
        return alarmState;
    }

    @Override
    public void activate(String code) {
        alarmState.activate(code);
    }

    @Override
    public void deactivate(String code) {
        alarmState.deactivate(code);
    }

    public String getCode() {
        return code;
    }
}
