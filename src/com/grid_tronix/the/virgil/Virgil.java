package com.grid_tronix.the.virgil;

import com.grid_tronix.the.virgil.command.VirgilCommand;
import com.grid_tronix.the.virgil.util.VirgilUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Virgil extends JavaPlugin
{
    public static final String CHAT_PREFIX = "[Virgil] ";

    private static com.grid_tronix.the.virgil.Virgil plugin;
    private FileConfiguration mainConfig;
    //private Listener chatListener;

    private int globalResponseCooldown;

    // kinda obvious
    public void onEnable()
    {
        com.grid_tronix.the.virgil.Virgil.plugin = this;
        this.mainConfig = getConfig();
        VirgilUtils.setupPermissions();
        this.setupConfig();
        this.loadConfig();
        this.setupListeners();
        this.setupCommands();
        this.sendEnabledMessage();
    }

    // kinda obvious
    public void onDisable()
    {
        this.getCommand("virgil").setExecutor(null);
        //HandlerList.unregisterAll(chatListener); // Uncomment once the listener exists
    }

    // helper method to setup config defaults
    private void setupConfig()
    {
        mainConfig.addDefault("global-response-cooldown", 2);
        mainConfig.options().copyDefaults(true);
        saveConfig();
    }

    // helper method to retrieve actual settings from the config
    private void loadConfig()
    {
        globalResponseCooldown = mainConfig.getInt("global-response-cooldown");
    }

    // helper method to register event listeners
    private void setupListeners()
    {
        //chatListener = null;
    }

    // helper method to register command executors
    private void setupCommands()
    {
        this.getCommand("virgil").setExecutor(new VirgilCommand());
    }

    // helper method to send the enabled message to the console
    private void sendEnabledMessage()
    {
        VirgilUtils.sendConsoleMessage(CHAT_PREFIX + "Virgil " + this.getDescription().getVersion() + " is enabled");
        VirgilUtils.sendConsoleMessage(CHAT_PREFIX + "Authors: " + this.getDescription().getAuthors());
    }

    // pretty obvious
    public static Virgil getPlugin()
    {
        return Virgil.plugin;
    }

    // pretty obvious
    public int getGlobalResponseCooldown()
    {
        return globalResponseCooldown;
    }
}