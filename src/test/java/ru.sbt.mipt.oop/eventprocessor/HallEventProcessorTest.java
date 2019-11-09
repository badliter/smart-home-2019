package ru.sbt.mipt.oop.eventprocessor;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.homereader.JsonHomeReader;
import ru.sbt.mipt.oop.sensor.DoorSensorEvent;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.sensor.DoorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensor.DoorEventType.DOOR_OPEN;

class HallEventProcessorTest {
    @Test
    public void closeDoorInHall() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new DoorSensorEvent(DOOR_CLOSED, "4");
        new HallEventProcessor().handle(smartHome, event);
        String expected = "";
        for (int i = 1; i < 10; i++) {
            expected = expected + "Pretent we're sending command SensorCommand{type=LIGHT_OFF, objectId='" + i + "'}";
        }
        assertEquals(expected.replace("\r", "").replace("\n", ""), outContent.toString().replace("\r", "").replace("\n", ""));

        System.setOut(null);
    }

    @Test
    public void closeDoorNotInHall() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new DoorSensorEvent(DOOR_CLOSED, "1");
        new HallEventProcessor().handle(smartHome, event);
        assertEquals("", outContent.toString().replace("\r", "").replace("\n", ""));

        System.setOut(null);
    }

    @Test
    public void openDoorInHall() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new DoorSensorEvent(DOOR_OPEN, "4");
        new HallEventProcessor().handle(smartHome, event);
        assertEquals("", outContent.toString());

        System.setOut(null);
    }
}