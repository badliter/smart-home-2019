package ru.sbt.mipt.oop.adapters.converter.ccsensorevent;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

public interface ConverterCCSensorEvent {
    SensorEvent convert(CCSensorEvent ccSensorEvent);
}
