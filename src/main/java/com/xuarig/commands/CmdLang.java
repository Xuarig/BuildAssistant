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

public class CmdLang implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {

            if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_LANG)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_LANG.getNode()));
                return true;
            }

            if(args.length == 0)
                return true;

            String baseLang = Main.getInstance().getPlayersConfig().getString(sender.getName() + ".lang");

            try {
                Main.getInstance().getPlayersConfig().set(sender.getName() + ".lang", args[0]);
                Main.getInstance().savePlayersConfig();
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "lang_change"));
            } catch (NullPointerException npe) {
                Main.getInstance().getPlayersConfig().set(sender.getName() + ".lang", baseLang);
                Main.getInstance().savePlayersConfig();
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "lang_not_found"));
            }

        }

        return false;
    }

}
