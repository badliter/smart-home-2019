package ru.sbt.mipt.oop.eventprocessor;

import ru.sbt.mipt.oop.EventProcess;
import ru.sbt.mipt.oop.MessageSender;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.home.alarm.ActivatedAlarmState;
import ru.sbt.mipt.oop.home.alarm.DangerAlarmState;
import ru.sbt.mipt.oop.home.SmartHome;

import static ru.sbt.mipt.oop.sensor.SensorEventType.*;

public class DecoratorDangerAlarmState implements EventProcess {
    private EventProcess delegate;

    public DecoratorDangerAlarmState(EventProcess delegate) {
        this.delegate = delegate;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        System.out.println("Got event: " + event);
        if (smartHome.getHomeAlarm().getAlarmState() instanceof ActivatedAlarmState) {
            doIfAlarmStateIsActivated(smartHome);
        } else if (smartHome.getHomeAlarm().getAlarmState() instanceof DangerAlarmState) {
            doIfAlarmStateIsDanger(smartHome, event);
        } else {
            delegate.processEvent(smartHome, event);
        }
    }

    private void doIfAlarmStateIsActivated(SmartHome smartHome) {
        smartHome.getHomeAlarm().danger();
    }

    private void doIfAlarmStateIsDanger(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == ALARM_DEACTIVATE) {
            delegate.processEvent(smartHome, event);
        } else {
            sendMsg();
        }
    }

    private void sendMsg(){
        MessageSender.sendMsgAboutDangerAlarmState();
    }
}
