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
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CmdMsg implements CommandExecutor{

    private HashMap<Player, Player> replys = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player && (cmd.getName().equalsIgnoreCase("m") || cmd.getName().equalsIgnoreCase("msg") || cmd.getName().equalsIgnoreCase("t") || cmd.getName().equalsIgnoreCase("tell"))) {

            Player p = (Player) sender; // p = l'envoyeur

            if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_MSG)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_MSG.getNode()));
                return true;
            }

            if (args.length <= 1) {
                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/msg <" + Lang.getMessage(p, "player") + "> <" + Lang.getMessage(p, "message") + ">");
            } else {


                StringBuilder Broadcast = new StringBuilder("");
                int i =0;
                for (String arg : args) {
                    if(i >= 1) {
                        Broadcast.append(" ").append(arg);
                    }
                    i++;

                }

                try {
                    Bukkit.getPlayer(args[0]).sendMessage("§7[§6" + Lang.getMessage(Bukkit.getPlayer(args[0]), "private_message") + "§7] §e" + p.getName() + " §7▶§e " + Bukkit.getPlayer(args[0]).getName() + ChatColor.WHITE + " » " + Broadcast.toString().replace('&', '§'));
                    p.sendMessage("§7[§6" + Lang.getMessage(p, "private_message") + "§7] §e" + p.getName() + " §7▶§e " + Bukkit.getPlayer(args[0]).getName() + ChatColor.WHITE + " » " + Broadcast.toString().replace('&', '§'));
                    replys.put(Bukkit.getPlayer(args[0]), p);
                    replys.put(p, Bukkit.getPlayer(args[0]));

                } catch (Exception exp) {
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "player_not_found"));
                }

            }
        } else if(sender instanceof Player && (cmd.getName().equalsIgnoreCase("r") || cmd.getName().equalsIgnoreCase("reply"))) {
            Player p = (Player) sender; // p = l'envoyeur

            if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_REPLY)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_REPLY.getNode()));
                return true;
            }

            if (args.length == 0) {
                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/r <" + Lang.getMessage(p, "message") + ">");
            } else if (replys.get(p) != null){


                StringBuilder Broadcast = new StringBuilder("");
                for (String arg : args) {
                        Broadcast.append(" ").append(arg);

                }

                    if(!replys.get(p).isOnline()) {
                        p.sendMessage(Main.PREFIX + Lang.getMessage(p, "player_not_longer_online"));
                        return true;
                    }
                    replys.get(p).sendMessage("§7[§6" + Lang.getMessage(replys.get(p), "private_message") + "§7] §e" + p.getName() + " §7▶§e " + replys.get(p).getName() + ChatColor.WHITE + " » " + Broadcast.toString().replace('&', '§'));
                    p.sendMessage("§7[§6" + Lang.getMessage(p, "private_message") + "§7] §e" + p.getName() + " §7▶§e " + replys.get(p).getName() + ChatColor.WHITE + " » " + Broadcast.toString().replace('&', '§'));
                    replys.put(replys.get(p), p);
                    replys.put(p, replys.get(p));

            } else {
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "reply_no_player"));
            }

        }

        return false;
    }






}
