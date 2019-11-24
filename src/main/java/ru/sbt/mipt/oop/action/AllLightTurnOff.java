package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.home.Light;

public class AllLightTurnOff implements Action {
    @Override
    public void act(Actionable actionable) {
        if (actionable.getClass().equals(Light.class)) {
            ((Light) actionable).setOn(false);
            sendCmd((Light) actionable);
        }
    }

    private void sendCmd(Light light) {
        SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
        CommandSender.sendCommand(command);
    }
}
