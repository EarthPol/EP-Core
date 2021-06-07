package com.earthpol.epcore.commands;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identity;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Map implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            TextComponent msg = new TextComponent("§b[Click Here to open the map]!");
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aOpens link to the DynMap on your web browser.").create()));
            msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://earthpol.com/map/"));
            player.sendMessage("§e============[MAP]============");
            player.sendMessage(msg);
            player.sendMessage("§e============================");
        } else {
            Bukkit.getLogger().info("[EP-Core]: You need to be a player to execute this command.");
        }

        return false;
    }
}
