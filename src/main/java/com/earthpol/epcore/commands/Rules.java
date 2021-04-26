package com.earthpol.epcore.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rules implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            TextComponent msg = new TextComponent("§b[Click here to view the rules]!");
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aOpens link to the rules on your web browser.").create()));
            msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://earthpol.com/rules"));
            player.sendMessage("§e===========[RULES]===========");
            player.sendMessage(msg);
            player.sendMessage("§e============================");
        }

        return false;
    }
}
