package com.grid_tronix.the.virgil.command.sub;

import com.grid_tronix.the.virgil.Virgil;
import com.grid_tronix.the.virgil.command.VirgilSubCommand;
import com.grid_tronix.the.virgil.util.VirgilUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class SubCommandHelp extends VirgilSubCommand
{
    @Override
    public String getSub()
    {
        return "help";
    }

    @Override
    public String getPermission()
    {
        return null;
    }

    @Override
    public String getDescription()
    {
        return "Display general plugin information";
    }

    @Override
    public String getSyntax()
    {
        return "/virgil help";
    }

    @Override
    public void onCommand(CommandSender commandSender, String[] args)
    {
        PluginDescriptionFile desc = Virgil.getPlugin().getDescription();
        commandSender.sendMessage(ChatColor.GREEN + "Virgil version " + desc.getVersion());
        commandSender.sendMessage(ChatColor.GREEN + "Authors: " + desc.getAuthors());
        commandSender.sendMessage(ChatColor.GOLD + "Available Commands:");
        for(final VirgilSubCommand subCommand : VirgilSubCommand.getCommands())
        {
            if (commandSender instanceof Player && !VirgilUtils.hasPermission((Player) commandSender, subCommand.getPermission()))
                continue;

            commandSender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + subCommand.getSyntax() +
                ChatColor.GOLD + "- " + ChatColor.AQUA + subCommand.getDescription());
        }
    }
}
