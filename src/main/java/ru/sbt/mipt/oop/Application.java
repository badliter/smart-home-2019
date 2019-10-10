package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        Application app = new Application(new JsonHomeReader());
        app.Execute();
    }

    private HomeReader homeReader;

    public Application(HomeReader homeReader){
        this.homeReader = homeReader;
    }

    public void Execute() throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = homeReader.readHome();
        // начинаем цикл обработки событий
        SensorEvent event = EventCreator.getNextSensorEvent();
        while (event != null) {
            EventHandling.doEventHandling(smartHome, event);
            event = EventCreator.getNextSensorEvent();
        }
    }
}
