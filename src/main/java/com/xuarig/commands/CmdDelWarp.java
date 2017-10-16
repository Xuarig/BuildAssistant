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
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdDelWarp implements CommandExecutor, TabCompleter{

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_DELWARP)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage(p, "no_perm").replaceAll("%perm%", Permissions.COMMAND_DELWARP.getNode()));
                return true;
            } else if (args.length != 1) {
                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/delwarp <" + Lang.getMessage(p, "name") + ">");
            } else {

                if(Main.getInstance().getWarpsConfig().get("warps." + args[0]) == null) {
                    p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "warp_not_found").replaceAll("%name%", args[0]));
                    return true;
                }

                Main.getInstance().getWarpsConfig().set("warps." + args[0], null);
                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "warp_deleted").replaceAll("%name%", args[0]));
                Main.getInstance().saveWarpsConfig();

                CmdWarp.COMMANDS = Main.getInstance().getWarpsConfig().getConfigurationSection("warps").getKeys(false);
            }
        }

        return false;

    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player && Perms.hasPermission((Player) sender, Permissions.COMMAND_DELWARP)) {
            if (args.length == 1) {
                ArrayList<String> commands = new ArrayList<String>();

                if (!args[0].equals("")) {

                    for (String commandes : CmdWarp.COMMANDS) {
                        if (commandes.toLowerCase().startsWith(args[0].toLowerCase())) {
                            commands.add(commandes);
                        }
                    }
                } else {
                    commands.addAll(CmdWarp.COMMANDS);
                }
                //Collections.sort(com.xuarig.commands);

                return commands;
            }
        }

        return null;

    }


}
