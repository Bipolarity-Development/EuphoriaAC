/*
    Class created by NotStxnks_
    Project: EuphoriaAC
    Date: 25/02/2020
    Time: 11:19
*/

package it.euphoria.ac.listener;

import it.euphoria.ac.EuphoriaAC;
import it.euphoria.ac.data.EuphoricPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        EuphoriaAC.getInstance().playerList.add(new EuphoricPlayer(e.getPlayer()));
    }
}
