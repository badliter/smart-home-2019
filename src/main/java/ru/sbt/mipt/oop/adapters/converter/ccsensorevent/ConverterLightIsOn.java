package ru.sbt.mipt.oop.adapters.converter.ccsensorevent;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.sensor.LightSensorEvent;

import static ru.sbt.mipt.oop.sensor.LightEventType.LIGHT_ON;

public class ConverterLightIsOn implements ConverterCCSensorEvent {
    private ConverterCCSensorEvent nextConverter;

    public ConverterLightIsOn(ConverterCCSensorEvent nextConverter){
        this.nextConverter = nextConverter;
    }

    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        if (ccSensorEvent.getEventType().equals("LightIsOn")){
            return new LightSensorEvent(LIGHT_ON, ccSensorEvent.getObjectId());
        } else if (nextConverter == null){
            return null;
        }
        return nextConverter.convert(ccSensorEvent);
    }
}
