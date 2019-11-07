package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventprocessor.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.homeReader.JsonHomeReader;
import ru.sbt.mipt.oop.sensorReader.RandomSensorEventReader;

import java.util.ArrayList;
import java.util.Collection;

public class Application {

    private HomeReader homeReader;
    private SensorEventReader sensorEventReader;
    private EventProcess eventProcess;

    public Application(HomeReader homeReader, SensorEventReader sensorEventReader, EventProcess eventProcess) {
        this.homeReader = homeReader;
        this.sensorEventReader = sensorEventReader;
        this.eventProcess = eventProcess;
    }

    public static void main(String... args) {
        Collection<EventHandler> collection = new ArrayList<>();
        collection.add(new LightEventProcessor());
        collection.add(new DoorEventProcessor());
        collection.add(new HallEventProcessor());
        collection.add(new AlarmEventProcessor());

        EventProcess eventProcess = new DecoratorDangerAlarmState(new EventProcessor(collection));


        Application app = new Application(new JsonHomeReader(), new RandomSensorEventReader(), eventProcess);
        app.execute();
    }

    public void execute() {
        // считываем состояние дома из файла
        SmartHome smartHome = homeReader.readHome();
        // начинаем цикл обработки событий
        new LoopEventHandler().performLoopEventHandle(smartHome, sensorEventReader, eventProcess);
    }
}
