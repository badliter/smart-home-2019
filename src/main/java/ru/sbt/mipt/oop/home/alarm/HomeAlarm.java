package ru.sbt.mipt.oop.home.alarm;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.AlarmState;

public class HomeAlarm implements AlarmState, Actionable {
    private AlarmState alarmState;
    private final String code;

    public HomeAlarm(String code) {
        alarmState = new DeactivatedAlarmState(this, code);
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

    @Override
    public void danger() {
        alarmState.danger();
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
