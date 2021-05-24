package com.earthpol.epcore.deathmessage;

import com.earthpol.epcore.Main;
import com.earthpol.epcore.deathmessage.listener.DeathMessageListener;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DeathMessageHandler {

    // Milliseconds
    public static final int DEATH_MESSAGE_COOLDOWN = 30 * 1000;

    private static Map<UUID, Long> deathMessageCooldown = new ConcurrentHashMap<>();

    public DeathMessageHandler() {
        Bukkit.getPluginManager().registerEvents(new DeathMessageListener(), Main.instance);

        new BukkitRunnable() {

            @Override
            public void run() {
                deathMessageCooldown.keySet().removeIf(uuid -> !isOnCooldown(uuid));
            }
        }.runTaskTimerAsynchronously(Main.instance, 200, 200);
    }

    public void putCooldown(UUID uuid) {
        deathMessageCooldown.put(uuid, System.currentTimeMillis() + DEATH_MESSAGE_COOLDOWN);
    }

    public boolean isOnCooldown(UUID uuid) {
        if (!deathMessageCooldown.containsKey(uuid))
            return false;

        return deathMessageCooldown.get(uuid) > System.currentTimeMillis();
    }
}
