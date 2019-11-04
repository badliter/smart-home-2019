package ru.sbt.mipt.oop.sensorReader;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventReader;
import ru.sbt.mipt.oop.sensor.*;

public class RandomSensorEventReader implements SensorEventReader {
    @Override
    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        double random = Math.random();
        if (random < 0.3d){
            SensorEventType sensorEventType = SensorEventType.values()[(int) (2 * Math.random())];
            String objectId = "" + ((int) (4 * Math.random()) + 1);
            return new DoorSensorEvent(sensorEventType,objectId);
        } else if (random < 0.6d && random >= 0.3d){
            SensorEventType sensorEventType = SensorEventType.values()[(int) (2 * Math.random())];
            String objectId = "" + ((int) (10 * Math.random()) + 1);
            return new LightSensorEvent(sensorEventType,objectId);
        } else if (random < 0.95d && random >= 0.6d){
            SensorEventType sensorEventType = SensorEventType.values()[(int) (2 * Math.random())];
            String code = "" + ((int) (2 * Math.random()));
            return new AlarmSensorEvent(sensorEventType,code);
        }
        return null;
    }
}
