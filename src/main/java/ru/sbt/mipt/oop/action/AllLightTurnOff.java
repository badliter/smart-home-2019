package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.WorkWithActionableCollection;

import java.util.Collection;

public class AllLightTurnOff implements Action {
    @Override
    public void act(Collection<Actionable> collection) {
        Actionable actionable = new WorkWithActionableCollection(collection).getLast();
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
