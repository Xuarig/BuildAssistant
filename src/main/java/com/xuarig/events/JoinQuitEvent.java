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
import com.xuarig.utils.SpigotUpdater;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class JoinQuitEvent implements Listener{

    @EventHandler
    public void onPlayerJoinEvent (PlayerJoinEvent e) {

        Player pl = e.getPlayer();

        if(Main.getInstance().getConfig().getBoolean("maintenance") && !Perms.hasPermission(pl, Permissions.JOIN_BYPASS)) {
            if(Main.getInstance().getPlayersConfig().contains(pl.getName() + ".lang"))
                pl.kickPlayer(Main.PREFIX + Lang.getMessage(pl, "maintenance"));
            else
                pl.kickPlayer(Main.PREFIX + Lang.getServerMessage("maintenance"));

            e.setJoinMessage(null);
            return;
        }

        UUID uuid = pl.getUniqueId();

        if (!Main.getInstance().getPlayersConfig().contains(pl.getName() + ".lang") && !Main.getInstance().getPlayersConfig().contains(pl.getName() + ".uuid")) {
            Bukkit.getLogger().warning(Lang.getServerMessage("new_player"));
            Main.getInstance().getPlayersConfig().set(pl.getName() + ".uuid", uuid.toString());
            Main.getInstance().getPlayersConfig().set(pl.getName() + ".lang", Main.getInstance().getConfig().get("lang"));
            Main.getInstance().savePlayersConfig();
        }

        if (!Main.getInstance().getPlayersConfig().getString(pl.getName() + ".uuid").equals(uuid.toString())) {
            Main.getInstance().getPlayersConfig().set(pl.getName() + ".uuid", uuid.toString());
            Main.getInstance().savePlayersConfig();
        }

        if(Main.getInstance().getConfig().getBoolean("joinMessages")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(Lang.getMessage(player, "join").replaceAll("%player%", pl.getName()));
            }

        }

        if(Main.getInstance().getConfig().getBoolean("updater") && Perms.hasPermission(pl, Permissions.COMMAND_BUILDASSISTANT)) {
            PluginDescriptionFile pdf = Main.getInstance().getDescription();
            SpigotUpdater updater = new SpigotUpdater(Main.getInstance(), 45252);
            pl.sendMessage(Main.PREFIX + Lang.getMessage(pl, "checking") + ChatColor.RESET);
            try {
                if (updater.checkForUpdates())
                    pl.sendMessage(Main.PREFIX + Lang.getMessage(pl, "update_found").replaceAll("%version%", pdf.getVersion().replaceAll("%url%", updater.getResourceURL())) + ChatColor.RESET);
            } catch (Exception exp) {
                pl.sendMessage(Main.PREFIX + Lang.getMessage(pl, "impossible_update_check") + ChatColor.RESET);
                pl.sendMessage(exp.getLocalizedMessage());
            }
        }

        e.setJoinMessage(null);

        new BukkitRunnable() {

            public void run() {

                if(!Main.getInstance().getConfig().getBoolean("permissions")) {
                    for(Permissions permissions : Permissions.values()) {
                        PermissionAttachment attach = pl.addAttachment(Main.getInstance());
                        attach.setPermission(permissions.getNode(), true);
                        pl.addAttachment(attach.getPlugin());
                    }
                }

                for(Permissions permissions : Main.getInstance().disabledPermissions){
                    PermissionAttachment attach = pl.addAttachment(Main.getInstance());
                    attach.setPermission(permissions.getNode(), true);
                    if(pl.hasPermission(permissions.getNode()))
                        pl.removeAttachment(attach);
                }

            }
        }.runTaskLater(Main.getInstance(), 1);

        if(Main.getInstance().getConfig().getBoolean("forceGamemodes")) {
            new BukkitRunnable() {

                public void run() {

                    if ((e.getPlayer().getGameMode().equals(GameMode.SURVIVAL) || e.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) && e.getPlayer().isOp()) {
                        e.getPlayer().setGameMode(GameMode.CREATIVE);
                        e.getPlayer().setFlying(true);
                    } else if ((e.getPlayer().getGameMode().equals(GameMode.SURVIVAL) || e.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) && !e.getPlayer().isOp()) {
                        e.getPlayer().setGameMode(GameMode.SPECTATOR);
                        e.getPlayer().setFlying(true);
                    }

                }
            }.runTaskLater(Main.getInstance(), 10);
        }


    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        Player pl = e.getPlayer();

        if(Main.getInstance().getConfig().getBoolean("maintenance") && !Perms.hasPermission(pl, Permissions.JOIN_BYPASS)) {
            e.setQuitMessage(null);
            return;
        }

        if(Main.getInstance().getConfig().getBoolean("joinMessages"))
            for (Player player : Bukkit.getOnlinePlayers())
                player.sendMessage(Lang.getMessage(player, "quit").replaceAll("%player%", pl.getName()));


        e.setQuitMessage(null);
    }
}
