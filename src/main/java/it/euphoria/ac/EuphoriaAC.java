package it.euphoria.ac;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class EuphoriaAC extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("euphoria")).setExecutor(new EuphoriaAC());
        Bukkit.getConsoleSender().sendMessage("§2§lEuphoriaAC has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("§c§lEuphoriaAC has been disabled!");
    }
}
