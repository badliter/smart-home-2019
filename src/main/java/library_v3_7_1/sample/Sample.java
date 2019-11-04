package library_v3_7_1.sample;

import library_v3_7_1.events.SensorEventsManager;

public class Sample {

    public static void main(String[] args) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(event -> {
            System.out.println("Event type [" + event.getEventType() + "] from object with id=" + event.getObjectId() + "]");
        });
        sensorEventsManager.start();
    }
}
