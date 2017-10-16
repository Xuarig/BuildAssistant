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
import com.xuarig.utils.SpigotUpdater;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import java.awt.*;

public class CmdBuildAssistant implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender; // p = l'envoyeur

            if (!Perms.hasPermission(p , Permissions.COMMAND_BUILDASSISTANT)) {
                p.sendMessage(Main.PREFIX + Lang.getMessage( p, "no_perm").replaceAll("%perm%", Permissions.COMMAND_BUILDASSISTANT.getNode()));
                return true;
            }

            if(args.length == 0) {
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "usage") + " §7: §e/buildassistant <reload/check/version/wiki/maintenance/clearchat>");
                return true;
            }

            if(args[0].equalsIgnoreCase("version")) {
                PluginDescriptionFile pdf = Main.getInstance().getDescription();
                p.sendMessage(Main.PREFIX + ChatColor.GOLD + Lang.getMessage(p, "version").replaceAll("%version%", pdf.getVersion()));
                return true;
            }

            if(args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                try {
                    Main.getInstance().createFiles();
                    Main.getInstance().reloadConfig();
                    p.sendMessage(Main.PREFIX + ChatColor.GOLD + Lang.getMessage(p, "files_reloaded"));
                } catch (Exception e) {
                    p.sendMessage(Main.PREFIX + ChatColor.GOLD + Lang.getMessage(p, "files_reload_error"));
                }

                return true;
            }

            if(args[0].equalsIgnoreCase("check")) {
                PluginDescriptionFile pdf = Main.getInstance().getDescription();
                SpigotUpdater updater = new SpigotUpdater(Main.getInstance(), 45252);
                p.sendMessage(Main.PREFIX + Lang.getMessage(p,"checking") + ChatColor.RESET);
                try {
                    if (updater.checkForUpdates())
                        p.sendMessage(Main.PREFIX + Lang.getMessage(p,"update_found").replaceAll("%version%", pdf.getVersion()).replaceAll("%url%", updater.getResourceURL()) + ChatColor.RESET);
                    else
                        p.sendMessage(Main.PREFIX + Lang.getMessage(p, "version").replaceAll("%version%", pdf.getVersion()) + ChatColor.RESET);
                } catch (Exception e) {
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "impossible_update_check") + ChatColor.RESET);
                    p.sendMessage(e.getLocalizedMessage());
                    e.printStackTrace();
                }
                return true;
            }

            if(args[0].equalsIgnoreCase("wiki")) {
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "wiki_link").replaceAll("%link%", "https://www.spigotmc.org/resources/45252"));
                return true;
            }

            if(args[0].equalsIgnoreCase("clearchat")) {
                for(int i = 0; i < 30; i++) {
                    p.sendMessage("");
                }
                return true;
            }

            if(args[0].equalsIgnoreCase("maintenance")) {
                if (args.length == 1) {
                    Main.getInstance().getConfig().set("maintenance", !Main.getInstance().getConfig().getBoolean("maintenance"));
                    Main.getInstance().saveConfig();

                    if(Main.getInstance().getConfig().getBoolean("maintenance"))
                        p.sendMessage(Main.PREFIX + Lang.getMessage(p, "maintenance_enable"));
                    else
                        p.sendMessage(Main.PREFIX + Lang.getMessage(p, "maintenance_disable"));

                } else if(args.length == 2) {
                    if (args[1].equalsIgnoreCase("on")) {
                        p.sendMessage(Main.PREFIX + Lang.getMessage(p, "maintenance_enable"));
                        Main.getInstance().getConfig().set("maintenance", true);
                        Main.getInstance().saveConfig();
                    } else if(args[1].equalsIgnoreCase("off")) {
                        p.sendMessage(Main.PREFIX + Lang.getMessage(p, "maintenance_disable"));
                        Main.getInstance().getConfig().set("maintenance", false);
                        Main.getInstance().saveConfig();
                    }
                }

                return true;
            }














        }

        return false;

    }
}
