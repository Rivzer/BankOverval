package be.rivzer.bankoverval.Guis;

import be.rivzer.bankoverval.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class OvervalCode {

    public static ArrayList<Integer> alleKonts = new ArrayList<>();
    public static HashMap<UUID, Boolean> errors = new HashMap<UUID, Boolean>();
    public static int trys = 0;

    public static void openOvervalGUI(Player p){
        Inventory inv = Bukkit.createInventory(p, 45, Logger.color("&6&lLock Pick &7&l- &e&lMenu"));

        for (int i = 0; i < 45; i++){
            setGlass(inv, Material.STAINED_GLASS_PANE, i, " ", (short) 0);
        }

        alleKonts.clear();
        errors.put(p.getUniqueId(), false);
        trys = 0;

        for (int i = 0; i < 10; i++){
            p.sendMessage(String.valueOf(i));
            Random r = new Random();
            int result = r.nextInt(45 - 1) + 1;

            if(alleKonts.contains(result)) errors.put(p.getUniqueId(), true);

            alleKonts.add(result);

            while (errors.get(p.getUniqueId()) == true){
                if(errors.get(p.getUniqueId()) == true){
                    trys++;
                    result = r.nextInt(45 - 1) + 1;

                    if(!alleKonts.contains(result)) errors.put(p.getUniqueId(), false);

                    if(trys >= 2025){
                        p.sendMessage(Logger.color("&cEr is iets misgegaan!"));
                        return;
                    }
                }
            }

            setGlass(inv, Material.STAINED_GLASS_PANE, result, "&c&lKlik Hier", (short) 14);
        }

        alleKonts.clear();
        errors.put(p.getUniqueId(), false);
        trys = 0;

        p.closeInventory();
        p.openInventory(inv);
    }

    public static void setGlass(Inventory inv, Material mat, Integer slotnum, String name, short color){
        ItemStack item = new ItemStack(mat, 1, (short) color);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Logger.color(name));
        item.setItemMeta(meta);

        inv.setItem(slotnum, item);
    }

}
