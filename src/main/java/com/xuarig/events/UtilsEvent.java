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
import com.xuarig.commands.CmdPickup;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class UtilsEvent implements Listener{

    @EventHandler
    public void WeatherChange(WeatherChangeEvent e)
    {

        if(!Main.getInstance().getOptionManager().isWeatherChange()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void ItemDrop(ItemSpawnEvent e)
    {

        if(!Main.getInstance().getOptionManager().isItemSpawn()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void ice(BlockFadeEvent e)
    {
        if (!Main.getInstance().getOptionManager().isIceMelt() && e.getBlock().getType().equals(Material.ICE)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void leavesDecay(LeavesDecayEvent e)
    {
        if(!Main.getInstance().getOptionManager().isLeavesDecay()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        if(CmdPickup.noPickup.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }


}
