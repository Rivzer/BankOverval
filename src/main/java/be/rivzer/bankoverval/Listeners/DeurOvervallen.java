package be.rivzer.bankoverval.Listeners;

import be.rivzer.bankoverval.Config.Config;
import be.rivzer.bankoverval.Guis.OvervalCode;
import be.rivzer.bankoverval.Logger;
import be.rivzer.bankoverval.Main;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.material.Door;

import java.util.List;

public class DeurOvervallen implements Listener {

    @EventHandler
    public void onOverval(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if(!p.hasPermission("bankov.overval")) return;

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getHand().equals(EquipmentSlot.HAND)){
            if(!(e.getClickedBlock().getType() == Material.IRON_DOOR_BLOCK)) return;
            if(p.getInventory().getItemInMainHand() == null) return;
            if(p.getInventory().getItemInMainHand().getItemMeta() == null) return;
            if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) return;
            Material mat = Material.valueOf(Config.getCustomConfig1().getString("Item.Material"));
            if(!(p.getInventory().getItemInMainHand().getType() == mat)) return;
            if(!p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(Logger.color(Config.getCustomConfig1().getString("Item.Naam")))) return;

            if(Main.overvalklaar.containsKey(p.getUniqueId())){
                if(Main.overvalklaar.get(p.getUniqueId()) >= 10){
                    p.sendMessage(Logger.color("&cU bent al aan het lockpicken!"));
                    return;
                }
            }

            World world = e.getClickedBlock().getWorld();
            int x = e.getClickedBlock().getX();
            int y = e.getClickedBlock().getY();
            int z = e.getClickedBlock().getZ();

            List<String> itemlijst2 = Config.getCustomConfig2().getStringList("DeurenATM");
            String[] items2 = (String[])itemlijst2.toArray(new String[0]);
            String[] var12 = items2;

            for(int i = 0; i < items2.length; ++i) {
                String naam = var12[i];
                if(Config.getCustomConfig2().getString("Deuren." + naam + ".World").equalsIgnoreCase(world.getName())){
                    if(Config.getCustomConfig2().getInt("Deuren." + naam + ".X") == x){
                        if(Config.getCustomConfig2().getInt("Deuren." + naam + ".Y") == y){
                            if(Config.getCustomConfig2().getInt("Deuren." + naam + ".Z") == z){
                                p.closeInventory();
                                OvervalCode.openOvervalGUI(p);
                            }
                        }
                    }
                }
            }
        }
    }

}
