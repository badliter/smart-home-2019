package ru.sbt.mipt.oop.home.alarm;

import ru.sbt.mipt.oop.AlarmState;
import ru.sbt.mipt.oop.MessageSender;

public class DeactivatedAlarmState implements AlarmState {
    private transient HomeAlarm homeAlarm;
    private String code;

    public DeactivatedAlarmState(HomeAlarm homeAlarm, String code) {
        this.homeAlarm = homeAlarm;
        this.code = code;
    }

    @Override
    public void activate(String code) {
        homeAlarm.changeState(new ActivatedAlarmState(homeAlarm, this.code));
    }

    @Override
    public void deactivate(String code) {
    }

    @Override
    public void danger() {
        homeAlarm.changeState(new DangerAlarmState(homeAlarm, code));
        MessageSender.sendMsgAboutDangerAlarmState();
    }
}
