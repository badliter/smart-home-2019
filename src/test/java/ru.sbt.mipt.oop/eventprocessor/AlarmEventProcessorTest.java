package ru.sbt.mipt.oop.eventprocessor;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.home.alarm.ActivatedAlarmState;
import ru.sbt.mipt.oop.home.alarm.DangerAlarmState;
import ru.sbt.mipt.oop.home.alarm.DeactivatedAlarmState;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.alarm.HomeAlarm;
import ru.sbt.mipt.oop.homereader.JsonHomeReader;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlarmEventProcessorTest {
    @Test
    public void checkAlarmDefaultStateIsDeactivated() {
        SmartHome smartHome = new JsonHomeReader().readHome();
        assertTrue(smartHome.getHomeAlarm().getAlarmState() instanceof DeactivatedAlarmState);
    }

    @Test
    public void activateAlarmWithRightCode() {
        SmartHome smartHome = new JsonHomeReader().readHome();
        activateAlarm(smartHome, "0");
        assertTrue(smartHome.getHomeAlarm().getAlarmState() instanceof ActivatedAlarmState);
    }

    @Test
    public void activateAlarmWithWrongCode() {
        SmartHome smartHome = new JsonHomeReader().readHome();
        activateAlarm(smartHome, "Q!WW#EW#REFER$EFEFF$");
        assertTrue(smartHome.getHomeAlarm().getAlarmState() instanceof ActivatedAlarmState);
    }

    @Test
    public void deactivateAlarmWithRightCode() {
        SmartHome smartHome = new JsonHomeReader().readHome();
        activateAlarm(smartHome, "0");
        deactivateAlarm(smartHome, "0");
        assertTrue(smartHome.getHomeAlarm().getAlarmState() instanceof DeactivatedAlarmState);
    }

    @Test
    public void deactivateAlarmWithWrongCode() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        activateAlarm(smartHome, "0");
        deactivateAlarm(smartHome, "Q!WW#EW#REFER$EFEFF$");
        assertEquals("Message: Dangerous. Your home is unsafe!!!", outContent.toString().replace("\r", "").replace("\n", ""));
        assertTrue(smartHome.getHomeAlarm().getAlarmState() instanceof DangerAlarmState);

        System.setOut(null);
    }

    @Test
    public void goToDangerAlarmStateFromDeactivatedSate() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        dangerAlarm(smartHome);
        assertEquals("Message: Dangerous. Your home is unsafe!!!", outContent.toString().replace("\r", "").replace("\n", ""));
        assertTrue(smartHome.getHomeAlarm().getAlarmState() instanceof DangerAlarmState);

        System.setOut(null);
    }

    @Test
    public void goToDangerAlarmStateFromActivatedSate() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        activateAlarm(smartHome, "0");
        assertTrue(smartHome.getHomeAlarm().getAlarmState() instanceof ActivatedAlarmState);
        dangerAlarm(smartHome);
        assertEquals("Message: Dangerous. Your home is unsafe!!!", outContent.toString().replace("\r", "").replace("\n", ""));
        assertTrue(smartHome.getHomeAlarm().getAlarmState() instanceof DangerAlarmState);

        System.setOut(null);
    }

    @Test
    public void callDangerStateFromDangerAlarmState() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SmartHome smartHome = new JsonHomeReader().readHome();
        dangerAlarm(smartHome);
        assertEquals("Message: Dangerous. Your home is unsafe!!!", outContent.toString().replace("\r", "").replace("\n", ""));
        assertTrue(smartHome.getHomeAlarm().getAlarmState() instanceof DangerAlarmState);

        System.setOut(null);
    }

    public void activateAlarm(SmartHome smartHome, String code) {
        smartHome.execute(homeAlarm -> {
            if (homeAlarm instanceof HomeAlarm) {
                ((HomeAlarm) homeAlarm).activate(code);
            }
        });
    }

    public void deactivateAlarm(SmartHome smartHome, String code) {
        smartHome.execute(homeAlarm -> {
            if (homeAlarm instanceof HomeAlarm) {
                ((HomeAlarm) homeAlarm).deactivate(code);
            }
        });
    }

    public void dangerAlarm(SmartHome smartHome) {
        smartHome.execute(homeAlarm -> {
            if (homeAlarm instanceof HomeAlarm) {
                ((HomeAlarm) homeAlarm).danger();
            }
        });
    }
}