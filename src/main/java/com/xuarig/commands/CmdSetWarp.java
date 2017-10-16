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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSetWarp implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_SETWARP)) {
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "no_perm").replaceAll("%perm%", Permissions.COMMAND_SETWARP.getNode()));
                return true;
            }


            else if(args.length != 1) {
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "usage") + " §7: §e/setwarp <nom>");
            }

            else {

                Main.getInstance().getWarpsConfig().set("warps."+args[0]+".x", p.getLocation().getX());
                Main.getInstance().getWarpsConfig().set("warps."+args[0]+".y", p.getLocation().getY());
                Main.getInstance().getWarpsConfig().set("warps."+args[0]+".z", p.getLocation().getZ());
                Main.getInstance().getWarpsConfig().set("warps."+args[0]+".yaw", p.getLocation().getYaw());
                Main.getInstance().getWarpsConfig().set("warps."+args[0]+".pitch", p.getLocation().getPitch());
                Main.getInstance().getWarpsConfig().set("warps."+args[0]+".world", p.getWorld().getName());
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "warp_created").replaceAll("%name%", args[0]));
                Main.getInstance().saveWarpsConfig();

                CmdWarp.COMMANDS = Main.getInstance().getWarpsConfig().getConfigurationSection("warps").getKeys(false);
            }



        }

        return false;
    }
}
