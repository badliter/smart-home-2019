package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventprocessor.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.homeReader.JsonHomeReader;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensorReader.RandomSensorEventReader;

import java.util.ArrayList;
import java.util.Collection;

public class Application {

    private HomeReader homeReader;
    private SensorEventReader sensorEventReader;
    private CollectionEventProcessor collectionEventProcessor;

    public Application(HomeReader homeReader, SensorEventReader sensorEventReader, CollectionEventProcessor collectionEventProcessor) {
        this.homeReader = homeReader;
        this.sensorEventReader = sensorEventReader;
        this.collectionEventProcessor = collectionEventProcessor;
    }

    public static void main(String... args) {
        Collection<EventHandler> collection = new ArrayList<>();
        collection.add(new LightEventProcessor());
        collection.add(new DoorEventProcessor());
        collection.add(new HallEventProcessor());

        Application app = new Application(new JsonHomeReader(), new RandomSensorEventReader(), new CollectionEventProcessor(collection));
        app.execute();
    }

    public void execute() {
        // считываем состояние дома из файла
        SmartHome smartHome = homeReader.readHome();
        // начинаем цикл обработки событий
        new LoopEventHandler().performLoopEventHandle(smartHome, sensorEventReader);
    }
}
