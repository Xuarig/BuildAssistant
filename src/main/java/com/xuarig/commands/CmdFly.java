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
import com.xuarig.utils.Permissions;
import com.xuarig.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdFly implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player)sender; // p = l'envoyeur

            if (args.length == 0 && !Perms.hasPermission((Player) sender, Permissions.COMMAND_FLY_OWN)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_FLY_OWN.getNode()));
                return true;
            } else if(args.length >= 1 && !Perms.hasPermission((Player) sender, Permissions.COMMAND_FLY)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_FLY.getNode()));
                return true;
            }

            if (args.length == 0) {

                if (p.getAllowFlight()) {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "fly_disable"));
                } else {
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "fly_enable"));
                }

            }

            else if (args.length == 1) {
                try {
                    if (Bukkit.getPlayer(args[0]).getAllowFlight()) {
                        Bukkit.getPlayer(args[0]).setAllowFlight(false);
                        Bukkit.getPlayer(args[0]).setFlying(false);
                        if(sender != Bukkit.getPlayer(args[0])) {
                            Bukkit.getPlayer(args[0]).sendMessage(Main.PREFIX + Lang.getMessage(p, "fly_disable"));
                            sender.sendMessage(Main.PREFIX + Lang.getMessage(p, "fly_disable_other").replaceAll("%player%",args[0]));
                        } else {
                            sender.sendMessage(Main.PREFIX + Lang.getMessage(p, "fly_disable"));
                        }


                    } else {
                        Bukkit.getPlayer(args[0]).setAllowFlight(true);
                        Bukkit.getPlayer(args[0]).setFlying(true);
                        if(sender != Bukkit.getPlayer(args[0])) {
                            Bukkit.getPlayer(args[0]).sendMessage(Main.PREFIX + Lang.getMessage(p, "fly_enable"));
                            sender.sendMessage(Main.PREFIX + Lang.getMessage(p, "fly_enable_other").replaceAll("%player%",args[0]));
                        } else {
                            sender.sendMessage(Main.PREFIX + Lang.getMessage(p, "fly_enable"));
                        }
                    }

                } catch (NullPointerException e) {
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "player_not_found"));

                }

            } else {
                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/fly [" + Lang.getMessage(p, "player") + "]");
            }

        }




        return false;
    }
}
