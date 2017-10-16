/*
 *  Copyright © 2017 XUARIG ALL RIGHT RESERVED
 *
 *  All modifications, distributions and usages (commercials or not) must be allowed by the owner.
 *  All contributions will be copyright © XUARIG unless otherwise specified.
 *
 *  Toutes modifications, distributions et utilisations (commerciales ou non) doivent être approuvés par le propriétaire.
 *  Toutes contributions seront copyright © XUARIG sauf indication contraire.
 *
 *  Updated on 08/09/17 04:41 by xuarig
 *  Mis à jour le 09/08/17 04:41 par xuarig
 */

package com.xuarig;

import com.xuarig.commands.*;
import com.xuarig.events.*;
import com.xuarig.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Monster;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JavaPlugin{

    public static String PREFIX = "§aBuildAssistant §7» ";

    private static Main instance;

    private OptionsManager optionsManager;

    public static Main getInstance() {
        return instance;
    }

    private File warpsf, playersf, frenchf, englishf;
    private FileConfiguration warps, players, french, english;

    public ArrayList<Permissions> disabledPermissions = new ArrayList<>();

    public OptionsManager getOptionManager() {
        return optionsManager;
    }

    public void onEnable(){

        instance = this;
        this.optionsManager = new OptionsManager();

        final ConsoleCommandSender console = Bukkit.getConsoleSender();

        PluginDescriptionFile pdf = this.getDescription();

        createFiles();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        if(!getConfig().getBoolean("prefix")) {
            PREFIX = getConfig().getString("baseColor").replaceAll("&","§");
        } else {
            PREFIX = PREFIX + getConfig().getString("baseColor").replaceAll("&","§");
        }

        console.sendMessage("");
        console.sendMessage("--------------------------------------------------------------------");
        console.sendMessage("");
        console.sendMessage(ChatColor.GREEN+"     +----------------+"+ ChatColor.RESET);
        console.sendMessage(ChatColor.GREEN+"     | "+ChatColor.AQUA+"BuildAssistant "+ChatColor.GREEN+"|"+ ChatColor.RESET);
        console.sendMessage(ChatColor.GREEN+"     +----------------+"+ ChatColor.RESET);
        console.sendMessage("");

        if(getConfig().getBoolean("updater")) {
            SpigotUpdater updater = new SpigotUpdater(this, 45252);
            try {
                if (updater.checkForUpdates())
                    console.sendMessage("     " + Lang.getServerMessage("update_found").replaceAll("%version%", pdf.getVersion().replaceAll("%url%", updater.getResourceURL())) + ChatColor.RESET);
                else
                    console.sendMessage("     " + Lang.getServerMessage("version").replaceAll("%version%", pdf.getVersion()) + ChatColor.RESET);
            } catch (Exception e) {
                console.sendMessage("     " + Lang.getServerMessage("impossible_update_check") + ChatColor.RESET);
                e.printStackTrace();
            }
        } else {
            console.sendMessage(ChatColor.WHITE + "     " + Lang.getServerMessage("version").replaceAll("%version%", pdf.getVersion()) + ChatColor.RESET);
        }

        //TODO Traductions

        console.sendMessage(ChatColor.GREEN+"     Xuarig©"+ ChatColor.RESET);
        console.sendMessage(ChatColor.WHITE+"     " + Lang.getServerMessage("no_reproduction") + ChatColor.RESET);

        console.sendMessage("");

        console.sendMessage("--------------------------------------------------------------------");
        int i = 1;
        for (String str : pdf.getCommands().keySet()) {
            console.sendMessage(PREFIX
                    + Lang.getServerMessage("startup").replaceAll("%cmd%", "/" + str)
                    + ChatColor.GRAY + " ("
                    + ChatColor.YELLOW + i
                    + ChatColor.GRAY + "/"
                    + ChatColor.YELLOW + pdf.getCommands().size()
                    + ChatColor.GRAY + ")"
                    + ChatColor.RESET);
            i++;
        }
        console.sendMessage("--------------------------------------------------------------------");
        console.sendMessage("");

        getCommand("gm").setExecutor(new CmdGm());
        getCommand("fly").setExecutor(new CmdFly());
        getCommand("speed").setExecutor(new CmdSpeed());
        getCommand("penable").setExecutor(new CmdPluginManager());
        getCommand("pdisable").setExecutor(new CmdPluginManager());
        getCommand("preload").setExecutor(new CmdPluginManager());
        getCommand("buildrule").setExecutor(new CmdBuildRule());
        getCommand("itemgive").setExecutor(new CmdItemGive());
        getCommand("msg").setExecutor(new CmdMsg());
        getCommand("reply").setExecutor(new CmdMsg());
        getCommand("tp").setExecutor(new CmdTP());
        getCommand("back").setExecutor(new CmdTP());
        getCommand("top").setExecutor(new CmdTop());
        getCommand("setwarp").setExecutor(new CmdSetWarp());
        getCommand("delwarp").setExecutor(new CmdDelWarp());
        getCommand("warp").setExecutor(new CmdWarp());
        getCommand("hide").setExecutor(new CmdHide());
        getCommand("nightvision").setExecutor(new CmdNightVision());
        getCommand("pickup").setExecutor(new CmdPickup());
        getCommand("lang").setExecutor(new CmdLang());
        getCommand("head").setExecutor(new CmdHead());
        getCommand("buildassistant").setExecutor(new CmdBuildAssistant());
        getCommand("get").setExecutor(new CmdGet());

        initialize();

        Main.getInstance().getOptionManager().reloadOptions();

        if(!Main.getInstance().getOptionManager().isMobSpawn()) {
            for(World world : Bukkit.getWorlds()) {
                for(org.bukkit.entity.Entity entity : world.getEntities()) {
                  if(entity instanceof Monster) {
                      entity.remove();
                  }
                }
                world.setGameRuleValue("doMobSpawning", "false");
            }
        }




    }

    public void save() {
        FileConfiguration config = getConfig();
        config.set("buildProtection", Main.getInstance().getOptionManager().isBuildProtection());
        config.set("mobSpawn", Main.getInstance().getOptionManager().isMobSpawn());
        config.set("iceMelt", Main.getInstance().getOptionManager().isIceMelt());
        config.set("leavesDecay", Main.getInstance().getOptionManager().isLeavesDecay());
        config.set("damages", Main.getInstance().getOptionManager().isDamages());
        config.set("weatherChange", Main.getInstance().getOptionManager().isWeatherChange());
        config.set("itemSpawn", Main.getInstance().getOptionManager().isItemSpawn());
        config.set("doDaylightCycle", Main.getInstance().getOptionManager().isDoDaylightCycle());
        saveConfig();
    }

    private void initialize() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {

            PluginManager pm = getServer().getPluginManager();
            pm.registerEvents(new JoinQuitEvent(), this);
            pm.registerEvents(new BuildEvent(), this);
            pm.registerEvents(new UtilsEvent(), this);
            pm.registerEvents(new DamageEvent(), this);
            pm.registerEvents(new TeleportationCore(), this);
            pm.registerEvents(new InteractEvent(), this);

            for(World world : Bukkit.getWorlds()) {
                if(!getConfig().getBoolean("doDaylightCycle")) {
                    world.setGameRuleValue("doDaylightCycle", "false");
                } else {
                    world.setGameRuleValue("doDaylightCycle", "true");
                }
                world.setStorm(false);
            }
        }, 5);
    }

    public void createFiles() {

        playersf = new File(getDataFolder(), "players.yml");
        warpsf = new File(getDataFolder(), "warps.yml");
        englishf = new File(getDataFolder(), "languages/english.yml");
        frenchf = new File(getDataFolder(), "languages/french.yml");

        if (!warpsf.exists()) {
            warpsf.getParentFile().mkdirs();
            saveResource("warps.yml", false);
        }

        if (!playersf.exists()) {
            playersf.getParentFile().mkdirs();
            saveResource("players.yml", false);
        }

        if (!englishf.exists()) {
            englishf.getParentFile().mkdirs();
            saveResource("languages/english.yml", false);
        }

        if (!frenchf.exists()) {
            frenchf.getParentFile().mkdirs();
            saveResource("languages/french.yml", false);

        }

        players = new YamlConfiguration();
        warps = new YamlConfiguration();
        french = new Utf8YamlConfiguration();
        english = new Utf8YamlConfiguration();

        try {
            french.load(frenchf);
            english.load(englishf);
            players.load(playersf);
            warps.load(warpsf);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    public FileConfiguration getPlayersConfig() {
        return this.players;
    }
    public FileConfiguration getWarpsConfig() {
        return this.warps;
    }

    public void savePlayersConfig() {
        try {
            getPlayersConfig().save(playersf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveWarpsConfig() {
        try {
            getPlayersConfig().save(warpsf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
