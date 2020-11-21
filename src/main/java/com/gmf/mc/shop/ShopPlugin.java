package com.gmf.mc.shop;

import lombok.SneakyThrows;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;


public class ShopPlugin extends JavaPlugin {

    Logger logger = LoggerFactory.getLogger(ShopPlugin.class);

    @Override
    public void onEnable() {
        logger.info("Plugin enabled");
    }

    @Override
    public void onDisable() {
    }

}
