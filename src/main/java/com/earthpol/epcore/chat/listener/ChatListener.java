package com.earthpol.epcore.chat.listener;

import com.palmergames.bukkit.TownyChat.events.AsyncChatHookEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncChatHookEvent event) {
        if (!event.getChannel().getName().equalsIgnoreCase("general"))
            return;

        for (char c : event.getMessage().toCharArray()) {
            if (c > 127) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("§cYou may only speak English in general chat.");
            }
        }
    }
}
