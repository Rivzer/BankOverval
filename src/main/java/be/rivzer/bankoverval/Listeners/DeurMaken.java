package be.rivzer.bankoverval.Listeners;

import be.rivzer.bankoverval.Config.Config;
import be.rivzer.bankoverval.Logger;
import be.rivzer.bankoverval.Main;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.List;

public class DeurMaken implements Listener {

    @EventHandler
    public void onMaken(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if(!p.hasPermission("bankov.maakdeur")) return;

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getHand().equals(EquipmentSlot.HAND)){
            if(Main.playerbezig.contains(p.getUniqueId())){
                if(e.getClickedBlock().getType() == Material.IRON_DOOR_BLOCK){
                    World world = e.getClickedBlock().getWorld();
                    int x = e.getClickedBlock().getX();
                    int y = e.getClickedBlock().getY();
                    int z = e.getClickedBlock().getZ();
                    List<String> size = Config.getCustomConfig2().getStringList("DeurenTotaal");
                    List<String> atm = Config.getCustomConfig2().getStringList("DeurenATM");

                    atm.add("Deur-" + size.size());
                    size.add("Deur-" + size.size());

                    Config.getCustomConfig2().set("Deuren." + "Deur-" + (size.size()-1) + ".World", world.getName());
                    Config.getCustomConfig2().set("Deuren." + "Deur-" + (size.size()-1) + ".X", x);
                    Config.getCustomConfig2().set("Deuren." + "Deur-" + (size.size()-1) + ".Y", y);
                    Config.getCustomConfig2().set("Deuren." + "Deur-" + (size.size()-1) + ".Z", z);
                    Config.getCustomConfig2().set("DeurenTotaal", size);
                    Config.getCustomConfig2().set("DeurenATM", atm);

                    Config.saveConfig2();

                    Main.playerbezig.remove(p.getUniqueId());
                    p.sendMessage(Logger.color("&7U heeft deze deur succsesvol opgeslagen als een &bbank overval &7deur!"));
                }
            }
        }
    }

}
