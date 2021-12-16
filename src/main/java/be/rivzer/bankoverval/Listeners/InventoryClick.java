package be.rivzer.bankoverval.Listeners;

import be.rivzer.bankoverval.Config.Config;
import be.rivzer.bankoverval.Guis.OvervalCode;
import be.rivzer.bankoverval.Logger;
import be.rivzer.bankoverval.Main;
import be.rivzer.bankoverval.Utils.CountDownTask;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    private Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        if(p == null) return;
        if(e.getInventory() == null) return;
        if(e.getClickedInventory() == null) return;
        if(e.getClickedInventory().getName().equalsIgnoreCase(Logger.color("&6&lLock Pick &7&l- &e&lMenu"))){
            e.setCancelled(true);
            ItemStack is = e.getCurrentItem();

            if(is == null) return;
            if(is.getItemMeta() == null) return;

            if(is.getType() == Material.STAINED_GLASS_PANE){
                if(is.getItemMeta().getDisplayName().equalsIgnoreCase(Logger.color("&c&lKlik Hier"))){

                    OvervalCode.setGlass(e.getClickedInventory(), Material.STAINED_GLASS_PANE, e.getSlot(), "&a&lDone", (short) 5);

                    if(Main.overvalklaar.containsKey(p.getUniqueId())){
                        Main.overvalklaar.put(p.getUniqueId(), (Main.overvalklaar.get(p.getUniqueId()) + 1));
                        if(Main.overvalklaar.get(p.getUniqueId()) >= 10){
                            p.closeInventory();
                            (new CountDownTask(plugin, p)).runTaskTimer(this.plugin, 0L, 2L);
                            Main.freeze.put(p.getUniqueId(), System.currentTimeMillis() + (Config.getCustomConfig1().getInt("Settings.LockPickSeconds") * 1000));
                        }
                    }
                    else{
                        Main.overvalklaar.put(p.getUniqueId(), 1);
                    }
                }
            }
        }
    }

}
