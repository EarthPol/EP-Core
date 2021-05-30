package com.earthpol.epcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Ping implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info("[EP-Core]: You need to be a player to execute this command.");
            return false;
        }

        Player target;

        if (args.length > 0) {
            target = Bukkit.getPlayer(args[0]);
            if (target == null || !target.isOnline())
                target = (Player) sender;
        } else {
            target = (Player) sender;
        }

        sender.sendMessage(target.displayName().toString() + ChatColor.YELLOW + "''s ping: " + ChatColor.GREEN + target.getPing());

        return false;
    }
}
