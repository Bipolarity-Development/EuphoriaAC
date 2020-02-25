package it.euphoria.ac.checks;

import it.euphoria.ac.EuphoriaAC;
import it.euphoria.ac.data.EuphoricPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Vector;

public class CheckManager {

    private static Vector<Check> checks = new Vector<>();

    public CheckManager() {
        new BukkitRunnable() {

            @Override
            public void run() {

                for (Check c : checks) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        EuphoricPlayer fp = EuphoriaAC.getInstance().get(p);
                        c.onUpdate(fp);
                    }
                }
            }
        }.runTaskTimerAsynchronously(EuphoriaAC.getInstance(), 1, 1);
    }

    public static void registerCheck(Check c) {
        if (!checks.contains(c)) {
            checks.add(c);
            EuphoriaAC.getInstance().getServer().getPluginManager().registerEvents(c, EuphoriaAC.getInstance());
            EuphoriaAC.getInstance().getACLogger().log(c.getName() + " successfully registered!");
        }
    }

    public Check getCheckByClass(Class<?> c) {
        for (Check check : checks) {
            if (check.getClass() == c) {
                return check;
            }
        }
        return null;
    }

    public Vector<Check> getChecks(){
        return checks;
    }
}