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
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CmdWarp implements CommandExecutor, TabCompleter {

    static Set<String> COMMANDS = Main.getInstance().getWarpsConfig().getConfigurationSection("warps").getKeys(false);

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;

            if(!Perms.hasPermission((Player) sender, Permissions.COMMAND_WARP)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_WARP.getNode()));
                return true;
            }


            else if(args.length != 1) {
                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/warp <" + Lang.getMessage(p, "name") + ">");
            }

            else {

                World w;
                try {
                    w = Bukkit.getWorld(Main.getInstance().getWarpsConfig().getString("warps." + args[0] + ".world"));
                } catch (IllegalArgumentException e) {
                    p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "warp_not_found").replaceAll("%name%", args[0]));
                    return true;
                }
                double x = Main.getInstance().getWarpsConfig().getDouble("warps." + args[0] + ".x");
                double y = Main.getInstance().getWarpsConfig().getDouble("warps." + args[0] + ".y");
                double z = Main.getInstance().getWarpsConfig().getDouble("warps." + args[0] + ".z");
                float yaw = Main.getInstance().getWarpsConfig().getInt("warps." + args[0] + ".yaw");
                float pitch = Main.getInstance().getWarpsConfig().getInt("warps." + args[0] + ".pitch");



                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "warp_tp").replaceAll("%name%", args[0]));
                CmdTP.back.put(p, p.getLocation());
                p.teleport(new Location(w, x, y, z, yaw, pitch));
            }




        }

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
                        commands.addAll(COMMANDS);
                    }
                    //Collections.sort(com.xuarig.commands);

                    return commands;
                }
        }

        return null;

    }


}
