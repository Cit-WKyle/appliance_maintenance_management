package com.appl_maint_mngt.event_bus.common;

import java.util.Observable;

/**
 * Created by Kyle on 20/03/2017.
 */

public class LocalEventBus extends Observable {
    private static final LocalEventBus ourInstance = new LocalEventBus();

    public static LocalEventBus getInstance() {
        return ourInstance;
    }

    private LocalEventBus() {
    }

    public void sendEvent(String event) {
        updateObservers(event);
    }
    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
