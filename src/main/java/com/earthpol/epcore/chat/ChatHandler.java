package com.earthpol.epcore.chat;

import com.earthpol.epcore.Main;
import com.earthpol.epcore.chat.listener.ChatListener;
import org.bukkit.Bukkit;

public class ChatHandler {

    public ChatHandler() {
        Bukkit.getPluginManager().registerEvents(new ChatListener(), Main.instance);
    }
}
