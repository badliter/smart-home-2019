package ru.sbt.mipt.oop;

import java.util.Collection;

public interface Action {
    void act(Collection<Actionable> collection);
}
