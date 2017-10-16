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

public class CmdItemGive implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player) {

            if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_ITEMGIVE)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_ITEMGIVE.getNode()));
                return true;
            }

            Player p = (Player) sender;

            if (args.length == 1) {
                try{
                    Player pl = Bukkit.getPlayer(args[0]);
                    if(p.getItemInHand() == null)
                        return true;
                    pl.getInventory().addItem(p.getItemInHand());
                    pl.sendMessage(Main.PREFIX + Lang.getMessage(pl, "item_receive").replaceAll("%player%",  p.getName()));
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "item_give").replaceAll("%player%",  pl.getName()));
                } catch (Exception exp) {
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "player_not_found"));
                }
            } else {
                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/giveItem <" + Lang.getMessage(p, "player") + ">");
            }


        }

        return false;

        }


    }

