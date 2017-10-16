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
import com.xuarig.events.DamageEvent;
import com.xuarig.utils.Permissions;
import com.xuarig.utils.Perms;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTop implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {

            if(!Perms.hasPermission((Player) sender, Permissions.COMMAND_TOP)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_TOP.getNode()));
                return true;
            }

            boolean isSafe = false;
            Location loc = ((Player) sender).getLocation();
            int i = ((Player) sender).getWorld().getMaxHeight();
            while (!isSafe) {
                loc.setY(i - 1);
                if(!loc.getBlock().isEmpty()) {
                    isSafe = true;
                    loc.setY(i + 1);
                    ((Player) sender).teleport(loc);
                    DamageEvent.NoFall.add((Player) sender);
                    break;
                } else if (i == 0){
                    loc.setY(((Player) sender).getWorld().getMaxHeight());
                    ((Player) sender).teleport(loc);
                    break;
                }
                i--;

            }


        }

        return false;
    }

}
