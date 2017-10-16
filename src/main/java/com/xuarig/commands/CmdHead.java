package com.xuarig.commands;

import com.xuarig.Main;
import com.xuarig.utils.Lang;
import com.xuarig.utils.Permissions;
import com.xuarig.utils.Perms;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

public class CmdHead implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player && args.length == 1) {

            if (!Perms.hasPermission((Player) sender, Permissions.COMMAND_HEAD)) {
                sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "no_perm").replaceAll("%perm%", Permissions.COMMAND_HEAD.getNode()));
                return true;
            }

            ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
            SkullMeta meta = (SkullMeta) is.getItemMeta();

            meta.setOwner(args[0]);
            is.setItemMeta(meta);

            ((Player) sender).getInventory().addItem(is);

            sender.sendMessage(Main.PREFIX + Lang.getMessage((Player) sender, "head").replaceAll("%owner%", args[0]).replaceAll("%player%", sender.getName()));

            return true;

        }

        return false;

    }
}
