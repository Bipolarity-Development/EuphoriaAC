package it.euphoria.ac.listener;

import it.euphoria.ac.EuphoriaAC;
import it.euphoria.ac.checks.Check;
import it.euphoria.ac.data.EuphoricPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        for (Check c : EuphoriaAC.getInstance().getCheckManager().getChecks()) {
            EuphoricPlayer fp = EuphoriaAC.getInstance().get(e.getPlayer());
            c.onMove(fp, e.getFrom(), e.getTo());
        }
    }
}