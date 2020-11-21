package com.gmf.mc.shop;

import com.gmf.mc.shop.event.MoneyEvent;
import com.gmf.mc.shop.event.MoneyEventType;
import com.gmf.mc.shop.listener.EventListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Locale;


public class ShopPlugin extends JavaPlugin {

    Logger logger = LoggerFactory.getLogger(ShopPlugin.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Arrays.stream(MoneyEventType.values()).anyMatch(n -> n.getName().equalsIgnoreCase(label.replace("s", "")))) {
            MoneyEvent event = new MoneyEvent(MoneyEventType.valueOf(label
                    .replace("s", "")
                    .toUpperCase(Locale.ENGLISH)));
            getServer().getPluginManager().callEvent(event);
            return true;
        }
        return super.onCommand(sender, command, label, args);
    }

    @Override
    public void onEnable() {
        logger.info("Plugin enabled");
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
    }

}
