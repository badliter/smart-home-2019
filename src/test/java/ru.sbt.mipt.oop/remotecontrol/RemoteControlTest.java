package ru.sbt.mipt.oop.remotecontrol;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.alarm.ActivatedAlarmState;
import ru.sbt.mipt.oop.home.alarm.DangerAlarmState;
import ru.sbt.mipt.oop.homereader.JsonHomeReader;
import ru.sbt.mipt.oop.remotecontrolreader.RandomRemoteControlReader;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.rmi.Remote;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoteControlTest {
//      RemoteControl 1
//    A - ActivateAlarm
//    B - AllLightTurnOn
//    C - AllLightTurnOff
//    D - CloseHallDoor
//    1 - DangerAlarmTurnOn
//    2 - TurnOnLightInHall
//    3 - null
//    4 - null

//      RemoteControl 2
//    A - null
//    B - null
//    C - ActivateAlarm
//    D - AllLightTurnOn
//    1 - AllLightTurnOff
//    2 - CloseHallDoor
//    3 - DangerAlarmTurnOn
//    4 - TurnOnLightInHall

    @Test
    public void checkExecuteCommandCmdActivateAlarm_RC1() {
        SmartHome smartHome = new JsonHomeReader().readHome();
        Map<String, RemoteControl> mapRC = new RandomRemoteControlReader(smartHome).readRemoteControl();
        mapRC.get("1").onButtonPressed("A");
        assertTrue(smartHome.getHomeAlarm().getAlarmState() instanceof ActivatedAlarmState);
    }

    @Test
    public void checkExecuteAllLightTurnOn_RC1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        Map<String, RemoteControl> mapRC = new RandomRemoteControlReader(smartHome).readRemoteControl();
        mapRC.get("1").onButtonPressed("B");
        String expected = "";
        for (int i = 1; i < 10; i++) {
            expected = expected + "Light " + i + " was turned on.";
        }

        assertEquals(expected, outContent.toString().replace("\r", "").replace("\n", ""));
    }

    @Test
    public void checkExecuteAllLightTurnOff_RC1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        Map<String, RemoteControl> mapRC = new RandomRemoteControlReader(smartHome).readRemoteControl();
        mapRC.get("1").onButtonPressed("C");
        String expected = "";
        for (int i = 1; i < 10; i++) {
            expected = expected + "Light " + i + " was turned off.";
        }

        assertEquals(expected, outContent.toString().replace("\r", "").replace("\n", ""));
    }

    @Test
    public void checkExecuteCloseHallDoor_RC1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        Map<String, RemoteControl> mapRC = new RandomRemoteControlReader(smartHome).readRemoteControl();
        mapRC.get("1").onButtonPressed("D");

        assertEquals("Door 4 in room hall was closed.", outContent.toString().replace("\r", "").replace("\n", ""));
    }

    @Test
    public void checkExecuteCommandCmdDangerAlarmTurnOn_RC2() {
        SmartHome smartHome = new JsonHomeReader().readHome();
        Map<String, RemoteControl> mapRC = new RandomRemoteControlReader(smartHome).readRemoteControl();
        mapRC.get("2").onButtonPressed("3");
        assertTrue(smartHome.getHomeAlarm().getAlarmState() instanceof DangerAlarmState);
    }

    @Test
    public void checkExecuteAllLightInHall_RC2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        Map<String, RemoteControl> mapRC = new RandomRemoteControlReader(smartHome).readRemoteControl();
        mapRC.get("2").onButtonPressed("4");
        String expected = "";
        for (int i = 7; i < 10; i++) {
            expected = expected + "Light " + i + " in room hall was turned off.";
        }

        assertEquals(expected, outContent.toString().replace("\r", "").replace("\n", ""));
    }

    @Test
    public void checkExecuteNullButton_RC1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        Map<String, RemoteControl> mapRC = new RandomRemoteControlReader(smartHome).readRemoteControl();
        mapRC.get("1").onButtonPressed("3");

        assertEquals("", outContent.toString().replace("\r", "").replace("\n", ""));
    }

    @Test
    public void checkExecuteNotExsitedButton_RC2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        Map<String, RemoteControl> mapRC = new RandomRemoteControlReader(smartHome).readRemoteControl();
        mapRC.get("2").onButtonPressed("qwerty");

        assertEquals("", outContent.toString().replace("\r", "").replace("\n", ""));
    }
}
