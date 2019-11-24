package ru.sbt.mipt.oop.home.alarm;

import ru.sbt.mipt.oop.AlarmState;
import ru.sbt.mipt.oop.MessageSender;

public class ActivatedAlarmState implements AlarmState {
    private transient HomeAlarm homeAlarm;
    private String code;

    public ActivatedAlarmState(HomeAlarm homeAlarm, String code) {
        this.homeAlarm = homeAlarm;
        this.code = code;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(this.code)) {
            homeAlarm.changeState(new DeactivatedAlarmState(homeAlarm, this.code));
        } else {
            danger();
        }
    }

    @Override
    public void danger() {
        homeAlarm.changeState(new DangerAlarmState(homeAlarm, code));
        MessageSender.sendMsgAboutDangerAlarmState();
    }
}
