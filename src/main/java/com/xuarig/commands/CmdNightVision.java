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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class CmdNightVision implements CommandExecutor{

    private ArrayList<Player> nightVision = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;


            if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_NIGHTVISION)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_NIGHTVISION.getNode()));
                return true;
            }

            if (args.length > 1) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/nightvision [On/Off]");
                return true;
            }

            if (args.length == 0) {
                if (!nightVision.contains(p)) {
                    nightVision.add(p);
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "nightvision_enable"));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, true, false), true);
                } else {
                    nightVision.remove(p);
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "nightvision_disable"));
                    p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                }
                return true;
            }

            if(args[0].equalsIgnoreCase("off")) {
                nightVision.add(p);
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "nightvision_disable"));
                p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                return true;
            }

            if(args[0].equalsIgnoreCase("on")) {
                nightVision.remove(p);
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "nightvision_enable"));
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, true, false), true);
            }

        }

        return false;

    }



}
