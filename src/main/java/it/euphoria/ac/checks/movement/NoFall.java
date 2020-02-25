/*
    Class created by NotStxnks_
    Project: EuphoriaAC
    Date: 25/02/2020
    Time: 12:26
*/

package it.euphoria.ac.checks.movement;

import it.euphoria.ac.checks.Check;
import it.euphoria.ac.data.EuphoricPlayer;
import it.euphoria.ac.utils.MoveUtils;
import it.euphoria.ac.utils.TimeUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NoFall extends Check {

    private Map<UUID, Map.Entry<Long, Integer>> noFallTicks = new HashMap<>();
    private Map<UUID, Double> fallDistance = new HashMap<>();

    public NoFall() {
        super("NoFall");
    }

    @Override
    public void onMove(EuphoricPlayer ep, Location from, Location to) {
        Player player = ep.getPlayer();
        if (player.getAllowFlight()) {
            return;
        }
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        if (player.getVehicle() != null) {
            return;
        }
        if (player.getHealth() <= 0.0) {
            return;
        }
        if (MoveUtils.isOnClimbable(player)) {
            return;
        }
        if (MoveUtils.isInWater(player)) {
            return;
        }

        double falling = 0.0;
        if (!MoveUtils.isOnGround(player) && from.getY() > to.getY()) {
            if (fallDistance.containsKey(player.getUniqueId())) {
                falling = fallDistance.get(player.getUniqueId());
            }
            falling += from.getY() - to.getY();
        }

        fallDistance.put(player.getUniqueId(), falling);

        if (falling < 3.0) {
            return;
        }

        long time = System.currentTimeMillis();

        int count = 0;

        if (noFallTicks.containsKey(player.getUniqueId())) {
            time = noFallTicks.get(player.getUniqueId()).getKey();
            count = noFallTicks.get(player.getUniqueId()).getValue();
        }

        if (player.isOnGround() || player.getFallDistance() == 0.0F) {
            ++count;
        } else {
            count = 0;
        }

        if (noFallTicks.containsKey(player.getUniqueId()) && TimeUtils.elapsed(time, 10000)) {
            count = 0;
            time = System.currentTimeMillis();
        }

        if (count >= 3) {
            count = 0;
            fallDistance.put(player.getUniqueId(), 0.0);
            ep.flag(this);
            ep.getPlayer().teleport(from);
            ep.getPlayer().setHealth(0);
        }

        noFallTicks.put(player.getUniqueId(), new AbstractMap.SimpleEntry<>(time, count));
    }
}
