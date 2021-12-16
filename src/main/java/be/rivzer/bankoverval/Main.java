package be.rivzer.bankoverval;

import be.rivzer.bankoverval.Commands.bankov;
import be.rivzer.bankoverval.Config.Config;
import be.rivzer.bankoverval.Listeners.DeurMaken;
import be.rivzer.bankoverval.Listeners.DeurOvervallen;
import be.rivzer.bankoverval.Listeners.InventoryClick;
import be.rivzer.bankoverval.Listeners.OnMove;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    public static ArrayList<UUID> playerbezig = new ArrayList<UUID>();
    public static ArrayList<UUID> aanhetlockpicken = new ArrayList<UUID>();
    public static HashMap<UUID, Integer> overvalklaar = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Long> freeze = new HashMap<UUID, Long>();
    private static Economy econ = null;

    @Override
    public void onEnable() {

        Config.createCustomConfig1();
        Config.createCustomConfig2();

        if (!setupEconomy() ) {
            System.out.print("Vault was niet gededecteerd");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        new bankov(this);

        Bukkit.getServer().getPluginManager().registerEvents(new DeurMaken(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DeurOvervallen(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnMove(), this);

        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        console.sendMessage(Logger.color("&f----------------------------------------"));
        console.sendMessage(Logger.color(""));
        console.sendMessage(Logger.color("&6Plugin wordt aangezet..."));
        console.sendMessage(Logger.color(""));
        console.sendMessage(Logger.color("&6Coded by&e: Rivzer"));
        console.sendMessage(Logger.color(""));
        console.sendMessage(Logger.color("&f----------------------------------------"));

    }

    public static Economy getEconomy() {
        return econ;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
