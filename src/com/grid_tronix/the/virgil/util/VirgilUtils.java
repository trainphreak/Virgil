package com.grid_tronix.the.virgil.util;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VirgilUtils
{
    private static Permission permission;

    public static void sendConsoleMessage(final String message)
    {
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage(message);
    }

    public static boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    public static boolean hasPermission(final Player player, final String permission)
    {
        if (permission == null) // Subcommands may have a null permission, indicating that any player may use them
            return player.isOp();

        return VirgilUtils.permission.playerHas(player, permission);
    }
}
