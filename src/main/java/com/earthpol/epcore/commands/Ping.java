package com.earthpol.epcore.commands;

import com.earthpol.epcore.Main;
import net.md_5.bungee.api.chat.TextComponent;
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
            Bukkit.getLogger().info(Main.prefix + "You need to be a player to execute this command.");
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

        int ping = target.getPing();

        if(ping <= 80){
            TextComponent msg = new TextComponent(Main.prefix + "§6" + target.displayName() + "§6's ping: §a" + target.getPing());
            sender.sendMessage(msg);
        } else if (ping <= 100 && ping >= 81){
            TextComponent msg = new TextComponent(Main.prefix + "§6" + target.displayName() + "§6's ping: §2" + target.getPing());
            sender.sendMessage(msg);
        } else if (ping <= 150 && ping >= 101) {
            TextComponent msg = new TextComponent(Main.prefix + "§6" + target.displayName() + "§6's ping: §e" + target.getPing());
            sender.sendMessage(msg);
        } else if (ping <= 200 && ping >= 151) {
            TextComponent msg = new TextComponent(Main.prefix + "§6" + target.displayName() + "§6's ping: &c" + target.getPing());
            sender.sendMessage(msg);
        } else if (ping >= 201) {
            TextComponent msg = new TextComponent(Main.prefix + "§6" + target.displayName() + "§6's ping: &4" + target.getPing());
            sender.sendMessage(msg);
        }

        return false;
    }
}
