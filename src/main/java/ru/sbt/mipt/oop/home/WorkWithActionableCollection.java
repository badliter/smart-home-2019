package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.Actionable;
import java.util.Collection;

public class WorkWithActionableCollection {
    private Collection<Actionable> collection;

    public WorkWithActionableCollection(Collection<Actionable> collection) {
        this.collection = collection;
    }

    public SmartHome getSmartHome() {
        return (SmartHome) get(SmartHome.class);
    }

    public Room getRoom() {
        return (Room) get(Room.class);
    }

    public Actionable getLast() {
        if (collection.size() > 1 ){
            return (Actionable) collection.toArray()[collection.size() - 1];
        }
        return null;
    }

    private Actionable get(Class<?> clazz) {
        for (Actionable actionable : collection) {
            if (actionable.getClass().equals(clazz)) {
                return actionable;
            }
        }
        return null;
    }
}
