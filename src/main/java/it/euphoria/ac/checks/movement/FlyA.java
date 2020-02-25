/*
    Class created by NotStxnks_
    Project: EuphoriaAC
    Date: 25/02/2020
    Time: 11:25
*/

package it.euphoria.ac.checks.movement;

import it.euphoria.ac.checks.Check;
import it.euphoria.ac.data.EuphoricPlayer;
import it.euphoria.ac.utils.MoveUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class FlyA extends Check {

    private HashMap<UUID, Long> flyTicks = new HashMap<>();

    public FlyA() {
        super("FlyA");
    }

    @Override
    public void onMove(EuphoricPlayer ep, Location from, Location to) {
        Player player = ep.getPlayer();
        if (player.getAllowFlight()) {
            return;
        }

        if (player.getVehicle() != null) {
            return;
        }

        if (MoveUtils.isInWater(player)) {
            return;
        }

        if (MoveUtils.isInWeb(player)) {
            return;
        }

        if (MoveUtils.isBlocksNear(player)) {
            flyTicks.remove(player.getUniqueId());
            return;
        }

        if (to.getX() == from.getX() && to.getZ() == from.getZ()) {
            return;
        }

        if (to.getY() != from.getY()) {
            flyTicks.remove(player.getUniqueId());
            return;
        }

        long time = System.currentTimeMillis();

        if (flyTicks.containsKey(player.getUniqueId())) {
            time = flyTicks.get(player.getUniqueId());
        }

        if (System.currentTimeMillis() - time > 500) {
            flyTicks.remove(player.getUniqueId());
            ep.flag(this);
            ep.kick(this, "§bPlease remove your Fly!");
            return;
        }

        flyTicks.put(player.getUniqueId(), time);
    }
}
