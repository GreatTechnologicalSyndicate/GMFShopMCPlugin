package com.gmf.mc.shop.listener;

import com.gmf.mc.shop.event.MoneyEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventListener implements Listener {
    Logger logger = LoggerFactory.getLogger(EventListener.class);

    @EventHandler
    public void onMoneyOperation(MoneyEvent event){
        logger.info(event.getEventType().getName());
    }
}
