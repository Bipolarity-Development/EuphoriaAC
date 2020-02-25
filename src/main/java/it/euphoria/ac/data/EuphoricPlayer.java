package it.euphoria.ac.data;

import it.euphoria.ac.checks.Check;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class EuphoricPlayer {

    private Player player;
    private String name;
    private long lastFlag;
    private int timeBetweenFlags = 1;

    public EuphoricPlayer(Player p) {
        setPlayer(p);
        setName(p.getName());
    }

    private void setPlayer(Player player) {
        this.player = player;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }

    public boolean canBypass() {
        if (player.isFlying())
            return true;
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)
            return true;
        return player.hasPermission("euphoria.bypass");
    }

    public void kick(Check c, String message) {
        lastFlag = System.currentTimeMillis();
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.isOp() || all.hasPermission("euphoria.notify")) {
                all.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7The Player " + player.getName() + " &7was"));
                all.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7kicked for: " + c.getName()));
                all.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7" + message));
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7The Player " + player.getName() + " &7was"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7kicked for: " + c.getName()));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7" + message));

        getPlayer().kickPlayer("§3Euphoria§bAC" + "\n" + message);
    }

    public void flag(Check c, String message) {
        if (!(lastFlag + timeBetweenFlags * 1000L < System.currentTimeMillis()))
            return;
        lastFlag = System.currentTimeMillis();
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.isOp() || all.hasPermission("euphoria.notify")) {
                all.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7The Player " + player.getName() + " &7was"));
                all.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7detected for: " + c.getName()));
                all.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7" + message));
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7The Player " + player.getName() + " &7was"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7detected for: " + c.getName()));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7" + message));
    }

    public void flag(Check c) {
        if (!(lastFlag + timeBetweenFlags * 1000L < System.currentTimeMillis()))
            return;
        lastFlag = System.currentTimeMillis();
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.isOp() || all.hasPermission("euphoria.notify")) {
                all.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7The Player " + player.getName() + " &7was"));
                all.sendMessage("§8> §7detected for: " + c.getName());
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7The Player " + player.getName() + " &7was"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8> &7detected for: " + c.getName()));
    }
}