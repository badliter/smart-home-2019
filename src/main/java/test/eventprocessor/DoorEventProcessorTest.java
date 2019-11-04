package test.eventprocessor;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.eventprocessor.DoorEventProcessor;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.homeReader.JsonHomeReader;
import ru.sbt.mipt.oop.sensor.DoorSensorEvent;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.sensor.DoorEventType.*;

public class DoorEventProcessorTest {

    @Test
    public void closeDoorInKitchen() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new DoorSensorEvent(DOOR_CLOSED, "1");
        new DoorEventProcessor().handle(smartHome, event);
        assertEquals("Door 1 was closed.", outContent.toString().replace("\r","").replace("\n",""));

        System.setOut(null);
    }

    @Test
    public void openDoorInBathRoom() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new DoorSensorEvent(DOOR_OPEN, "2");
        new DoorEventProcessor().handle(smartHome, event);
        assertEquals("Door 2 was opened.", outContent.toString().replace("\r","").replace("\n",""));

        System.setOut(null);
    }

    @Test
    public void tryWorkWithNotExistedRoom() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        SensorEvent event = new DoorSensorEvent(DOOR_OPEN, "5");
        new DoorEventProcessor().handle(smartHome, event);
        assertEquals("", outContent.toString());

        System.setOut(null);
    }
}