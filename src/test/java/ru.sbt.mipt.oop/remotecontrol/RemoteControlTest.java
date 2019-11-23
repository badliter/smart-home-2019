package ru.sbt.mipt.oop.remotecontrol;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.configuration.RemoteControlConfiguration;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.alarm.ActivatedAlarmState;
import ru.sbt.mipt.oop.home.alarm.DangerAlarmState;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    private SmartHome smartHome;
    private RemoteControl rc1;
    private RemoteControl rc2;

    @BeforeEach
    public void configBeforeTests(){
        ApplicationContext context = new AnnotationConfigApplicationContext(RemoteControlConfiguration.class);
        smartHome = context.getBean(SmartHome.class);
        rc1 = (RemoteControl) context.getBean("remoteControlImpVar1");
        rc2 = (RemoteControl) context.getBean("remoteControlImpVar2");
    }

    @Test
    public void checkExecuteCommandCmdActivateAlarm_RC1() {
        rc1.onButtonPressed("A");
        assertTrue(smartHome.getHomeAlarm().getAlarmState() instanceof ActivatedAlarmState);
    }

    @Test
    public void checkExecuteAllLightTurnOn_RC1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        rc1.onButtonPressed("B");
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

        rc1.onButtonPressed("C");
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

        rc1.onButtonPressed("D");

        assertEquals("Door 4 in room hall was closed.", outContent.toString().replace("\r", "").replace("\n", ""));
    }

    @Test
    public void checkExecuteCommandCmdDangerAlarmTurnOn_RC2() {
        rc2.onButtonPressed("3");
        assertTrue(smartHome.getHomeAlarm().getAlarmState() instanceof DangerAlarmState);
    }

    @Test
    public void checkExecuteAllLightInHall_RC2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        rc2.onButtonPressed("4");
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

        rc1.onButtonPressed("3");

        assertEquals("", outContent.toString().replace("\r", "").replace("\n", ""));
    }

    @Test
    public void checkExecuteNotExsitedButton_RC2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        rc2.onButtonPressed("qwerty");

        assertEquals("", outContent.toString().replace("\r", "").replace("\n", ""));
    }
}
