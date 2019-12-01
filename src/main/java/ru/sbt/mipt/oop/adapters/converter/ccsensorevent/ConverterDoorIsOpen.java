package ru.sbt.mipt.oop.adapters.converter.ccsensorevent;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.sensor.DoorSensorEvent;

import static ru.sbt.mipt.oop.sensor.DoorEventType.DOOR_OPEN;

public class ConverterDoorIsOpen implements ConverterCCSensorEvent{
    private ConverterCCSensorEvent nextConverter;

    public ConverterDoorIsOpen(ConverterCCSensorEvent nextConverter){
        this.nextConverter = nextConverter;
    }

    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        if (ccSensorEvent.getEventType().equals("DoorIsOpen")){
            return new DoorSensorEvent(DOOR_OPEN, ccSensorEvent.getObjectId());
        } else if (nextConverter == null){
            return null;
        }
        return nextConverter.convert(ccSensorEvent);
    }
}
