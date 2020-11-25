package com.gmf.mc.shop.listener;

import com.gmf.mc.shop.event.MoneyEvent;
import com.gmf.mc.shop.manager.MoneyEventManager;
import com.gmf.mc.shop.model.MoneyModels;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.lang.reflect.InvocationHandler;

public class MoneyEventListener implements Listener {
    Logger logger = LoggerFactory.getLogger(MoneyEventListener.class);

    public MoneyEventListener() {

    }

    @EventHandler
    public void onMoneyOperation(MoneyEvent event) {
        logger.info(event.getEventType().getName());
        MoneyEventManager.getInstance().execute(event);
        /**switch (event.getEventType()) {
        **    case PAY -> event.getSender().sendMessage(webResource.path("pay")
        **            .post(String.class, new MoneyModels.Pay(event.getSender().getName(),
        **                                        event.getArgs()[0],
        **                                        Double.parseDouble(event.getArgs()[1])).toString()));
        **    case BALANCE -> event.getSender().sendMessage(webResource.path("balance")
        **            .post(String.class, new MoneyModels.Balance(event.getSender().getName()).toString()));
        **    case RATING -> event.getSender().sendMessage(webResource.path("rating")
        **            .post(String.class, ""));
        **}
        * */
    }

    @EventHandler
    public void anyEvent(AsyncPlayerChatEvent event) {
        logger.info("{}", event);
    }

}
