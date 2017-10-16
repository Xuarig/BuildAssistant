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

package com.xuarig.events;

import com.xuarig.Main;
import com.xuarig.utils.Lang;
import com.xuarig.utils.Permissions;
import com.xuarig.utils.Perms;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BuildEvent implements Listener {

    @EventHandler
    public void onBuildEvent(BlockPlaceEvent e) {
        if (Main.getInstance().getOptionManager().isBuildProtection() && !Perms.hasPermission(e.getPlayer(), Permissions.BUILDBYPASS)) {
            if (!e.getPlayer().isOp()) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Main.PREFIX + Lang.getMessage(e.getPlayer(), "no_perm").replaceAll("%perm%", Permissions.BUILDBYPASS.getNode()));
            }

        }
    }

    @EventHandler
    public void onBreakEvent(BlockBreakEvent e) {
        if (Main.getInstance().getOptionManager().isBuildProtection() && !Perms.hasPermission(e.getPlayer(), Permissions.BUILDBYPASS)) {
            if (!e.getPlayer().isOp()) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Main.PREFIX + Lang.getMessage(e.getPlayer(), "no_perm").replaceAll("%perm%", Permissions.BUILDBYPASS.getNode()));
            }
        }
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e) {
        if (Main.getInstance().getOptionManager().isBuildProtection() && !Perms.hasPermission(e.getPlayer(), Permissions.BUILDBYPASS)) {

            if (!e.getPlayer().isOp()) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Main.PREFIX + Lang.getMessage(e.getPlayer(), "no_perm").replaceAll("%perm%", Permissions.BUILDBYPASS.getNode()));
            }
        }
    }

}
