package be.rivzer.bankoverval.Listeners;

import be.rivzer.bankoverval.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnMove implements Listener {

    @EventHandler
    public void Freeze (PlayerMoveEvent e){
        if(Main.freeze.containsKey(e.getPlayer().getUniqueId())) {
            if(Main.freeze.get(e.getPlayer().getUniqueId()) > System.currentTimeMillis()){
                e.setCancelled(true);
            }
        }
        else{
            e.setCancelled(false);
        }
    }

}
