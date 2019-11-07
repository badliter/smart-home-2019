package ru.sbt.mipt.oop.home.alarm;

import ru.sbt.mipt.oop.AlarmState;
import ru.sbt.mipt.oop.MessageSender;

public class DangerAlarmState implements AlarmState {
    private HomeAlarm homeAlarm;

    public DangerAlarmState(HomeAlarm homeAlarm) {
        this.homeAlarm = homeAlarm;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        new ActivatedAlarmState(homeAlarm).deactivate(code);
    }

    @Override
    public void danger() {
        MessageSender.sendMsgAboutDangerAlarmState();
    }
}
