package com.wz.latte_core.delegate.web.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * @author wangzhen
 * @date 2019/06/02
 */
public class EventManager {

    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {
    }

    private static class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@NonNull String name, @NonNull Event event) {
        EVENTS.put(name, event);
        return this;
    }

    public Event creaetEvent(@NonNull String action) {
        final Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }
}
