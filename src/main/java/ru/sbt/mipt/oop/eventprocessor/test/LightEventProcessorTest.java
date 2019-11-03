package ru.sbt.mipt.oop.eventprocessor.test;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.eventprocessor.LightEventProcessor;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.homeReader.JsonHomeReader;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LightEventProcessorTest {
    @Test
    public void turnOnLightInKitchen() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        new LightEventProcessor().handle(smartHome, event);
        assertEquals("Light 1 was turned on.", outContent.toString().replace("\r","").replace("\n",""));

        System.setOut(null);
    }

    @Test
    public void turnOffLightInBedRoom() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "4");
        new LightEventProcessor().handle(smartHome, event);
        assertEquals("Light 4 was turned off.", outContent.toString().replace("\r","").replace("\n",""));

        System.setOut(null);
    }

    @Test
    public void openDoorInKitchen() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        new LightEventProcessor().handle(smartHome, event);
        assertEquals("", outContent.toString());

        System.setOut(null);
    }

    @Test
    public void turnOffLightWithNotExistedId() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "10");
        new LightEventProcessor().handle(smartHome, event);
        assertEquals("", outContent.toString());

        System.setOut(null);
    }
}