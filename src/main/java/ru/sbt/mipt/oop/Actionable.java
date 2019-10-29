package ru.sbt.mipt.oop;

import java.util.Collection;

public interface Actionable {
    void execute(Action action, Collection<Actionable> collection);
}
