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

import com.xuarig.Main;
import com.xuarig.utils.Lang;
import com.xuarig.utils.Permissions;
import com.xuarig.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CmdHide implements CommandExecutor{

    private ArrayList<Player> hideList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length == 0 && !Perms.hasPermission((Player) sender, Permissions.COMMAND_HIDE_OWN)) {
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "no_perm").replaceAll("%perm%", Permissions.COMMAND_HIDE_OWN.getNode()));
                return true;
            } else if(args.length >= 1 && !Perms.hasPermission((Player) sender, Permissions.COMMAND_HIDE)) {
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "no_perm").replaceAll("%perm%", Permissions.COMMAND_HIDE.getNode()));
                return true;
            }

            if(args.length > 1) {
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "usage") + " §7: §e/hide [" + Lang.getMessage(p, "player") + "]");
                return true;
            }

            else if(args.length == 0) {
                if(!hideList.contains(p)) {
                    for (Player pl : Bukkit.getOnlinePlayers())
                        pl.hidePlayer(p);
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "hide_enable"));
                    hideList.add(p);
                } else {
                    for(Player pl : Bukkit.getOnlinePlayers())
                        pl.showPlayer(p);
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "hide_disable"));
                    hideList.remove(p);
                }
                return true;
            }

            Player player;

            try {
                player = Bukkit.getPlayer(args[0]);
            } catch (Exception e) {
                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "player_not_found"));
                return true;
            }

            try {
                if (!hideList.contains(player)) {
                    for (Player pl : Bukkit.getOnlinePlayers())
                        pl.hidePlayer(player);
                    player.sendMessage(Main.PREFIX + Lang.getMessage(player, "hide_enable"));
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "hide_enable_other").replaceAll("%player%", player.getName()));
                    hideList.add(player);
                } else {
                    for (Player pl : Bukkit.getOnlinePlayers())
                        pl.showPlayer(player);
                    player.sendMessage(Main.PREFIX + Lang.getMessage(player, "hide_disable"));
                    p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "hide_disable_other").replaceAll("%player%", player.getName()));
                    hideList.remove(player);
                }
            } catch (IllegalArgumentException e) {
                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "player_not_found"));
                return true;
            }



        }
        return false;

    }



}


