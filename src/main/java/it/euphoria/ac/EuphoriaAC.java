package it.euphoria.ac;

import it.euphoria.ac.checks.CheckManager;
import it.euphoria.ac.checks.movement.Fly;
import it.euphoria.ac.data.EuphoricPlayer;
import it.euphoria.ac.listener.JoinEvent;
import it.euphoria.ac.listener.MoveEvent;
import it.euphoria.ac.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.Vector;

public final class EuphoriaAC extends JavaPlugin {

    private static EuphoriaAC plugin;
    public Vector<EuphoricPlayer> playerList = new Vector<>();
    private static CheckManager checkManager;
    private static Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        logger = new Logger(this);
        registerEvents();
        checkManager = new CheckManager(this);
        CheckManager.registerCheck(new Fly());
        Objects.requireNonNull(getCommand("euphoria")).setExecutor(new EuphoriaAC());
        Bukkit.getConsoleSender().sendMessage("§2§lEuphoriaAC has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("§c§lEuphoriaAC has been disabled!");
    }

    public static EuphoriaAC getPlugin() {
        return plugin;
    }

    private static void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), plugin);
        Bukkit.getPluginManager().registerEvents(new MoveEvent(), plugin);
//        Bukkit.getPluginManager().registerEvents(new InteractEvent(), this);
//        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
//        Bukkit.getPluginManager().registerEvents(new DamageEvent(), this);
//        Bukkit.getPluginManager().registerEvents(new TeleportEvent(), this);
    }

    public static CheckManager getCheckManager() {
        return checkManager;
    }

    public Logger getACLogger() {
        return logger;
    }

    public EuphoricPlayer get(Player p) {
        for (EuphoricPlayer fp : playerList) {
            if (fp.getPlayer() == p) {
                return fp;
            }
        }
        EuphoricPlayer newEp = new EuphoricPlayer(p);
        playerList.add(newEp);
        return newEp;
    }
}
