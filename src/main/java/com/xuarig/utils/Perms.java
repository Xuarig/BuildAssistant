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

package com.xuarig.utils;

import com.xuarig.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Perms {

    public static boolean hasPermission(Player player, Permissions permissions) {
        if(Main.getInstance().getConfig().getBoolean("permissions")) {
            return player.hasPermission(permissions.getNode());
        } else {
            return player.isOp();
        }
    }

}
