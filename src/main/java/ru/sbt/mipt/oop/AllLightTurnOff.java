package ru.sbt.mipt.oop;

public class AllLightTurnOff implements Action {
    private Room room;
    private SmartHome smartHome;

    @Override
    public void act(Actionable actionable) {
        if (actionable.getClass().equals(Light.class)){
            ((Light)actionable).setOn(false);
            sendCmd((Light)actionable);
        }
    }

    private void sendCmd(Light light){
        SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
        CommandSender.sendCommand(command);
    }

    @Override
    public SmartHome getSmartHome() {
        return smartHome;
    }

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public Room getRoom() {
        return room;
    }

    @Override
    public void setRoom(Room room) {
        this.room = room;
    }
}
