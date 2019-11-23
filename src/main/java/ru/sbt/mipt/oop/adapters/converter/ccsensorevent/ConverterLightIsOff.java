package ru.sbt.mipt.oop.adapters.converter.ccsensorevent;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.sensor.LightSensorEvent;

import static ru.sbt.mipt.oop.sensor.LightEventType.LIGHT_OFF;

public class ConverterLightIsOff implements ConverterCCSensorEvent{
    private ConverterCCSensorEvent nextConverter;

    public ConverterLightIsOff(ConverterCCSensorEvent nextConverter){
        this.nextConverter = nextConverter;
    }

    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        if (ccSensorEvent.getEventType().equals("LightIsOff")){
            return new LightSensorEvent(LIGHT_OFF, ccSensorEvent.getObjectId());
        } else if (nextConverter == null){
            return null;
        }
        return nextConverter.convert(ccSensorEvent);
    }
}
