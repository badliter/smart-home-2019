package ru.sbt.mipt.oop;

public class CheckIsHall implements Action {
    private String id;
    private Room room;
    private SmartHome smartHome;

    public CheckIsHall (String id){
        this.id = id;
    }

    @Override
    public void act(Actionable actionable) {
        if (actionable.getClass().equals(Door.class)){
                if (((Door)actionable).getId().equals(id) && room.getName().equals("hall"))
                {
                    smartHome.execute(new AllLightTurnOff());
                }
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

    @Override
    public Room getRoom() {
        return room;
    }

    @Override
    public void setRoom(Room room) {
        this.room = room;
    }
}
