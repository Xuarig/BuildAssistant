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
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdGm implements CommandExecutor, TabCompleter {

    private final String[] COMMANDS = {"survival", "creative", "adventure", "spectator"};

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player) {

            if (args.length == 1 && !Perms.hasPermission((Player) sender, Permissions.COMMAND_GAMEMODE_OWN)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_GAMEMODE_OWN.getNode()));
                return true;
            } else if (args.length >= 2 && !Perms.hasPermission((Player) sender, Permissions.COMMAND_GAMEMODE)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_GAMEMODE.getNode()));
                return true;
            }

            Player player;

            if (args.length == 1) {
                player = (Player) sender;
            } else if (args.length == 2) {

                player = Bukkit.getPlayer(args[1]);
                if(player == null) {
                    sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "player_not_found"));
                    return true;
                }
            } else {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/gm <" + Lang.getMessage((Player) sender, "gamemode") + "> [" + Lang.getMessage((Player) sender, "player") + "]");
                return true;
            }
            
            switch (args[0]) {
                case "0": case "survival":
                    if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "gamemode_change").replaceAll("%gamemode%",Lang.getMessage((Player) sender, "survival")));
                    }
                    break;
                case "1":case "creative":
                    if (!player.getGameMode().equals(GameMode.CREATIVE)) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "gamemode_change").replaceAll("%gamemode%",Lang.getMessage((Player) sender, "creative")));
                    }
                    break;
                case "2": case "adventure":
                    if (!player.getGameMode().equals(GameMode.ADVENTURE)) {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "gamemode_change").replaceAll("%gamemode%",Lang.getMessage((Player) sender, "adventure")));
                    }
                    break;
                case "3": case "spectator":
                    if (!player.getGameMode().equals(GameMode.SPECTATOR)) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "gamemode_change").replaceAll("%gamemode%",Lang.getMessage((Player) sender, "spectator")));
                    }
                    break;
            }
        }

        return false;

        }


    public List<String> onTabComplete(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player && Perms.hasPermission((Player) sender, Permissions.COMMAND_GAMEMODE_OWN)) {
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

