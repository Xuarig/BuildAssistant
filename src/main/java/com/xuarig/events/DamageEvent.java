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
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;

public class DamageEvent implements Listener{

    public static ArrayList<Player> NoFall = new ArrayList<>();

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            Player pl = (Player) e.getEntity();
            if(e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                pl.setAllowFlight(true);
                pl.setFlying(true);
                if(!(pl.getGameMode().equals(GameMode.CREATIVE) || pl.getGameMode().equals(GameMode.SPECTATOR))) {
                    pl.setGameMode(GameMode.SPECTATOR);
                }
                pl.teleport(pl.getWorld().getSpawnLocation());
                e.setCancelled(true);

            } else if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
                if (!Main.getInstance().getOptionManager().isDamages()) {
                    e.setCancelled(true);
                } else if (NoFall.contains(pl)) {
                    e.setCancelled(true);
                    NoFall.remove(pl);
                }

            } else {
                if (!Main.getInstance().getOptionManager().isDamages()) {
                    e.setCancelled(true);
                }

            }
        }
    }


}
