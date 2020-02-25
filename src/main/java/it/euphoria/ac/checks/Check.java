/*
    Class created by NotStxnks_
    Project: EuphoriaAC
    Date: 25/02/2020
    Time: 11:08
*/

package it.euphoria.ac.checks;

import it.euphoria.ac.data.EuphoricPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;

public class Check implements Listener {

    String name;
    boolean disabled;

    public Check(String name){
        setName(name);
        setDisabled(false);
    }

    public boolean isDisabled() {
        return disabled;
    }

    public String getName() {
        return name;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void onInteract(EuphoricPlayer ep) {}

    public void onDamage(EuphoricPlayer ep, Entity damagedEntity) {}

    public void onTeleport(EuphoricPlayer ep, Location from, Location to) {}

    public void onMove(EuphoricPlayer ep, Location from, Location to) {}

    public void onUpdate(EuphoricPlayer ep) {}
}
