package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LightEventProcessorTest {
    @Test
    public void turnOnLightInKitchen() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        new LightEventProcessor().handle(smartHome, event);
        assertEquals("Light 1 in room kitchen was turned on.\r\n", outContent.toString());

        System.setOut(null);
    }

    @Test
    public void turnOffLightInBedRoom() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "4");
        new LightEventProcessor().handle(smartHome, event);
        assertEquals("Light 4 in room bedroom was turned off.\r\n", outContent.toString());

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