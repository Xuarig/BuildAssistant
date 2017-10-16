/*
 *
 *  *  Copyright © 2017 XUARIG ALL RIGHT RESERVED
 *  *
 *  *  All modifications, decompilations, distributions and usages (commercials or not) must be allowed by the owner.
 *  *  All contributions will be copyright © XUARIG unless otherwise specified.
 *  *
 *  *  Toutes modifications, décompilations, distributions et utilisations (commerciales ou non) doivent être approuvés par le propriétaire.
 *  *  Toutes contributions seront copyright © XUARIG sauf indication contraire.
 *  *
 *  *  Updated on 10/02/17 04:41 by Xuarig
 *  *  Mis à jour le 02/10/17 04:41 par Xuarig
 *
 */

package com.xuarig.commands;

import com.xuarig.Main;
import com.xuarig.utils.Lang;
import com.xuarig.utils.Permissions;
import com.xuarig.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CmdGet implements CommandExecutor, TabCompleter{

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_GET)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage(p, "no_perm").replaceAll("%perm%", Permissions.COMMAND_GET.getNode()));
                return true;
            } else if (args.length != 1 && args.length != 2) {
                p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/get <" + Lang.getMessage(p, "name") + "> [data]");
            } else {

                ArrayList<String> materials = new ArrayList<>();
                for(Material material : Material.values()) {
                    materials.add(material.name());
                }

                if(!materials.contains(args[0])) {
                    p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "item_not_found").replaceAll("%name%", args[0]));
                    return true;
                }

                ItemStack stack = new ItemStack(Material.getMaterial(args[0]));
                try {
                    if (args.length == 2) {
                        stack.setDurability(Short.parseShort(args[1]));
                        p.getInventory().addItem(stack);
                        p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "item_get").replaceAll("%name%", args[0] + ":" + args[1]).replaceAll("%player%", p.getName()));
                    } else {
                        p.getInventory().addItem(stack);
                        p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "item_get").replaceAll("%name%", args[0]).replaceAll("%player%", p.getName()));
                    }
                } catch (NumberFormatException e) {
                    p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "item_not_found").replaceAll("%name%", args[0]));
                }

            }
        }

        return false;

    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player && Perms.hasPermission((Player) sender, Permissions.COMMAND_GET)) {
            if (args.length == 1) {
                ArrayList<String> commands = new ArrayList<>();

                if (!args[0].equals("")) {
                    for (Material commandes : Material.values()) {
                        if (commandes.name().toLowerCase().startsWith(args[0].toLowerCase())) {
                            commands.add(commandes.name());
                        }
                    }
                } else {
                    for (Material commandes : Material.values()) {
                        commands.add(commandes.name());
                    }
                }
                //Collections.sort(com.xuarig.commands);

                return commands;
            }
        }

        return null;

    }
}
