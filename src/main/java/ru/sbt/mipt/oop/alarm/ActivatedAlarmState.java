package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.AlarmState;
import ru.sbt.mipt.oop.MessageSender;

public class ActivatedAlarmState implements AlarmState {
    private HomeAlarm homeAlarm;
    public static final String STATE = "ACTIVATED";

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
            homeAlarm.changeState(new DangerAlarmState(homeAlarm));
            MessageSender.sendMessage("Dangerous. Your home is unsafe!!!");
        }
    }
}
