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

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Openable;

public class InteractEvent implements Listener{

    @EventHandler
    public void onClick(PlayerInteractEvent event) {

        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) && event.getPlayer().isSneaking() && event.getPlayer().getGameMode() == GameMode.CREATIVE && event.getPlayer().isFlying()) {

            if(event.getPlayer().getInventory().getItemInHand().getType().isSolid() && event.getPlayer().getInventory().getItemInHand().getType().isBlock()) {
                event.getPlayer().getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().setType(event.getMaterial());
                event.getPlayer().getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().setData((byte) event.getItem().getDurability());
            }
            return;
        }

        if(event.getClickedBlock() == null)
            return;

        /*if(event.getClickedBlock().getType().equals(Material.SIGN)) {
            Sign sign = (Sign) event.getClickedBlock();
        }*/


        if(event.getAction() == Action.RIGHT_CLICK_BLOCK
                && (event.getClickedBlock().getType().equals(Material.IRON_TRAPDOOR) || event.getClickedBlock().getType().equals(Material.IRON_DOOR_BLOCK))
                && event.getPlayer().getGameMode() == GameMode.CREATIVE ) {
            BlockState state = event.getClickedBlock().getState();
            Openable openable = (Openable) state.getData();
            if(openable.isOpen()) {
                openable.setOpen(false);
                state.setData((MaterialData) openable);
                state.update(true);
            } else {
                openable.setOpen(true);
                state.setData((MaterialData) openable);
                state.update(true);
            }
        }


    }

}
