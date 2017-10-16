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

package com.xuarig.events;

import com.xuarig.Main;
import com.xuarig.commands.CmdTP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportationCore implements Listener{

    @EventHandler
    public void onTeleportation(PlayerTeleportEvent event) {
        CmdTP.back.put(event.getPlayer(), event.getFrom());
        for(Player player : Bukkit.getOnlinePlayers())
            player.hidePlayer(event.getPlayer());
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers())
                    player.showPlayer(event.getPlayer());
            }
        }.runTaskLater(Main.getInstance(), 100);
    }



}
