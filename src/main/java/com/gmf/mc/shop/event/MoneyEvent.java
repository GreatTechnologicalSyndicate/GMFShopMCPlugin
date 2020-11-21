package com.gmf.mc.shop.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

@Getter
@AllArgsConstructor
public class MoneyEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final MoneyEventType eventType;

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
