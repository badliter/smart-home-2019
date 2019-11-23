package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.EventProcess;
import ru.sbt.mipt.oop.adapters.converter.ccsensorevent.ConverterCCSensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;

public class AdapterEventHandlerToEventProcess implements EventHandler {
    private final EventProcess eventProcess;
    private final SmartHome smartHome;
    private final ConverterCCSensorEvent converterCCSensorEvent;

    public AdapterEventHandlerToEventProcess(EventProcess eventProcess, SmartHome smartHome, ConverterCCSensorEvent converterCCSensorEvent) {
        this.eventProcess = eventProcess;
        this.smartHome = smartHome;
        this.converterCCSensorEvent = converterCCSensorEvent;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventProcess.processEvent(smartHome, converterCCSensorEvent.convert(event));
    }
}
