package com.earthpol.epcore.deathmessage.listener;

import com.earthpol.epcore.Main;
import com.earthpol.epcore.deathmessage.DeathMessageHandler;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownBlockType;
import com.palmergames.bukkit.towny.object.WorldCoord;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashSet;
import java.util.Set;

public class DeathMessageListener implements Listener {

    private TownyAPI api = TownyAPI.getInstance();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) throws NotRegisteredException {
        Player player = event.getEntity();
        Component deathMessage = event.deathMessage();

        event.deathMessage(null);

        Set<Player> recipients = new HashSet<>();

        TownBlock tb = api.getTownBlock(player.getLocation());
        if(tb != null && tb.getType() == TownBlockType.ARENA) {
            Town town;
            try {
                town = tb.getTown();
            } catch (NotRegisteredException e) {
                event.deathMessage(deathMessage);
                return;
            }

            Bukkit.getOnlinePlayers().forEach(online -> {
                if(online.equals(player) || online.equals(player.getKiller())) {
                    recipients.add(online);
                    return;
                }

                TownBlock recipientTB = api.getTownBlock(online.getLocation());
                if(recipientTB == null || recipientTB.getType() != TownBlockType.ARENA)
                    return;

                Town recipientTBTown;
                try {
                    recipientTBTown = recipientTB.getTown();
                } catch (NotRegisteredException e) {
                    return;
                }

                if(town.equals(recipientTBTown))
                    recipients.add(online);
            });
            // If a player is in the same town and in an arena plot, send a death message.
            recipients.forEach(recipient -> recipient.sendMessage(deathMessage));
        } else {
            if(Main.deathMessageHandler.isOnCooldown(player.getUniqueId())) {
                recipients.add(player);
                if(player.getKiller() != null);
                    recipients.add(player.getKiller());
            } else {
                recipients.addAll(Bukkit.getOnlinePlayers());

                Main.deathMessageHandler.putCooldown(player.getUniqueId());
            }
        }

    }
}
