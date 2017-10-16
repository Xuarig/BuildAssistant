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

import com.avaje.ebeaninternal.server.core.BasicTypeConverter;
import com.xuarig.utils.Lang;
import com.xuarig.Main;
import com.xuarig.utils.Permissions;
import com.xuarig.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSpeed implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player)sender; // p = l'envoyeur

            String message = Main.PREFIX + Lang.getMessage(p, "usage") + " §7: §e/speed [" + Lang.getMessage(p, "player") + "] [fly/walk] <" + Lang.getMessage(p, "speed") + " (0-10)/ clear>";

            if(args.length == 0 || args.length > 3) {
                p.sendMessage(message);
            }

            if(args.length == 1) {

                if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_SPEED_OWN)) {
                    sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_SPEED_OWN.getNode()));
                    return true;
                }

                if(((Player) sender).isFlying()) {
                    if(args[0].equalsIgnoreCase("clear")) {
                        p.setFlySpeed(BasicTypeConverter.toFloat(0.2));
                        p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "fly_speed_set").replaceAll("%speed%", "2"));
                        return true;
                    }
                    try {
                        p.setFlySpeed(BasicTypeConverter.toFloat(args[0])/10);
                        p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "fly_speed_set").replaceAll("%speed%", args[0]));
                    } catch (NumberFormatException e) {
                        p.sendMessage(message);
                    }
                } else {
                    if(args[0].equalsIgnoreCase("clear")) {
                        p.setWalkSpeed(BasicTypeConverter.toFloat(0.2));
                        p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "walk_speed_set").replaceAll("%speed%", "2"));
                        return true;
                    }
                    try {
                        p.setWalkSpeed(BasicTypeConverter.toFloat(args[0])/10);
                        p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "walk_speed_set").replaceAll("%speed%", args[0]));
                    } catch (NumberFormatException e) {
                        p.sendMessage(message);
                    }
                }
            } else if (args.length == 2) {

                if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_SPEED_OWN)) {
                    sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_SPEED_OWN.getNode()));
                    return true;
                }

                if(args[0].equalsIgnoreCase("fly")) {
                    if(args[1].equalsIgnoreCase("clear")) {
                        p.setFlySpeed(BasicTypeConverter.toFloat(0.2));
                        p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "fly_speed_set").replaceAll("%speed%", "2"));
                        return true;
                    }
                    try {
                        p.setFlySpeed(BasicTypeConverter.toFloat(args[1])/10);
                        p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "fly_speed_set").replaceAll("%speed%", args[0]));
                    } catch (NumberFormatException e) {
                        p.sendMessage(message);
                    }
                } else if (args[0].equalsIgnoreCase("walk")){
                    if(args[1].equalsIgnoreCase("clear")) {
                        p.setWalkSpeed(BasicTypeConverter.toFloat(0.2));
                        p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "walk_speed_set").replaceAll("%speed%", "2"));
                        return true;
                    }
                    try {
                        p.setWalkSpeed(BasicTypeConverter.toFloat(args[1])/10);
                        p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "walk_speed_set").replaceAll("%speed%", args[0]));
                    } catch (NumberFormatException e) {
                        p.sendMessage(message);
                    }
                } else {
                    if (!sender.hasPermission(Permissions.COMMAND_SPEED.getNode())) {
                        sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm"));
                        return true;
                    }
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                        if (Bukkit.getPlayer(args[0]).isFlying()) {
                            if(args[1].equalsIgnoreCase("clear")) {
                                Bukkit.getPlayer(args[0]).setFlySpeed(BasicTypeConverter.toFloat(0.2));
                                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "fly_speed_set").replaceAll("%speed%", "2"));
                                return true;
                            }
                            try {
                                Bukkit.getPlayer(args[0]).setFlySpeed(BasicTypeConverter.toFloat(args[1])/10);
                                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "fly_speed_set").replaceAll("%speed%", args[1]));

                            } catch (NumberFormatException e) {
                                p.sendMessage(message);
                            }
                        } else {
                            if(args[1].equalsIgnoreCase("clear")) {
                                Bukkit.getPlayer(args[0]).setWalkSpeed(BasicTypeConverter.toFloat(0.2));
                                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "fly_speed_set").replaceAll("%speed%", "2"));
                                return true;
                            }
                            try {
                                Bukkit.getPlayer(args[0]).setWalkSpeed(BasicTypeConverter.toFloat(args[1])/10);
                                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "walk_speed_set").replaceAll("%speed%", args[1]));
                            } catch (NumberFormatException  e) {
                                p.sendMessage(message);
                            }
                        }
                    }

                }
            } else if (args.length == 3) {
                if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_SPEED)) {
                    sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_SPEED.getNode()));
                    return true;
                }
                if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                    if (args[1].equalsIgnoreCase("fly")) {
                        if(args[2].equalsIgnoreCase("clear")) {
                            Bukkit.getPlayer(args[0]).setFlySpeed(BasicTypeConverter.toFloat(0.2));
                            p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "fly_speed_set").replaceAll("%speed%", "2"));
                            return true;
                        }
                        try {
                            Bukkit.getPlayer(args[0]).setFlySpeed(BasicTypeConverter.toFloat(args[2])/10);
                            p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "fly_speed_set").replaceAll("%speed%", args[2]));
                        } catch (NumberFormatException e) {
                            p.sendMessage(message);
                        }
                    } else if (args[1].equalsIgnoreCase("walk")){
                        if(args[2].equalsIgnoreCase("clear")) {
                            Bukkit.getPlayer(args[0]).setWalkSpeed(BasicTypeConverter.toFloat(0.2));
                            p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "walk_speed_set").replaceAll("%speed%", "2"));
                            return true;
                        }
                        try {
                            Bukkit.getPlayer(args[0]).setWalkSpeed(BasicTypeConverter.toFloat(args[2])/10);
                            p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "walk_speed_set").replaceAll("%speed%", args[2]));
                        } catch (NumberFormatException e) {
                            p.sendMessage(message);
                        }
                    }
                }
            }


        }

        return false;
    }
}

