package com.gmf.mc.shop;

import com.gmf.mc.shop.event.MoneyEvent;
import com.gmf.mc.shop.event.MoneyEventType;
import com.gmf.mc.shop.listener.MoneyEventListener;
import com.gmf.mc.shop.manager.MoneyEventManager;
import com.gmf.mc.shop.model.MoneyModels;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Locale;


public class ShopPlugin extends JavaPlugin {

    Logger logger = LoggerFactory.getLogger(ShopPlugin.class);

    public ShopPlugin() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Arrays.stream(MoneyEventType.values()).anyMatch(n -> n.getName().equalsIgnoreCase(label))) {
            MoneyEvent event = new MoneyEvent(MoneyEventType.valueOf(label.toUpperCase(Locale.ENGLISH)), args, sender);
            getServer().getPluginManager().callEvent(event);
            return true;
        }
        return super.onCommand(sender, command, label, args);
    }

    @Override
    public void onEnable() {
        saveConfig();
        FileConfiguration configuration = getConfig();
        ConfigurationSection transactionServer = configuration.getConfigurationSection("transactionServer");
        assert transactionServer != null;
        getServer().getPluginManager().registerEvents(new MoneyEventListener(), this);
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource(transactionServer.getString("url"));

        MoneyEventManager.getInstance().addListener(MoneyEventType.BALANCE,
                event -> event.getSender().sendMessage(webResource.path("balance")
                        .post(String.class, new MoneyModels.Balance(event.getSender().getName()).toString())));
        MoneyEventManager.getInstance().addListener(MoneyEventType.RATING,
                event -> event.getSender().sendMessage(webResource.path("rating")
                        .post(String.class, "")));
        MoneyEventManager.getInstance().addListener(MoneyEventType.PAY,
                event -> event.getSender().sendMessage(webResource.path("pay")
                        .post(String.class, new MoneyModels.Pay(event.getSender().getName(),
                                event.getArgs()[0],
                                Double.parseDouble(event.getArgs()[1])).toString())));
    }

    @Override
    public void onDisable() {
        getServer().shutdown();
    }

}
