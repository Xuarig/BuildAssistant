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
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CmdPluginManager implements CommandExecutor, TabCompleter{

    private final Plugin[] COMMANDS = Main.getInstance().getServer().getPluginManager().getPlugins();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(!(sender instanceof Player)) return true;

            if (cmd.getName().equalsIgnoreCase("penable") && args.length == 1) {

                if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_PENABLE)) {
                    sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_PENABLE.getNode()));
                    return true;
                }

                try {
                    if (args[0].equalsIgnoreCase("all")) {
                        for(Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
                            if(!plugin.isEnabled()) {
                                Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(plugin.getName()));
                                Bukkit.getPluginManager().getPlugin(plugin.getName()).reloadConfig();
                                Bukkit.broadcastMessage(Main.PREFIX + Lang.getServerMessage("plugin_enable").replaceAll("%plugin%", plugin.getName()));
                            }
                        }
                        return true;
                    }
                    Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(args[0]));
                    Bukkit.getPluginManager().getPlugin(args[0]).reloadConfig();
                    Bukkit.broadcastMessage(Main.PREFIX + Lang.getServerMessage("plugin_enable").replaceAll("%plugin%", args[0]));
                } catch (Exception ex) {
                    sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "plugin_not_found"));
                }
            } else if (cmd.getName().equalsIgnoreCase("pdisable") && args.length == 1) {

                if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_PDISABLE)) {
                    sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_PDISABLE.getNode()));
                    return true;
                }

                if (args[0].equalsIgnoreCase("all")) {
                    for(Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
                        if(plugin.isEnabled()) {
                            Bukkit.getServer().getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(plugin.getName()));
                            Bukkit.broadcastMessage(Main.PREFIX + Lang.getServerMessage("plugin_disable").replaceAll("%plugin%", plugin.getName()));
                        }
                    }
                    return true;
                }
                if (Bukkit.getServer().getPluginManager().isPluginEnabled(args[0])) {
                    Bukkit.getServer().getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(args[0]));
                    Bukkit.broadcastMessage(Main.PREFIX + Lang.getServerMessage("plugin_disable").replaceAll("%plugin%", args[0]));
                } else {
                    sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "plugin_not_load"));
                }
            } else if (cmd.getName().equalsIgnoreCase("preload") && args.length == 1) {
                if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_PRELOAD)) {
                    sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_ITEMGIVE.getNode()));
                    return true;
                }
                if (args[0].equalsIgnoreCase("all")) {
                    Bukkit.getServer().reload();
                    return true;
                }
                if (Bukkit.getServer().getPluginManager().isPluginEnabled(args[0])) {
                    Bukkit.getServer().getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(args[0]));
                    Bukkit.broadcastMessage(Main.PREFIX + Lang.getServerMessage("plugin_disable").replaceAll("%plugin%", args[0]));
                    Bukkit.getServer().getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(args[0]));
                    Bukkit.broadcastMessage(Main.PREFIX + Lang.getServerMessage("plugin_enable").replaceAll("%plugin%", args[0]));
                    Bukkit.getPluginManager().getPlugin(args[0]).reloadConfig();
                } else {
                    sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "plugin_not_load"));
                }
            } else {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/penable <plugin/all>");
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/pdisable <plugin/all>");
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/preload <plugin/all>");
            }

        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player && sender.isOp()) {
                if (args.length == 1) {
                    ArrayList<String> commands = new ArrayList<String>();

                    if (!args[0].equals("")) {

                        for (Plugin commandes : COMMANDS) {
                            if (commandes.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                                commands.add(commandes.getName());
                            }
                        }
                    } else {
                        for (Plugin commandes : COMMANDS) {
                            commands.add(commandes.getName());
                        }
                    }
                    //Collections.sort(com.xuarig.commands);

                    return commands;
                }
            }

        return null;

    }


}
