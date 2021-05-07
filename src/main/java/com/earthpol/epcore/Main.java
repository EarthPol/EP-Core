package com.earthpol.epcore;

import com.earthpol.epcore.chat.ChatHandler;
import com.earthpol.epcore.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static Main instance;
    public static Logger log = Bukkit.getLogger();
    public static String prefix = ChatColor.GOLD + "[" + ChatColor.AQUA + "EPMC" + ChatColor.GOLD + "]: " + ChatColor.RESET + ChatColor.YELLOW;

    public static ChatHandler chatHandler;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        log.info("§e======= §bEPMC CORE §e=======");
        log.info("§e= §bEarthPol Core Plugin");
        log.info("§e= §bVersion: §3" + this.getDescription().getVersion());
        log.info("§e= §bAuthors: §3" + this.getDescription().getAuthors());
        log.info("§e= §bWebsite: §3" + this.getDescription().getWebsite());
        log.info("§e= §bSupport: §3https://discord.gg/DvtZzztAfF");
        log.info("§e=========================");
        log.info("§e= §bRegistering EventListener");
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        log.info("§e= §aRegistered EventListener");
        log.info("§e=========================");
        log.info("§e= §bInitializing ChatHandler");
        chatHandler = new ChatHandler();
        log.info("§e= §aInitialized ChatHandler");
        log.info("§e=========================");
        log.info("§e= §bRegistering Commands");
        Objects.requireNonNull(getCommand("map")).setExecutor(new Map());
        Objects.requireNonNull(getCommand("help")).setExecutor(new Help());
        Objects.requireNonNull(getCommand("rules")).setExecutor(new Rules());
        Objects.requireNonNull(getCommand("store")).setExecutor(new Store());
        Objects.requireNonNull(getCommand("support")).setExecutor(new Support());
        Objects.requireNonNull(getCommand("mapcolor")).setExecutor(new MapColor());
        log.info("§e= §aRegistered Commands");
        log.info("§e=========================");
        log.info("§e= §bLoading Custom Recipes");
        recipes();
        log.info("§e= §aLoaded Custom Recipes");
        log.info("§e=========================");
        log.info("§e= §aStartup completed.");
        log.info("§e=========================");
    }

    @SuppressWarnings("deprecation")
    public void recipes() {

        // Custom Bell Recipe
        final ShapedRecipe bell = new ShapedRecipe(new NamespacedKey(this, "custom_bell"), new ItemStack(Material.BELL, 1));
        bell.shape("SSS", " G ", "GNG");
        bell.setIngredient('S', Material.STICK);
        bell.setIngredient('G', Material.GOLD_INGOT);
        bell.setIngredient('N', Material.GOLD_NUGGET);
        Bukkit.addRecipe(bell);

        // Custom Web Recipe
        final ShapedRecipe web = new ShapedRecipe(new NamespacedKey(this, "custom_web"), new ItemStack(Material.COBWEB, 5));
        web.shape("S S", " S ", "S S");
        web.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(web);

        //Custom Quartz Item Recipe
        final ShapelessRecipe quartz = new ShapelessRecipe(new NamespacedKey(this, "custom_nether_quartz"), new ItemStack(Material.QUARTZ, 4));
        quartz.addIngredient(1, Material.QUARTZ_BLOCK);
        Bukkit.addRecipe(quartz);

        //Custom Packed Ice
        final ShapedRecipe packedIce = new ShapedRecipe(new NamespacedKey(this, "custom_packedice"), new ItemStack(Material.PACKED_ICE, 4));
        packedIce.shape("III", "III", "III");
        packedIce.setIngredient('I', Material.ICE);
        Bukkit.addRecipe(packedIce);

        //Custom Blue Ice
        final ShapedRecipe blueice = new ShapedRecipe(new NamespacedKey(this, "custom_blueice"), new ItemStack(Material.BLUE_ICE, 4));
        blueice.shape("III", "III", "III");
        blueice.setIngredient('I', Material.PACKED_ICE);
        Bukkit.addRecipe(blueice);

        //Custom Slimeball
        final ShapelessRecipe slimeball = new ShapelessRecipe(new NamespacedKey(this, "custom_slimeball"), new ItemStack(Material.SLIME_BALL, 1));
        slimeball.addIngredient(1, Material.GREEN_DYE);
        slimeball.addIngredient(1, Material.SNOWBALL);
        Bukkit.addRecipe(slimeball);

        //Custom Prismarine Shard
        final ShapelessRecipe prismarineshard = new ShapelessRecipe(new NamespacedKey(this, "custom_prismarineshard"), new ItemStack(Material.PRISMARINE_SHARD, 3));
        prismarineshard.addIngredient(1, Material.SAND);
        prismarineshard.addIngredient(1, Material.DIRT);
        prismarineshard.addIngredient(1, Material.LAPIS_LAZULI);
        Bukkit.addRecipe(prismarineshard);

        //Custom Prismarine Crystals
        final ShapelessRecipe prismarinecrystals = new ShapelessRecipe(new NamespacedKey(this, "custom_prismarinecrystals"), new ItemStack(Material.PRISMARINE_CRYSTALS, 3));
        prismarinecrystals.addIngredient(1, Material.PRISMARINE_SHARD);
        prismarinecrystals.addIngredient(1, Material.GLOWSTONE_DUST);
        Bukkit.addRecipe(prismarinecrystals);

        //Custom Lily Pad - Acacia Leaves
        final ShapedRecipe lilypad = new ShapedRecipe(new NamespacedKey(this, "custom_lilypad1"), new ItemStack(Material.LILY_PAD, 3));
        lilypad.shape("   ", "   ", "LLL");
        lilypad.setIngredient('L', Material.ACACIA_LEAVES);
        Bukkit.addRecipe(lilypad);

        //Custom Lily Pad - Birch Leaves
        final ShapedRecipe lilypad2 = new ShapedRecipe(new NamespacedKey(this, "custom_lilypad2"), new ItemStack(Material.LILY_PAD, 3));
        lilypad2.shape("   ", "   ", "LLL");
        lilypad2.setIngredient('L', Material.BIRCH_LEAVES);
        Bukkit.addRecipe(lilypad2);

        //Custom Lily Pad - Dark Oak Leaves
        final ShapedRecipe lilypad3 = new ShapedRecipe(new NamespacedKey(this, "custom_lilypad3"), new ItemStack(Material.LILY_PAD, 3));
        lilypad3.shape("   ", "   ", "LLL");
        lilypad3.setIngredient('L', Material.DARK_OAK_LEAVES);
        Bukkit.addRecipe(lilypad3);

        //Custom Lily Pad - Jungle Leaves
        final ShapedRecipe lilypad4 = new ShapedRecipe(new NamespacedKey(this, "custom_lilypad4"), new ItemStack(Material.LILY_PAD, 3));
        lilypad4.shape("   ", "   ", "LLL");
        lilypad4.setIngredient('L', Material.JUNGLE_LEAVES);
        Bukkit.addRecipe(lilypad4);

        //Custom Lily Pad - Oak Leaves
        final ShapedRecipe lilypad5 = new ShapedRecipe(new NamespacedKey(this, "custom_lilypad5"), new ItemStack(Material.LILY_PAD, 3));
        lilypad5.shape("   ", "   ", "LLL");
        lilypad5.setIngredient('L', Material.OAK_LEAVES);
        Bukkit.addRecipe(lilypad5);

        //Custom Lily Pad - Spruce Leaves
        final ShapedRecipe lilypad6 = new ShapedRecipe(new NamespacedKey(this, "custom_lilypad6"), new ItemStack(Material.LILY_PAD, 3));
        lilypad6.shape("   ", "   ", "LLL");
        lilypad6.setIngredient('L', Material.SPRUCE_LEAVES);
        Bukkit.addRecipe(lilypad6);

        //Custom Glowstone Dust
        final ShapedRecipe glowstoneDust = new ShapedRecipe(new NamespacedKey(this, "custom_glowstoneDust"), new ItemStack(Material.GLOWSTONE_DUST, 8));
        glowstoneDust.shape("RRR", "RBR", "RRR");
        glowstoneDust.setIngredient('R', Material.REDSTONE);
        glowstoneDust.setIngredient('B', Material.BLAZE_POWDER);
        Bukkit.addRecipe(glowstoneDust);

        //Custom Heart of Sea
        final ShapedRecipe HeartOfSea = new ShapedRecipe(new NamespacedKey(this, "custom_HeartOfSea"), new ItemStack(Material.HEART_OF_THE_SEA, 1));
        HeartOfSea.shape("DPQ", "PSP", "QPD");
        HeartOfSea.setIngredient('D', Material.DIAMOND_BLOCK);
        HeartOfSea.setIngredient('Q', Material.QUARTZ_BLOCK);
        HeartOfSea.setIngredient('P', Material.PRISMARINE);
        HeartOfSea.setIngredient('S', Material.SLIME_BALL);
        Bukkit.addRecipe(HeartOfSea);

        //Custom Nether Wart
        final ShapedRecipe netherWart = new ShapedRecipe(new NamespacedKey(this, "custom_netherWart"), new ItemStack(Material.NETHER_WART, 8));
        netherWart.shape("RRR", "RBR", "RRR");
        netherWart.setIngredient('R', Material.REDSTONE_BLOCK);
        netherWart.setIngredient('B', Material.BLAZE_POWDER);
        Bukkit.addRecipe(netherWart);

        //Custom String
        final ShapelessRecipe string = new ShapelessRecipe(new NamespacedKey(this, "custom_string"), new ItemStack(Material.STRING, 4));
        string.addIngredient(1, Material.WHITE_WOOL);
        Bukkit.addRecipe(string);

        //Custom Soulsand
        final ShapedRecipe soulsand = new ShapedRecipe(new NamespacedKey(this, "custom_soulsand"), new ItemStack(Material.SOUL_SAND, 12));
        soulsand.shape("RBR", "BPB", "RBR");
        soulsand.setIngredient('R', Material.ROTTEN_FLESH);
        soulsand.setIngredient('B', Material.BONE);
        soulsand.setIngredient('P', Material.BLAZE_POWDER);
        Bukkit.addRecipe(soulsand);

        //Custom Black Dye
        final ShapelessRecipe blackdye1 = new ShapelessRecipe(new NamespacedKey(this, "custom_blackdye_1"), new ItemStack(Material.INK_SAC, 1));
        blackdye1.addIngredient(1, Material.COAL);
        Bukkit.addRecipe(blackdye1);

        //Custom Black Dye #2
        final ShapelessRecipe blackdye2 = new ShapelessRecipe(new NamespacedKey(this, "custom_blackdye_2"), new ItemStack(Material.INK_SAC, 1));
        blackdye2.addIngredient(1, Material.CHARCOAL);
        Bukkit.addRecipe(blackdye2);

        //Custom Sponge
        final ShapedRecipe sponge = new ShapedRecipe(new NamespacedKey(this, "custom_sponge"), new ItemStack(Material.WET_SPONGE, 8));
        sponge.shape("WWW", "WBW", "WWW");
        sponge.setIngredient('W', Material.YELLOW_WOOL);
        sponge.setIngredient('B', Material.WATER_BUCKET);
        Bukkit.addRecipe(sponge);

        //Custom Blackstone
        final ShapedRecipe blackstone = new ShapedRecipe(new NamespacedKey(this, "custom_blackstone"), new ItemStack(Material.BLACKSTONE, 8));
        blackstone.shape("SSS", "SDS", "SSS");
        blackstone.setIngredient('S', Material.STONE);
        blackstone.setIngredient('D', Material.BLACK_DYE);
        Bukkit.addRecipe(blackstone);


        //Custom Chiseled Polished Blackstone
        final ShapedRecipe chiseledBlackstone = new ShapedRecipe(new NamespacedKey(this, "custom_chiseled_blackstone"), new ItemStack(Material.CHISELED_POLISHED_BLACKSTONE, 4));
        chiseledBlackstone.shape(" BB", " BB", "   ");
        chiseledBlackstone.setIngredient('B', Material.POLISHED_BLACKSTONE_BRICKS);
        Bukkit.addRecipe(chiseledBlackstone);

        //Netherrack
        final ShapedRecipe netherrack = new ShapedRecipe(new NamespacedKey(this, "custom_netherrack"), new ItemStack(Material.NETHERRACK, 8));
        netherrack.shape("SSS", "SDS", "SSS");
        netherrack.setIngredient('S', Material.STONE);
        netherrack.setIngredient('D', Material.RED_DYE);
        Bukkit.addRecipe(netherrack);

        //Crimson Nylium
        final ShapelessRecipe crimsonNylium = new ShapelessRecipe(new NamespacedKey(this, "custom_crimson_nylium"), new ItemStack(Material.CRIMSON_NYLIUM, 1));
        crimsonNylium.addIngredient(1, Material.MYCELIUM);
        crimsonNylium.addIngredient(1, Material.NETHER_WART);
        crimsonNylium.addIngredient(1, Material.NETHERRACK);
        Bukkit.addRecipe(crimsonNylium);

        //Warped Nylium
        final ShapelessRecipe warpedNylium = new ShapelessRecipe(new NamespacedKey(this, "custom_warped_nylium"), new ItemStack(Material.WARPED_NYLIUM, 1));
        warpedNylium.addIngredient(1, Material.MYCELIUM);
        warpedNylium.addIngredient(1, Material.NETHER_WART);
        warpedNylium.addIngredient(1, Material.SOUL_SAND);
        Bukkit.addRecipe(warpedNylium);

        final ShapedRecipe gunPowder = new ShapedRecipe(new NamespacedKey(this, "custom_gunpowder"), new ItemStack(Material.GUNPOWDER, 4));
        gunPowder.shape("GGG", "GCG", "GGG");
        gunPowder.setIngredient('G', Material.GLOWSTONE_DUST);
        gunPowder.setIngredient('C', Material.COAL);
        Bukkit.addRecipe(gunPowder);

        final ShapedRecipe endRod = new ShapedRecipe(new NamespacedKey(this, "custom_end_rod"), new ItemStack(Material.END_ROD, 1));
        endRod.shape(" Q ", " T ", " Q ");
        endRod.setIngredient('Q', Material.QUARTZ);
        endRod.setIngredient('T', Material.TORCH);
        Bukkit.addRecipe(endRod);


        /*=================
         * CARPET COLORS
         ==================*/

        // BLACK CARPET
        final ShapelessRecipe blackCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_black_carpet"), new ItemStack(Material.BLACK_CARPET, 3));
        blackCarpet.addIngredient(1, Material.BLACK_CARPET);
        Bukkit.addRecipe(blackCarpet);

        //Blue Carpet
        final ShapelessRecipe blueCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_blue_carpet"), new ItemStack(Material.BLUE_CARPET, 3));
        blueCarpet.addIngredient(1, Material.BLUE_CARPET);
        Bukkit.addRecipe(blueCarpet);

        //Brown Carpet
        final ShapelessRecipe brownCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_brown_carpet"), new ItemStack(Material.BROWN_CARPET, 3));
        brownCarpet.addIngredient(1, Material.BROWN_CARPET);
        Bukkit.addRecipe(brownCarpet);

        //Cyan Carpet
        final ShapelessRecipe cyanCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_cyan_carpet"), new ItemStack(Material.CYAN_CARPET, 3));
        cyanCarpet.addIngredient(1, Material.CYAN_CARPET);
        Bukkit.addRecipe(cyanCarpet);

        //Gray carpet
        final ShapelessRecipe grayCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_gray_carpet"), new ItemStack(Material.GRAY_CARPET, 3));
        grayCarpet.addIngredient(1, Material.GRAY_CARPET);
        Bukkit.addRecipe(grayCarpet);

        //Green Carpet
        final ShapelessRecipe greenCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_green_carpet"), new ItemStack(Material.GREEN_CARPET, 3));
        greenCarpet.addIngredient(1, Material.GREEN_CARPET);
        Bukkit.addRecipe(greenCarpet);

        //lightblue carpet
        final ShapelessRecipe lightBlueCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_light_blue_carpet"), new ItemStack(Material.LIGHT_BLUE_CARPET, 3));
        lightBlueCarpet.addIngredient(1, Material.LIGHT_BLUE_CARPET);
        Bukkit.addRecipe(lightBlueCarpet);

        //LIGHT GRAY carpet
        final ShapelessRecipe lightGrayCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_light_gray_carpet"), new ItemStack(Material.LIGHT_GRAY_CARPET, 3));
        lightGrayCarpet.addIngredient(1, Material.LIGHT_GRAY_CARPET);
        Bukkit.addRecipe(lightGrayCarpet);

        //LIME carpet
        final ShapelessRecipe limeCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_lime_carpet"), new ItemStack(Material.LIME_CARPET, 3));
        limeCarpet.addIngredient(1, Material.LIME_CARPET);
        Bukkit.addRecipe(limeCarpet);

        //Magenta Carpet
        final ShapelessRecipe magentaCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_magenta_carpet"), new ItemStack(Material.MAGENTA_CARPET, 3));
        magentaCarpet.addIngredient(1, Material.MAGENTA_CARPET);
        Bukkit.addRecipe(magentaCarpet);

        //Pink Carpet
        final ShapelessRecipe pinkCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_pink_carpet"), new ItemStack(Material.PINK_CARPET, 3));
        pinkCarpet.addIngredient(1, Material.PINK_CARPET);
        Bukkit.addRecipe(pinkCarpet);

        //Purple Carpet
        final ShapelessRecipe purpleCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_purple_carpet"), new ItemStack(Material.PURPLE_CARPET, 3));
        purpleCarpet.addIngredient(1, Material.PURPLE_CARPET);
        Bukkit.addRecipe(purpleCarpet);

        //Red Carpet
        final ShapelessRecipe redCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_red_carpet"), new ItemStack(Material.RED_CARPET, 3));
        redCarpet.addIngredient(1, Material.RED_CARPET);
        Bukkit.addRecipe(redCarpet);

        //White Carpet
        final ShapelessRecipe whiteCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_white_carpet"), new ItemStack(Material.WHITE_CARPET, 3));
        whiteCarpet.addIngredient(1, Material.WHITE_CARPET);
        Bukkit.addRecipe(whiteCarpet);

        //Yellow Carpet
        final ShapelessRecipe yellowCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_yellow_carpet"), new ItemStack(Material.YELLOW_CARPET, 3));
        yellowCarpet.addIngredient(1, Material.YELLOW_CARPET);
        Bukkit.addRecipe(yellowCarpet);

        //Orange Carpet
        final ShapelessRecipe orangeCarpet = new ShapelessRecipe(new NamespacedKey(this, "custom_orange_carpet"), new ItemStack(Material.ORANGE_CARPET, 3));
        orangeCarpet.addIngredient(1, Material.ORANGE_CARPET);
        Bukkit.addRecipe(orangeCarpet);

        //Custom Blaze Rods
        this.getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.BLAZE_ROD, 1), Material.IRON_BARS));

        this.getServer().addRecipe(new BlastingRecipe(new NamespacedKey(this, "custom_blazerods"), new ItemStack(Material.BLAZE_ROD, 1), Material.IRON_BARS, 0, 60));

        //Custom Green Dye
        this.getServer().addRecipe(new SmokingRecipe(new NamespacedKey(this, "custom_green_dye"), new ItemStack(Material.GREEN_DYE, 1), Material.OAK_LEAVES, 0, 60));

        //Custom Green Dye #1
        this.getServer().addRecipe(new SmokingRecipe(new NamespacedKey(this, "custom_green_dye_1"), new ItemStack(Material.GREEN_DYE, 1), Material.ACACIA_LEAVES, 0, 60));

        //Custom Green Dye #2
        this.getServer().addRecipe(new SmokingRecipe(new NamespacedKey(this, "custom_green_dye_2"), new ItemStack(Material.GREEN_DYE, 1), Material.SPRUCE_LEAVES, 0, 60));

        //Custom Green Dye #3
        this.getServer().addRecipe(new SmokingRecipe(new NamespacedKey(this, "custom_green_dye_3"), new ItemStack(Material.GREEN_DYE, 1), Material.BIRCH_LEAVES, 0, 60));

        //Custom Green Dye #4
        this.getServer().addRecipe(new SmokingRecipe(new NamespacedKey(this, "custom_green_dye_4"), new ItemStack(Material.GREEN_DYE, 1), Material.JUNGLE_LEAVES, 0, 60));

        //Custom Green Dye #5
        this.getServer().addRecipe(new SmokingRecipe(new NamespacedKey(this, "custom_green_dye_5"), new ItemStack(Material.GREEN_DYE, 1), Material.DARK_OAK_LEAVES, 0, 60));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log.info("§e======= §bEPMC CORE §e=======");
        log.info("§e= §bEarthPol Core Plugin");
        log.info("§e= §bDisabling plugin...");
        log.info("§e= §bThank you for using EP-Core");
        log.info("§e= §bSupport: §3https://discord.gg/DvtZzztAfF");
        log.info("§e=========================");
    }
}
