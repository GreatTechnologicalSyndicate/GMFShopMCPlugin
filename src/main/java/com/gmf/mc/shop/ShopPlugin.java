package com.gmf.mc.shop;

import com.gmf.mc.shop.event.MoneyEvent;
import com.gmf.mc.shop.event.MoneyEventType;
import com.gmf.mc.shop.listener.MoneyEventListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Locale;


public class ShopPlugin extends JavaPlugin {

    Logger logger = LoggerFactory.getLogger(ShopPlugin.class);

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
        getServer().getPluginManager().registerEvents(new MoneyEventListener(transactionServer.getString("url")), this);
    }

    @Override
    public void onDisable() {
        getServer().shutdown();
    }

}
