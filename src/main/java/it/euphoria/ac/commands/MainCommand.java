/*
    Class created by NotStxnks_
    Project: EuphoriaAC
    Date: 25/02/2020
    Time: 10:51
*/

package it.euphoria.ac.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("euphoria.admin")) {
                player.sendMessage("§b§m--------------------");
                player.sendMessage("");
                player.sendMessage("§e/euphoria reload");
                player.sendMessage("§e/euphoria info");
                player.sendMessage("");
                player.sendMessage("§b§m--------------------");
            }
        } else {
            sender.sendMessage("§7You must be a player to execute this command.");
        }
        return true;
    }
}
