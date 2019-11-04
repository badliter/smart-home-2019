package ru.sbt.mipt.oop.eventprocessor;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.sensor.AlarmSensorEvent;

import static ru.sbt.mipt.oop.sensor.SensorEventType.*;

public class AlarmEventProcessor implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == ALARM_ACTIVATE) {
            smartHome.getHomeAlarm().activate(((AlarmSensorEvent) event).getCode());
        } else if (event.getType() == ALARM_DEACTIVATE) {
            smartHome.getHomeAlarm().deactivate(((AlarmSensorEvent) event).getCode());
        }
    }
}
