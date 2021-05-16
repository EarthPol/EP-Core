package com.earthpol.epcore;

import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlockType;
import com.palmergames.bukkit.towny.object.WorldCoord;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class EventListener implements Listener {
    boolean mobSpawning;

    @EventHandler
    public void preMobSpawn(PreCreatureSpawnEvent event) {
        EntityType ent = event.getType();

        if (ent == EntityType.RABBIT ||
                ent == EntityType.POLAR_BEAR ||
                ent == EntityType.DONKEY ||
                ent == EntityType.MULE ||
                ent == EntityType.BAT ||
                ent == EntityType.SQUID ||
                ent == EntityType.COD ||
                ent == EntityType.ENDERMITE ||
                ent == EntityType.TROPICAL_FISH ||
                ent == EntityType.LLAMA) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void playerLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        int onlinePlayers = onlinePlayerCount();

        if (onlinePlayers >= 80) {
            if (mobSpawning) {
                world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
                Bukkit.broadcastMessage(Main.prefix + "Mob Spawning has been " + ChatColor.RED + "Disabled");
                mobSpawning = false;
            }
        }

        if (!player.hasPlayedBefore()) {
            Bukkit.broadcastMessage(Main.prefix + ChatColor.BOLD + "Welcome " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + player.getName() + ChatColor.YELLOW + ChatColor.BOLD + " to" + ChatColor.GREEN + ChatColor.BOLD + " EarthPol!");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.5f, 1.0f);

            TextComponent guide = new TextComponent("§3Get started by using our §e[§bGuide§e]");
            guide.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aOpens link to the Guide on the web browser.").create()));
            guide.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://earthpol.com/guide"));

            TextComponent map = new TextComponent("§3See where you are in the world using the §e[§bMap§e]");
            map.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aOpens link to the DynMap on your web browser.").create()));
            map.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://earthpol.com/map/"));

            TextComponent rules = new TextComponent("§3Make sure to read the §e[§bRules§e]");
            rules.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aOpens link to the rules on your web browser.").create()));
            rules.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://earthpol.com/rules"));

            player.sendMessage("§e==========[ WELCOME ]==========");
            player.sendMessage(guide);
            player.sendMessage(map);
            player.sendMessage(rules);
            player.sendMessage("§e=============================");

            event.setJoinMessage("");

        }

    }

    @EventHandler
    public void playerLogout(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        int onlinePlayers = onlinePlayerCount();

        if (onlinePlayers <= 80) {
            if (!mobSpawning) {
                world.setGameRule(GameRule.DO_MOB_SPAWNING, true);
                Bukkit.broadcastMessage(Main.prefix + "Mob Spawning has been " + ChatColor.GREEN + "Enabled");
                mobSpawning = true;
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) throws TownyException {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player wasHit = (Player) e.getEntity();
            Player whoHit = (Player) e.getDamager();
            String whoHitName = whoHit.getName();
            String wasHitName = wasHit.getName();
            Resident attacker = TownyUniverse.getInstance().getDataSource().getResident(whoHitName);
            Resident defender = TownyUniverse.getInstance().getDataSource().getResident(wasHitName);
            boolean attackerHasTown = attacker.hasTown();
            boolean defenderHasTown = defender.hasTown();

            if (!attackerHasTown && defenderHasTown) {
                try {
                    Town town = WorldCoord.parseWorldCoord(wasHit.getLocation()).getTownBlock().getTown();
                    TownBlockType plotType = WorldCoord.parseWorldCoord(wasHit.getLocation()).getTownBlock().getType();
                    if (!town.hasOutlaw(whoHit.getName()) && plotType != TownBlockType.ARENA) {
                        whoHit.sendMessage(Main.prefix + "You attempted to hit " + wasHit.getName() + ", but you are not in town.");
                        e.setDamage(0.0D);
                        e.setCancelled(true);
                    }
                } catch (NotRegisteredException error) {
                    whoHit.sendMessage(Main.prefix + "You attempted to hit " + wasHit.getName() + ", but you are not in a town");
                    e.setDamage(0.0D);
                    e.setCancelled(true);
                }
            } else if (attackerHasTown && !defenderHasTown) {
                try {
                    Town town = WorldCoord.parseWorldCoord(wasHit.getLocation()).getTownBlock().getTown();
                    TownBlockType plotType = WorldCoord.parseWorldCoord(wasHit.getLocation()).getTownBlock().getType();
                    if (!town.hasOutlaw(wasHit.getName()) && plotType != TownBlockType.ARENA) {
                        whoHit.sendMessage(Main.prefix + "You attempted to hit " + wasHit.getName() + ", but they are not an outlaw of your town.");
                        e.setDamage(0.0D);
                        e.setCancelled(true);
                    }
                } catch (NotRegisteredException error) {
                    whoHit.sendMessage(Main.prefix + "You attempted to hit " + wasHit.getName() + ", but they are not in town.");
                    e.setDamage(0.0D);
                    e.setCancelled(true);
                }
            }

        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        if (event.getCause() != DamageCause.VOID)
            return;

        Player player = (Player) event.getEntity();

        if (player.getWorld().getEnvironment() == Environment.THE_END)
            return;

        event.setCancelled(true);
        player.teleport(player.getWorld().getHighestBlockAt(player.getLocation()).getLocation().add(0.0, 1.0, 0.0), TeleportCause.UNKNOWN);
    }

    public int onlinePlayerCount() {
        return Main.instance.getServer().getOnlinePlayers().size();
    }
}
