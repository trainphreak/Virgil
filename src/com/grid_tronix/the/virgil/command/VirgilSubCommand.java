package com.grid_tronix.the.virgil.command;

import com.grid_tronix.the.virgil.command.sub.SubCommandHelp;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class VirgilSubCommand
{
    private static List<VirgilSubCommand> commands;

    public abstract String getSub();

    public abstract String getPermission();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void onCommand(final CommandSender commandSender, final String[] args);

    public static void loadCommands()
    {
        VirgilSubCommand.commands = new ArrayList<>();

        loadCommand(new SubCommandHelp());
    }

    private static void loadCommand(final VirgilSubCommand subCommand)
    {
        VirgilSubCommand.commands.add(subCommand);
    }

    public static List<VirgilSubCommand> getCommands()
    {
        return VirgilSubCommand.commands;
    }
}
