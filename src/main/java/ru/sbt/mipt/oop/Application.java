package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.HomeAlarm;
import ru.sbt.mipt.oop.alarmreader.RandomAlarmReader;
import ru.sbt.mipt.oop.eventprocessor.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.homeReader.JsonHomeReader;
import ru.sbt.mipt.oop.sensorReader.RandomSensorEventReader;

import java.util.ArrayList;
import java.util.Collection;

public class Application {

    private HomeReader homeReader;
    private AlarmReader alarmReader;
    private SensorEventReader sensorEventReader;
    private CollectionEventProcessor collectionEventProcessor;

    public Application(HomeReader homeReader, AlarmReader alarmReader, SensorEventReader sensorEventReader, CollectionEventProcessor collectionEventProcessor) {
        this.homeReader = homeReader;
        this.alarmReader = alarmReader;
        this.sensorEventReader = sensorEventReader;
        this.collectionEventProcessor = collectionEventProcessor;
    }

    public static void main(String... args) {
        Collection<EventHandler> collection = new ArrayList<>();
        collection.add(new LightEventProcessor());
        collection.add(new DoorEventProcessor());
        collection.add(new HallEventProcessor());
        collection.add(new AlarmEventProcessor());

        Application app = new Application(new JsonHomeReader(), new RandomAlarmReader(), new RandomSensorEventReader(), new CollectionEventProcessor(collection));
        app.execute();
    }

    public void execute() {
        // считываем состояние дома из файла
        SmartHome smartHome = new SmartHome(homeReader.readHome(), alarmReader.readAlarm());
        // начинаем цикл обработки событий
        new LoopEventHandler().performLoopEventHandle(smartHome, sensorEventReader);
    }
}
