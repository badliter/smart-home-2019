package ru.sbt.mipt.oop.eventprocessor;

import ru.sbt.mipt.oop.EventHandler;

import java.util.Collection;

public class CollectionEventProcessor {
    public static Collection<EventHandler> collection;

    public CollectionEventProcessor(Collection<EventHandler> collection) {
        this.collection = collection;
    }
}
