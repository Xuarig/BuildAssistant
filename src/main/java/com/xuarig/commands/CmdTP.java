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

package com.xuarig.commands;

import com.xuarig.utils.Lang;
import com.xuarig.Main;
import com.xuarig.utils.Permissions;
import com.xuarig.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CmdTP implements CommandExecutor{

    public static HashMap<Player, Location> back = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player && (cmd.getName().equalsIgnoreCase("tp"))) {

            if(!Perms.hasPermission((Player) sender, Permissions.COMMAND_TP_OWN)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_TP_OWN.getNode()));
                return true;
            }

            Player p = (Player) sender; // p = l'envoyeur


            if(Main.getInstance().getConfig().getBoolean("default_tp")) {
                StringBuilder builder = new StringBuilder();
                for(String arg : args) {
                    builder.append(arg).append(" ");
                }

                Bukkit.dispatchCommand(p, "minecraft:tp " + builder);

                return true;
            }

            if (args.length == 0 || args.length > 6) {
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "usage") + " §7: §e/tp <" + Lang.getMessage(p,"player") + "> [" + Lang.getMessage(p,"player") + "]");
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "usage") + " §7: §e/tp [" + Lang.getMessage(p,"player") + "] <x> <y> <z> [yaw] [pitch]");
            } else {
                if (args.length == 1) {
                    try {
                        back.put(p, p.getLocation());
                        p.teleport(Bukkit.getPlayer(args[0]));
                    } catch (NullPointerException e) {
                        p.sendMessage(Main.PREFIX + Lang.getMessage(p, "player_not_found"));
                    }

                    if(p.getLocation().add(0, -3, 0).getBlock().getType() == Material.AIR)
                        p.setAllowFlight(true);
                        p.setFlying(true);
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "teleportation_to").replaceAll("%to%", args[0]));
                } else if (args.length == 2){
                    if(!sender.hasPermission(Permissions.COMMAND_TP.getNode())) {
                        sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm"));
                        return true;
                    }
                    try {
                        back.put(Bukkit.getPlayer(args[0]), Bukkit.getPlayer(args[0]).getLocation());
                        Bukkit.getPlayer(args[0]).teleport(Bukkit.getPlayer(args[1]));
                        Bukkit.getPlayer(args[0]).setFlying(true);
                        p.sendMessage(Main.PREFIX + Lang.getMessage(p, "teleportation_from_to").replaceAll("%to%", args[1]).replaceAll("%from%", args[0]));
                        Bukkit.getPlayer(args[0]).sendMessage(Main.PREFIX + Lang.getMessage(p, "teleportation_to").replaceAll("%to%", args[1]));
                    } catch (NullPointerException e) {
                        p.sendMessage(Main.PREFIX + Lang.getMessage(p, "one_of_two_player_not_found"));
                    }
                } else if(args.length == 3 || args.length == 5) {
                    try {
                        Location loc;
                        double x;
                        double y;
                        double z;

                        if(args[0].startsWith("~")) {
                            String X = args[0].replace("~", "");
                            if(X.isEmpty()) {
                                x = p.getLocation().getX();
                            } else {
                                x = Double.parseDouble(X) + p.getLocation().getX();
                            }
                        } else {
                            x = Double.parseDouble(args[0]);
                        }
                        if(args[1].startsWith("~")) {
                            String Y = args[1].replace("~", "");
                            if(Y.isEmpty()) {
                                y = p.getLocation().getY();
                            } else {
                                y = Double.parseDouble(Y) + p.getLocation().getY();
                            }
                        } else {
                            y = Double.parseDouble(args[1]);
                        }
                        if(args[2].startsWith("~")) {
                            String Z = args[2].replace("~", "");
                            if(Z.isEmpty()) {
                                z = p.getLocation().getZ();
                            } else {
                                z = Double.parseDouble(Z) + p.getLocation().getZ();
                            }
                        } else {
                            z = Double.parseDouble(args[2]);
                        }

                        if(args.length == 3) {
                            loc = new Location(p.getWorld(), x, y, z, p.getLocation().getYaw(), p.getLocation().getPitch());
                        } else {
                            loc = new Location(p.getWorld(), x, y, z, Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                        }

                        p.setFlying(true);

                        p.teleport(loc);
                    } catch (NumberFormatException exp) {
                        p.sendMessage(Main.PREFIX + Lang.getMessage(p, "usage") + " §7: §e/tp [" + Lang.getMessage(p,"player") + "] <x> <y> <z> [yaw] [pitch]");
                    }
                } else if (args.length == 4 || args.length == 6) {
                    if(!Perms.hasPermission((Player) sender, Permissions.COMMAND_TP)) {
                        sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_TP.getNode()));
                        return true;
                    }
                    try {
                        Player pl = Bukkit.getPlayer(args[0]);

                        Location loc;
                        double x;
                        double y;
                        double z;

                        if(args[1].startsWith("~")) {
                            String X = args[1].replace("~", "");
                            if(X.isEmpty()) {
                                x = p.getLocation().getX();
                            } else {
                                x = Double.parseDouble(X) + pl.getLocation().getX();
                            }
                        } else {
                            x = Double.parseDouble(args[1]);
                        }
                        if(args[2].startsWith("~")) {
                            String Y = args[2].replace("~", "");
                            if(Y.isEmpty()) {
                                y = p.getLocation().getY();
                            } else {
                                y = Double.parseDouble(Y) + pl.getLocation().getY();
                            }
                        } else {
                            y = Double.parseDouble(args[2]);
                        }
                        if(args[3].startsWith("~")) {
                            String Z = args[3].replace("~", "");
                            if(Z.isEmpty()) {
                                z = p.getLocation().getZ();
                            } else {
                                z = Double.parseDouble(Z) + pl.getLocation().getZ();
                            }
                        } else {
                            z = Double.parseDouble(args[3]);
                        }

                        if(args.length == 4) {
                            loc = new Location(p.getWorld(), x, y, z, pl.getLocation().getYaw(), pl.getLocation().getPitch());
                        } else {
                            loc = new Location(p.getWorld(), x, y, z, Integer.parseInt(args[4]), Integer.parseInt(args[5]));
                        }

                        pl.setFlying(true);

                        pl.teleport(loc);
                    } catch (NumberFormatException e) {
                        p.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "usage") + " §7: §e/tp [" + Lang.getMessage(p,"player") + "] <x> <y> <z> [yaw] [pitch]");
                    } catch (NullPointerException e) {
                        p.sendMessage(Main.PREFIX + Lang.getMessage(p, "player_not_found"));
                    }
                }
            }

        } else if (sender instanceof Player && (cmd.getName().equalsIgnoreCase("back"))) {

            if(!Perms.hasPermission((Player) sender, Permissions.COMMAND_BACK)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_BACK.getNode()));
                return true;
            }

            Player p = (Player) sender; // p = l'envoyeur
            if (args.length > 0) {
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "usage") + " §7: §e/back");
            } else {
                if(back.get(p) == null) {
                    p.sendMessage(Main.PREFIX + Lang.getMessage(p, "impossible_back"));
                    return true;
                }

                p.teleport(back.get(p));
                p.sendMessage(Main.PREFIX + Lang.getMessage(p, "back"));
            }

        }

        return false;

    }



}
