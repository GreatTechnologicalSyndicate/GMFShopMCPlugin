package com.gmf.mc.shop.manager;

import com.gmf.mc.shop.event.MoneyEvent;
import com.gmf.mc.shop.event.MoneyEventType;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class MoneyEventManager {
    private static MoneyEventManager instance;
    private Multimap<MoneyEventType, MoneyEventListener> listeners;

    public void addListener(MoneyEventType eventType, MoneyEventListener listener){
        listeners.put(eventType, listener);
    }

    public void execute(MoneyEvent event){
        listeners.asMap()
                .get(event.getEventType())
                .forEach(moneyEventListener -> moneyEventListener.listen(event));
    }

    private MoneyEventManager() {
        listeners = ArrayListMultimap.create();
    }

    public static MoneyEventManager getInstance() {
        if (instance == null) {
            instance = new MoneyEventManager();
        }
        return instance;
    }
}
