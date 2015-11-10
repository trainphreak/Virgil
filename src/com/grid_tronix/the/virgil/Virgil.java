package com.grid_tronix.the.virgil;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Virgil extends JavaPlugin
{
    public static final String CHAT_PREFIX = "[Virgil] ";

    private static com.grid_tronix.the.virgil.Virgil plugin;
    private FileConfiguration mainConfig;
    private static Permission permission;

    private int globalResponseCooldown;

    // kinda obvious
    public void onEnable()
    {
        com.grid_tronix.the.virgil.Virgil.plugin = this;
        this.mainConfig = getConfig();
        this.setupPermissions();
        this.setupConfig();
        this.loadConfig();
        this.setupListeners();
        this.setupCommands();
        this.sendEnabledMessage();
    }

    // kinda obvious
    public void onDisable()
    {

    }

    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private void setupConfig()
    {
        mainConfig.addDefault("global-response-cooldown", 2);
        mainConfig.options().copyDefaults(true);
        saveConfig();
    }

    private void loadConfig()
    {
        globalResponseCooldown = mainConfig.getInt("global-response-cooldown");
    }

    private void setupListeners()
    {

    }

    private void setupCommands()
    {

    }

    private void sendEnabledMessage()
    {
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage(CHAT_PREFIX + "Virgil " + this.getDescription().getVersion() + " is enabled");
        console.sendMessage(CHAT_PREFIX + "Authors: " + this.getDescription().getAuthors());
    }
}