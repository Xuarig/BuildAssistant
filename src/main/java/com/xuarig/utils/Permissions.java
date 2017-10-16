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

package com.xuarig.utils;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

import java.awt.*;

public enum  Permissions {

    BUILDBYPASS("buildassistant.buildbypass"),
    JOIN_BYPASS("buildassistant.joinbypasss"),
    COMMAND_BUILDASSISTANT("buildassistant.command.buildassistant"),
    COMMAND_BUILDRULE("buildassistant.command.buildrule"),
    COMMAND_DELWARP("buildassistant.command.delwarp"),
    COMMAND_SETWARP("buildassistant.command.setwarp"),
    COMMAND_WARP("buildassistant.command.warp"),
    COMMAND_FLY("buildassistant.command.fly"),
    COMMAND_FLY_OWN("buildassistant.command.fly.own"),
    COMMAND_GAMEMODE("buildassistant.command.gamemode"),
    COMMAND_GAMEMODE_OWN("buildassistant.command.gamemode.own"),
    COMMAND_GET("buildassistant.command.get"),
    COMMAND_HEAD("buildassistant.command.head"),
    COMMAND_HIDE("buildassistant.command.hide"),
    COMMAND_HIDE_OWN("buildassistant.command.hide.own"),
    COMMAND_ITEMGIVE("buildassistant.command.itemgive"),
    COMMAND_LANG("buildassistant.command.lang"),
    COMMAND_MSG("buildassistant.command.msg"),
    COMMAND_REPLY("buildassistant.command.reply"),
    COMMAND_NIGHTVISION("buildassistant.command.nightvision"),
    COMMAND_PICKUP("buildassistant.command.pickup"),
    COMMAND_PENABLE("buildassistant.command.penable"),
    COMMAND_PDISABLE("buildassistant.command.pdisable"),
    COMMAND_PRELOAD("buildassistant.command.preload"),
    COMMAND_SPEED("buildassistant.command.speed"),
    COMMAND_SPEED_OWN("buildassistant.command.speed.own"),
    COMMAND_TOP("buildassistant.command.top"),
    COMMAND_BACK("buildassistant.command.back"),
    COMMAND_TP("buildassistant.command.tp"),
    COMMAND_TP_OWN("buildassistant.command.tp.own");

    String node;

    Permissions(String node) {
        this.node = node;
    }

    public String getNode() {
        return node;
    }
}

