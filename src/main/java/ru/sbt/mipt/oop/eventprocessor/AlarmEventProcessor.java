package ru.sbt.mipt.oop.eventprocessor;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.alarm.HomeAlarm;
import ru.sbt.mipt.oop.sensor.AlarmSensorEvent;

import static ru.sbt.mipt.oop.sensor.SensorEventType.*;

public class AlarmEventProcessor implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        smartHome.execute(homeAlarm -> {
            if (homeAlarm instanceof HomeAlarm){
                if (event.getType() == ALARM_ACTIVATE){
                    ((HomeAlarm) homeAlarm).activate(((AlarmSensorEvent) event).getCode());
                } else if (event.getType() == ALARM_DEACTIVATE){
                    ((HomeAlarm) homeAlarm).deactivate(((AlarmSensorEvent) event).getCode());
                }
            }
        });
    }
}
