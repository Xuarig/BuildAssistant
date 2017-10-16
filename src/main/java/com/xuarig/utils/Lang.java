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

import com.xuarig.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Lang {

    public static String getMessage(String name, Player player) {
        return getMessage(name, getLang(player), true);
    }

    public static String getMessage(String name, String lang, boolean dotrue) {

        FileConfiguration fileConfiguration = getLangFile(lang);

        try {
            return fileConfiguration.getString(name)
                    .replaceAll("&", "§")
                    .replaceAll("@a@", "é")
                    .replaceAll("@b@", "ê")
                    .replaceAll("@c@", "è")
                    .replaceAll("@d@", "à")
                    .replaceAll("@e@", "â")
                    .replaceAll("@f@", "ù")
                    .replaceAll("@g@", "û")
                    .replaceAll("@h@", "ç")
                    .replaceAll("@i@", "É")
                    .replaceAll("@j@", "È")
                    .replaceAll("@k@", "Ê");
        } catch (NullPointerException npe) {
            return fileConfiguration.getString("no_translation");
        }
    }

    public static String getServerMessage(String name) {
        return getMessage(name, Main.getInstance().getConfig().getString("lang"), true);
    }

    public static String getMessage(Player player, String name) {
        return getMessage(player.getName(), name);
    }

    public static String getMessage(String playerName, String name) {
        return getMessage(name, Bukkit.getPlayer(playerName));
    }

    public static String getLang(Player player) {
        return getLang(player.getName());
    }

    public static String getLang(String playerName) {
        return Main.getInstance().getPlayersConfig().getString(playerName + ".lang");
    }

    public static FileConfiguration getLangFile(String name) {
        FileConfiguration fileConfiguration;
        File file;
        file = new File(Main.getInstance().getDataFolder(), "languages/" + name + ".yml");

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            Main.getInstance().saveResource("languages/" + name + ".yml", false);
        }

        fileConfiguration = new Utf8YamlConfiguration();
        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return fileConfiguration;
    }
}