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
        if (smartHome.getHomeAlarm().getAlarmState() instanceof ActivatedAlarmState) {
            smartHome.getHomeAlarm().danger();
        } else if (smartHome.getHomeAlarm().getAlarmState() instanceof DangerAlarmState) {
            if (event.getType() == ALARM_DEACTIVATE) {
                delegate.processEvent(smartHome, event);
            } else {
                sendMsg();
            }
        } else {
            delegate.processEvent(smartHome, event);
        }
    }

    private void sendMsg(){
        MessageSender.sendMsgAboutDangerAlarmState();
    }
}
