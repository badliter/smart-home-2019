package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class HallEventProcessorTest {
    @Test
    public void closeDoorInHall() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");
        new HallEventProcessor().handle(smartHome, event);
        String expected = "";
        for (int i = 1; i < 10; i++){
            expected = expected + "Pretent we're sending command SensorCommand{type=LIGHT_OFF, objectId='" + i + "'}\r\n";
        }
        assertEquals(expected, outContent.toString());

        System.setOut(null);
    }

    @Test
    public void openDoorInHall() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "4");
        new HallEventProcessor().handle(smartHome, event);
        assertEquals("", outContent.toString());

        System.setOut(null);
    }
}