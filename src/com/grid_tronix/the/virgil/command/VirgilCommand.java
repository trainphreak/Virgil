package com.grid_tronix.the.virgil.command;

import com.grid_tronix.the.virgil.Virgil;
import com.grid_tronix.the.virgil.util.VirgilUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class VirgilCommand implements CommandExecutor
{
    public VirgilCommand()
    {
        VirgilSubCommand.loadCommands();
    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String alias, String[] args)
    {
        if (args.length == 0)
        {
            args = new String[] { "help" };
        }
        for (final VirgilSubCommand subCommand : VirgilSubCommand.getCommands())
        {
            if (!subCommand.getSub().equalsIgnoreCase(args[0]))
                continue;

            if (subCommand.getPermission() != null && commandSender instanceof Player &&
                    !VirgilUtils.hasPermission((Player) commandSender, command.getPermission()))
            {
                commandSender.sendMessage(ChatColor.RED + Virgil.CHAT_PREFIX + "You do not have permission to use this command.");
                return true;
            }

            String[] newArgs;
            if (args.length > 1)
                newArgs = Arrays.copyOfRange(args, 1, args.length);
            else
                newArgs = new String[] {};
            subCommand.onCommand(commandSender, newArgs);
            return true;
        }

        commandSender.sendMessage(ChatColor.RED + Virgil.CHAT_PREFIX + "Unknown command. Try using \"/virgil help\"");
        return true;
    }
}
