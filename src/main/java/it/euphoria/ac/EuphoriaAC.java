package it.euphoria.ac;

import it.euphoria.ac.checks.CheckManager;
import it.euphoria.ac.checks.movement.FlyA;
import it.euphoria.ac.data.EuphoricPlayer;
import it.euphoria.ac.listener.JoinEvent;
import it.euphoria.ac.listener.MoveEvent;
import it.euphoria.ac.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Vector;

public final class EuphoriaAC extends JavaPlugin {

    private static EuphoriaAC instance;
    public Vector<EuphoricPlayer> playerList = new Vector<>();
    private static CheckManager checkManager;
    private static Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        logger = new Logger();
        registerEvents();
        checkManager = new CheckManager();
        //Objects.requireNonNull(this.getCommand("euphoria")).setExecutor(new EuphoriaAC());
        CheckManager.registerCheck(new FlyA());
        Bukkit.getConsoleSender().sendMessage("§2§lEuphoriaAC has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("§c§lEuphoriaAC has been disabled!");
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MoveEvent(), this);
    }

    public static EuphoriaAC getInstance() {
        return instance;
    }

    public CheckManager getCheckManager() {
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
