package ru.sbt.mipt.oop;

public class Application {

    private HomeReader homeReader;
    private SensorEventReader sensorEventReader;

    public Application(HomeReader homeReader, SensorEventReader sensorEventReader) {
        this.homeReader = homeReader;
        this.sensorEventReader = sensorEventReader;
    }

    public static void main(String... args) {
        Application app = new Application(new JsonHomeReader(), new RandomSensorEventReader());
        app.execute();
    }

    public void execute() {
        // считываем состояние дома из файла
        SmartHome smartHome = homeReader.readHome();
        // начинаем цикл обработки событий
        performLoopEventHandle(smartHome);
    }

    private void performLoopEventHandle(SmartHome smartHome){
        SensorEvent event = sensorEventReader.getNextSensorEvent();
        while (event != null) {
            new EventProcess().processEvent(smartHome, event);
            event = sensorEventReader.getNextSensorEvent();
        }
    }
}
