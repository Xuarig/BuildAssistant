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

package com.xuarig.commands;

import com.xuarig.utils.Lang;
import com.xuarig.Main;
import com.xuarig.events.BuildEvent;
import com.xuarig.events.UtilsEvent;
import com.xuarig.utils.Permissions;
import com.xuarig.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdBuildRule implements CommandExecutor, TabCompleter{

    private final String[] COMMANDS = {"buildProtection", "leavesDecay", "iceMelt", "mobSpawn", "damages", "weatherChange", "itemSpawn", "doDaylightCycle"};

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {

            if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_BUILDRULE)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_BUILDRULE.getNode()));
                return true;
            }

            else if(args.length == 0 || args.length > 1) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/buildrule <" + Lang.getMessage((Player) sender, "argument") + ">");
            }

            else if(args[0].equalsIgnoreCase("buildprotection")) {
                Main.getInstance().getOptionManager().setBuildProtection(!Main.getInstance().getOptionManager().isBuildProtection());

                Main.getInstance().getConfig().set("buildprotection", Main.getInstance().getOptionManager().isBuildProtection());

                if (Main.getInstance().getOptionManager().isBuildProtection()) {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "buildprotection_enable"));
                } else {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "buildprotection_disable"));
                }

            } else if (args[0].equalsIgnoreCase("leavesdecay")) {
                Main.getInstance().getOptionManager().setLeavesDecay(!Main.getInstance().getOptionManager().isLeavesDecay());

                Main.getInstance().getConfig().set("leavesdecay", Main.getInstance().getOptionManager().isLeavesDecay());

                if (Main.getInstance().getOptionManager().isLeavesDecay()) {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "leavesdecay_enable"));
                } else {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "leavesdecay_disable"));
                }

            } else if (args[0].equalsIgnoreCase("icemelt")) {
                Main.getInstance().getOptionManager().setIceMelt(!Main.getInstance().getOptionManager().isIceMelt());

                Main.getInstance().getConfig().set("icemelt", Main.getInstance().getOptionManager().isIceMelt());

                if (Main.getInstance().getOptionManager().isIceMelt()) {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "icemelt_enable"));
                } else {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "icemelt_disable"));
                }

            } else if (args[0].equalsIgnoreCase("mobspawn")) {
                Main.getInstance().getOptionManager().setMobSpawn(!Main.getInstance().getOptionManager().isMobSpawn());

                Main.getInstance().getConfig().set("mobspawn", Main.getInstance().getOptionManager().isMobSpawn());

                if (Main.getInstance().getOptionManager().isMobSpawn()) {
                    for(World world : Bukkit.getWorlds()) {
                        world.setGameRuleValue("doMobSpawning", "true");
                    }
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "mobspawn_enable"));
                } else {
                    for(World world : Bukkit.getWorlds()) {
                        world.setGameRuleValue("doMobSpawning", "false");
                        for(Entity entity : world.getEntities()) {
                            if(entity instanceof Monster)
                                entity.remove();
                        }
                    }
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "mobspawn_disable"));
                }

            } else if (args[0].equalsIgnoreCase("damages")) {
                Main.getInstance().getOptionManager().setDamages(!Main.getInstance().getOptionManager().isDamages());

                Main.getInstance().getConfig().set("damages", Main.getInstance().getOptionManager().isDamages());

                if (Main.getInstance().getOptionManager().isDamages()) {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "damages_enable"));
                } else {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "damages_disable"));
                }

            } else if (args[0].equalsIgnoreCase("weatherchange")) {
                Main.getInstance().getOptionManager().setWeatherChange(!Main.getInstance().getOptionManager().isWeatherChange());

                Main.getInstance().getConfig().set("weatherChange", Main.getInstance().getOptionManager().isWeatherChange());

                if (Main.getInstance().getOptionManager().isWeatherChange()) {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "weatherchange_enable"));
                } else {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "weatherchange_disable"));
                }

            } else if (args[0].equalsIgnoreCase("itemSpawn")) {
                Main.getInstance().getOptionManager().setItemSpawn(!Main.getInstance().getOptionManager().isItemSpawn());

                Main.getInstance().getConfig().set("damages", Main.getInstance().getOptionManager().isItemSpawn());

                if (Main.getInstance().getOptionManager().isItemSpawn()) {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "itemspawn_enable"));
                } else {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "itemspawn_disable"));
                }

            } else if (args[0].equalsIgnoreCase("doDaylightCycle")) {
                Main.getInstance().getOptionManager().setDoDaylightCycle(!Main.getInstance().getOptionManager().isDoDaylightCycle());

                Main.getInstance().getConfig().set("damages", Main.getInstance().getOptionManager().isDoDaylightCycle());

                if (Main.getInstance().getOptionManager().isDoDaylightCycle()) {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "daylightcycle_enable"));
                } else {
                    for(Player player : Bukkit.getOnlinePlayers())
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "daylightcycle_disable"));
                }

            } else {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/buildrule <" + Lang.getMessage((Player) sender, "argument") + ">");
            }

        }

        Main.getInstance().save();
        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player && sender.isOp()) {
                if (args.length == 1) {
                    ArrayList<String> commands = new ArrayList<String>();

                    if (!args[0].equals("")) {

                        for (String commandes : COMMANDS) {
                            if (commandes.toLowerCase().startsWith(args[0].toLowerCase())) {
                                commands.add(commandes);
                            }
                        }
                    } else {
                        for (String commandes : COMMANDS) {
                            commands.add(commandes);
                        }
                    }
                    //Collections.sort(com.xuarig.commands);

                    return commands;
                }
            }

        return null;

    }

}
