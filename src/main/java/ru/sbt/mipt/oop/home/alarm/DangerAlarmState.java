package ru.sbt.mipt.oop.home.alarm;

import ru.sbt.mipt.oop.AlarmState;
import ru.sbt.mipt.oop.MessageSender;

public class DangerAlarmState implements AlarmState {
    private transient HomeAlarm homeAlarm;
    private String code;

    public DangerAlarmState(HomeAlarm homeAlarm, String code) {
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
        MessageSender.sendMsgAboutDangerAlarmState();
    }
}
