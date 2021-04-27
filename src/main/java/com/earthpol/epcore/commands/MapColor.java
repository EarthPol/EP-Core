package com.earthpol.epcore.commands;

import com.earthpol.epcore.Main;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MapColor implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length > 0){
                if(player.hasPermission("group.premium")){
                    String playerName = player.getName();

                    try {
                        Resident issuer = TownyUniverse.getInstance().getResident(playerName);
                        assert issuer != null;
                        if(issuer.hasTown()) {
                            if(issuer.hasNation()) {
                                Nation nation = issuer.getTown().getNation();

                                if(nation.getKing() == issuer){
                                    switch (args[0]) {
                                        case "aqua":
                                        case "cyan":
                                            nation.setMapColorHexCode("00ffff");
                                            player.sendMessage(Main.prefix + "Nation color changed to §bCyan");
                                            break;
                                        case "azure":
                                            nation.setMapColorHexCode("f0ffff");
                                            player.sendMessage(Main.prefix + "Nation color changed to Azure");
                                            break;
                                        case "beige":
                                            nation.setMapColorHexCode("f5f5dc");
                                            player.sendMessage(Main.prefix + "Nation color changed to Biege");
                                            break;
                                        case "black":
                                            nation.setMapColorHexCode("000000");
                                            player.sendMessage(Main.prefix + "Nation color changed to §0Black");
                                            break;
                                        case "blue":
                                            nation.setMapColorHexCode("0000ff");
                                            player.sendMessage(Main.prefix + "Nation color changed to Blue");
                                            break;
                                        case "brown":
                                            nation.setMapColorHexCode("a52a2a");
                                            player.sendMessage(Main.prefix + "Nation color changed to Brown");
                                            break;
                                        case "darkblue":
                                            nation.setMapColorHexCode("00008b");
                                            player.sendMessage(Main.prefix + "Nation color changed to §1Dark Blue");
                                            break;
                                        case "darkcyan":
                                            nation.setMapColorHexCode("008b8b");
                                            player.sendMessage(Main.prefix + "Nation color changed to Dark Cyan");
                                            break;
                                        case "darkgrey":
                                            nation.setMapColorHexCode("a9a9a9");
                                            player.sendMessage(Main.prefix + "Nation color changed to Dark Grey");
                                            break;
                                        case "darkgreen":
                                            nation.setMapColorHexCode("006400");
                                            player.sendMessage(Main.prefix + "Nation color changed to §2Dark Green");
                                            break;
                                        case "darkkhaki":
                                            nation.setMapColorHexCode("bdb76b");
                                            player.sendMessage(Main.prefix + "Nation color changed to Dark Khaki");
                                            break;
                                        case "darkmagenta":
                                            nation.setMapColorHexCode("8b008b");
                                            player.sendMessage(Main.prefix + "Nation color changed to §5Dark Magenta");
                                            break;
                                        case "darkolivegreen":
                                            nation.setMapColorHexCode("556b2f");
                                            player.sendMessage(Main.prefix + "Nation color changed to Dark Olive Green");
                                            break;
                                        case "darkorange":
                                            nation.setMapColorHexCode("ff8c00");
                                            player.sendMessage(Main.prefix + "Nation color changed to Dark Orange");
                                            break;
                                        case "darkorchid":
                                            nation.setMapColorHexCode("9932cc");
                                            player.sendMessage(Main.prefix + "Nation color changed to Dark Orchid");
                                            break;
                                        case "darkred":
                                            nation.setMapColorHexCode("8b0000");
                                            player.sendMessage(Main.prefix + "Nation color changed to §4Dark Red");
                                            break;
                                        case "darksalmon":
                                            nation.setMapColorHexCode("e9967a");
                                            player.sendMessage(Main.prefix + "Nation color changed to Dark Salmon");
                                            break;
                                        case "darkviolet":
                                            nation.setMapColorHexCode("9400d3");
                                            player.sendMessage(Main.prefix + "Nation color changed to Dark Violet");
                                            break;
                                        case "fuchsia":
                                            nation.setMapColorHexCode("ff00ff");
                                            player.sendMessage(Main.prefix + "Nation color changed to Fuchsia");
                                            break;
                                        case "gold":
                                            nation.setMapColorHexCode("ffd700");
                                            player.sendMessage(Main.prefix + "Nation color changed to §6Gold");
                                            break;
                                        case "green":
                                            nation.setMapColorHexCode("008000");
                                            player.sendMessage(Main.prefix + "Nation color changed to §2Green");
                                            break;
                                        case "indigo":
                                            nation.setMapColorHexCode("4b0082");
                                            player.sendMessage(Main.prefix + "Nation color changed to Indigo");
                                            break;
                                        case "khaki":
                                            nation.setMapColorHexCode("f0e68c");
                                            player.sendMessage(Main.prefix + "Nation color changed to Khaki");
                                            break;
                                        case "lightblue":
                                            nation.setMapColorHexCode("add8e6");
                                            player.sendMessage(Main.prefix + "Nation color changed to §9Light Blue");
                                            break;
                                        case "lightcyan":
                                            nation.setMapColorHexCode("e0ffff");
                                            player.sendMessage(Main.prefix + "Nation color changed to §bLight Cyan");
                                            break;
                                        case "lightgreen":
                                            nation.setMapColorHexCode("90ee90");
                                            player.sendMessage(Main.prefix + "Nation color changed to §aLight Green");
                                            break;
                                        case "lightgrey":
                                            nation.setMapColorHexCode("d3d3d3");
                                            player.sendMessage(Main.prefix + "Nation color changed to Light Grey");
                                            break;
                                        case "lightpink":
                                            nation.setMapColorHexCode("ffb6c1");
                                            player.sendMessage(Main.prefix + "Nation color changed to Light Pink");
                                            break;
                                        case "lightyellow":
                                            nation.setMapColorHexCode("ffffe0");
                                            player.sendMessage(Main.prefix + "Nation color changed to §eLight Yellow");
                                            break;
                                        case "lime":
                                            nation.setMapColorHexCode("00ff00");
                                            player.sendMessage(Main.prefix + "Nation color changed to §aLime");
                                            break;
                                        case "magenta":
                                            nation.setMapColorHexCode("ff00ff");
                                            player.sendMessage(Main.prefix + "Nation color changed to §dMagenta");
                                            break;
                                        case "maroon":
                                            nation.setMapColorHexCode("800000");
                                            player.sendMessage(Main.prefix + "Nation color changed to Maroon");
                                            break;
                                        case "navy":
                                            nation.setMapColorHexCode("000080");
                                            player.sendMessage(Main.prefix + "Nation color changed to §9Navy");
                                            break;
                                        case "olive":
                                            nation.setMapColorHexCode("808000");
                                            player.sendMessage(Main.prefix + "Nation color changed to Olive");
                                            break;
                                        case "orange":
                                            nation.setMapColorHexCode("ffa500");
                                            player.sendMessage(Main.prefix + "Nation color changed to Orange");
                                            break;
                                        case "pink":
                                            nation.setMapColorHexCode("ffc0cb");
                                            player.sendMessage(Main.prefix + "Nation color changed to Pink");
                                            break;
                                        case "purple":
                                            nation.setMapColorHexCode("800080");
                                            player.sendMessage(Main.prefix + "Nation color changed to §5Purple");
                                            break;
                                        case "violet":
                                            nation.setMapColorHexCode("800080");
                                            player.sendMessage(Main.prefix + "Nation color changed to §5Violet");
                                            break;
                                        case "red":
                                            nation.setMapColorHexCode("ff0000");
                                            player.sendMessage(Main.prefix + "Nation color changed to §cRed");
                                            break;
                                        case "silver":
                                            nation.setMapColorHexCode("c0c0c0");
                                            player.sendMessage(Main.prefix + "Nation color changed to §7Silver");
                                            break;
                                        case "white":
                                            nation.setMapColorHexCode("ffffff");
                                            player.sendMessage(Main.prefix + "Nation color changed to §fWhite");
                                            break;
                                        case "yellow":
                                            nation.setMapColorHexCode("ffff00");
                                            player.sendMessage(Main.prefix + "Nation color changed to §eYellow");
                                            break;
                                        case "colors":
                                        default:
                                            player.sendMessage("§e==========[COLORS]==========");
                                            player.sendMessage("§3aqua, azure, beige, black, blue, brown, cyan, darkblue,");
                                            player.sendMessage("§3darkcyan, darkgrey, darkgreen, darkkhaki, darkmagenta,");
                                            player.sendMessage("§3darkolivegreen, darkorange, darkorchid, darkred, darksalmon,");
                                            player.sendMessage("§3darkviolet, fuchsia, gold, green, indigo, khaki, lightblue,");
                                            player.sendMessage("§3lightcyan, lightgreen, lightgrey, lightpink, lightyellow,");
                                            player.sendMessage("§3lime, magenta, maroon, navy, olive, orange, pink, purple,");
                                            player.sendMessage("§3violet, red, silver, white, yellow");
                                            player.sendMessage("§e===========================");
                                            break;
                                    }
                                } else {
                                    player.sendMessage(Main.prefix + "§cYou must be the §eKing §cof the nation to change the map color.");
                                }
                            } else {
                                player.sendMessage(Main.prefix + "§cYou are not in a nation.");
                            }

                        } else {
                            player.sendMessage(Main.prefix + "§cYou are not in a town.");
                        }

                    } catch (NotRegisteredException e) {
                        e.printStackTrace();
                        player.sendMessage(Main.prefix + "§cAn issue occured while running that command.");
                    }

                } else {
                    player.sendMessage(Main.prefix + "You must be §dPremium §eto use that command.");
                }
            } else {
                player.sendMessage(Main.prefix + "Incorrect Usage: Please specific a color.");
                player.sendMessage(Main.prefix + "Correct Usage: [/mapcolor <color>]");
            }


        } else {
            Bukkit.getLogger().info("[EP-Core]: You need to be a player to execute this command.");
        }
        return false;
    }
}
