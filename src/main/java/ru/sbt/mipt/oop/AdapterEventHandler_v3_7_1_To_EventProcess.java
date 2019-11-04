package ru.sbt.mipt.oop;

import library_v3_7_1.events.CCSensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;

public class AdapterEventHandler_v3_7_1_To_EventProcess implements library_v3_7_1.events.EventHandler {
    private final ru.sbt.mipt.oop.EventProcess eventProcess;
    private final SmartHome smartHome;

    public AdapterEventHandler_v3_7_1_To_EventProcess(EventProcess eventProcess, SmartHome smartHome) {
        this.eventProcess = eventProcess;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventProcess.processEvent(smartHome, new AdapterCCSensorEvent_v3_7_1_To_SensorEvent(event));
    }
}
