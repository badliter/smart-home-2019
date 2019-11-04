package ru.sbt.mipt.oop.alarmreader;

import ru.sbt.mipt.oop.AlarmReader;
import ru.sbt.mipt.oop.alarm.HomeAlarm;

public class RandomAlarmReader implements AlarmReader {
    @Override
    public HomeAlarm readAlarm() {
        return new HomeAlarm("0");
    }
}
