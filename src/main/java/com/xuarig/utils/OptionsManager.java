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
import org.bukkit.configuration.file.FileConfiguration;

public class OptionsManager {

    private boolean mobSpawn;
    private boolean leavesDecay;
    private boolean iceMelt;
    private boolean damages;
    private boolean weatherChange;
    private boolean itemSpawn;
    private boolean doDaylightCycle;
    private boolean buildProtection;
    private boolean quitMessages;
    private boolean joinMessages;
    private boolean forceGamemodes;
    private boolean defaultTp;
    private boolean permissions;
    private boolean updater;
    private boolean maintenance;
    private boolean prefix;
    private FileConfiguration config;

    public OptionsManager() {
        this.config = Main.getInstance().getConfig();
        reloadOptions();
    }

    public void reloadOptions(){
        setBuildProtection(config.getBoolean("buildProtection"));
        setDamages(config.getBoolean("damages"));
        setDefaultTp(config.getBoolean("default_tp"));
        setDoDaylightCycle(config.getBoolean("doDaylightCycle"));
        setForceGamemodes(config.getBoolean("forceGamemodes"));
        setIceMelt(config.getBoolean("iceMelt"));
        setItemSpawn(config.getBoolean("itemSpawn"));
        setJoinMessages(config.getBoolean("joinMessages"));
        setQuitMessages(config.getBoolean("quitMessages"));
        setMaintenance(config.getBoolean("maintenance"));
        setLeavesDecay(config.getBoolean("leavesDecay"));
        setWeatherChange(config.getBoolean("weatherChange"));
        setUpdater(config.getBoolean("updater"));
        setPermissions(config.getBoolean("permissions"));
        setPrefix(config.getBoolean("prefix"));
        setMobSpawn(config.getBoolean("mobSpawn"));
    }

    public boolean isBuildProtection() {
        return buildProtection;
    }

    public boolean isDamages() {
        return damages;
    }

    public boolean isDefaultTp() {
        return defaultTp;
    }

    public boolean isDoDaylightCycle() {
        return doDaylightCycle;
    }

    public boolean isForceGamemodes() {
        return forceGamemodes;
    }

    public boolean isIceMelt() {
        return iceMelt;
    }

    public boolean isItemSpawn() {
        return itemSpawn;
    }

    public boolean isJoinMessages() {
        return joinMessages;
    }

    public boolean isLeavesDecay() {
        return leavesDecay;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public boolean isMobSpawn() {
        return mobSpawn;
    }

    public boolean isPermissions() {
        return permissions;
    }

    public boolean isQuitMessages() {
        return quitMessages;
    }

    public boolean isUpdater() {
        return updater;
    }

    public boolean isWeatherChange() {
        return weatherChange;
    }

    public void setBuildProtection(boolean buildProtection) {
        this.buildProtection = buildProtection;
    }

    public void setDamages(boolean damages) {
        this.damages = damages;
    }

    public void setDefaultTp(boolean defaultTp) {
        this.defaultTp = defaultTp;
    }

    public void setDoDaylightCycle(boolean doDaylightCycle) {
        this.doDaylightCycle = doDaylightCycle;
    }

    public void setForceGamemodes(boolean forceGamemodes) {
        this.forceGamemodes = forceGamemodes;
    }

    public void setIceMelt(boolean iceMelt) {
        this.iceMelt = iceMelt;
    }

    public void setItemSpawn(boolean itemSpawn) {
        this.itemSpawn = itemSpawn;
    }

    public void setJoinMessages(boolean joinMessages) {
        this.joinMessages = joinMessages;
    }

    public void setLeavesDecay(boolean leavesDecay) {
        this.leavesDecay = leavesDecay;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    public void setMobSpawn(boolean mobSpawn) {
        this.mobSpawn = mobSpawn;
    }

    public void setPermissions(boolean permissions) {
        this.permissions = permissions;
    }

    public void setQuitMessages(boolean quitMessages) {
        this.quitMessages = quitMessages;
    }

    public void setUpdater(boolean updater) {
        this.updater = updater;
    }

    public void setWeatherChange(boolean weatherChange) {
        this.weatherChange = weatherChange;
    }

    public boolean isPrefix() {
        return prefix;
    }

    public void setPrefix(boolean prefix) {
        this.prefix = prefix;
    }
}
