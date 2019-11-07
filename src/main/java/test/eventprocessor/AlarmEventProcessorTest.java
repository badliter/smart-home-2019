package test.eventprocessor;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.home.alarm.ActivatedAlarmState;
import ru.sbt.mipt.oop.home.alarm.DangerAlarmState;
import ru.sbt.mipt.oop.home.alarm.DeactivatedAlarmState;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.homeReader.JsonHomeReader;

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
//
//    @Test
//    public void activateAlarmWithRightCode() {
//        SmartHome smartHome = new JsonHomeReader().readHome();
//        smartHome.getHomeAlarm().activate(new RandomAlarmReader().readAlarm().getCode());
//        assertEquals(new ActivatedAlarmState(new RandomAlarmReader().readAlarm()).STATE, ((ActivatedAlarmState) smartHome.getHomeAlarm().getAlarmState()).STATE);
//    }
//
//    @Test
//    public void activateAlarmWithWrongCode() {
//        SmartHome smartHome = new JsonHomeReader().readHome();
//        smartHome.getHomeAlarm().activate("Q!WW#EW#REFER$EFEFF$");
//        assertEquals(new DeactivatedAlarmState(new RandomAlarmReader().readAlarm()).STATE, ((DeactivatedAlarmState) smartHome.getHomeAlarm().getAlarmState()).STATE);
//    }
//
//    @Test
//    public void deactivateAlarm() {
//        SmartHome smartHome = new JsonHomeReader().readHome();
//        smartHome.getHomeAlarm().activate(new RandomAlarmReader().readAlarm().getCode());
//        smartHome.getHomeAlarm().deactivate(new RandomAlarmReader().readAlarm().getCode());
//        assertEquals(new DeactivatedAlarmState(new RandomAlarmReader().readAlarm()).STATE, ((DeactivatedAlarmState) smartHome.getHomeAlarm().getAlarmState()).STATE);
//    }
//
//    @Test
//    public void deactivateAlarmWithWrongCode() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        SmartHome smartHome = new JsonHomeReader().readHome();
//        smartHome.getHomeAlarm().activate(new RandomAlarmReader().readAlarm().getCode());
//        smartHome.getHomeAlarm().deactivate("Q!WW#EW#REFER$EFEFF$");
//        assertEquals("Message: Dangerous. Your home is unsafe!!!", outContent.toString().replace("\r","").replace("\n",""));
//        assertEquals(new DangerAlarmState(new RandomAlarmReader().readAlarm()).STATE, ((DangerAlarmState) smartHome.getHomeAlarm().getAlarmState()).STATE);
//
//        System.setOut(null);
//    }
}