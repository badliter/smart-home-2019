package ru.sbt.mipt.oop;

public class LightTurnOn implements Action {
    private boolean turnOn;
    private String id;
    private Room room;
    private SmartHome smartHome;

    public LightTurnOn(boolean turnOn, String id){
        this.turnOn = turnOn;
        this.id = id;
    }

    @Override
    public void act(Actionable actionable) {
        if (actionable.getClass().equals(Light.class) && ((Light)actionable).getId().equals(id)){
            ((Light)actionable).setOn(turnOn);
            writeToConsole(turnOn, ((Light)actionable));
        }
    }

    @Override
    public SmartHome getSmartHome() {
        return smartHome;
    }

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private void writeToConsole(boolean turnOn, Light light){
        if (turnOn) {
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
        } else {
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
        }
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
