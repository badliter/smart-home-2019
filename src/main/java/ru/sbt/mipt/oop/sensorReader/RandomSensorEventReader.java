package ru.sbt.mipt.oop.sensorReader;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventReader;
import ru.sbt.mipt.oop.sensor.*;

import static ru.sbt.mipt.oop.sensor.SensorEventType.*;

public class RandomSensorEventReader implements SensorEventReader {
    @Override
    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        double random = Math.random();
        int randomEventNum = (int) (6 * Math.random());
        SensorEventType sensorEventType = SensorEventType.values()[randomEventNum];
        if (random > 0.05d && (sensorEventType == LIGHT_ON || sensorEventType == LIGHT_OFF)){
            String objectId = "" + ((int) (4 * Math.random()) + 1);
            return new LightSensorEvent(sensorEventType,objectId);
        } else if (random > 0.05d && (sensorEventType == DOOR_OPEN || sensorEventType == DOOR_CLOSED)){
            String objectId = "" + ((int) (10 * Math.random()) + 1);
            return new DoorSensorEvent(sensorEventType,objectId);
        } else if (random > 0.05d && (sensorEventType == ALARM_DEACTIVATE || sensorEventType == ALARM_ACTIVATE)){
            String code = "" + ((int) (2 * Math.random()));
            return new AlarmSensorEvent(sensorEventType,code);
        }
        return null;
    }
}
