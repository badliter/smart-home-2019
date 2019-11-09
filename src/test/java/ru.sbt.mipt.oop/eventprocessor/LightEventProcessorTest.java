package ru.sbt.mipt.oop.eventprocessor;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.eventprocessor.LightEventProcessor;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.homereader.JsonHomeReader;
import ru.sbt.mipt.oop.sensor.LightSensorEvent;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.sensor.LightEventType.*;

class LightEventProcessorTest {
    @Test
    public void turnOnLightInKitchen() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new LightSensorEvent(LIGHT_ON, "1");
        new LightEventProcessor().handle(smartHome, event);
        assertEquals("Light 1 in room kitchen was turned on.", outContent.toString().replace("\r", "").replace("\n", ""));

        System.setOut(null);
    }

    @Test
    public void turnOffLightInBedRoom() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new LightSensorEvent(LIGHT_OFF, "4");
        new LightEventProcessor().handle(smartHome, event);
        assertEquals("Light 4 in room bedroom was turned off.", outContent.toString().replace("\r", "").replace("\n", ""));

        System.setOut(null);
    }

    @Test
    public void turnOffLightWithNotExistedId() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new LightSensorEvent(LIGHT_OFF, "10");
        new LightEventProcessor().handle(smartHome, event);
        assertEquals("", outContent.toString());

        System.setOut(null);
    }
}