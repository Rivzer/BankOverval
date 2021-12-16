package be.rivzer.bankoverval.Utils;

import java.util.Random;

import be.rivzer.bankoverval.Config.Config;
import be.rivzer.bankoverval.Logger;
import be.rivzer.bankoverval.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDownTask extends BukkitRunnable {
    private int time;
    private int seconds;
    private Main plugin;
    private Player player;

    public CountDownTask(Main plugin, Player player) {
        this.plugin = plugin;
        this.time = 9;
        this.seconds = Config.getCustomConfig1().getInt("Settings.LockPickSeconds")-1;
        this.player = player;
    }

    public void run() {
        --this.time;
        if (this.time == -1) {
            this.time = 9;
            --this.seconds;
        }

        if (this.seconds != -1) {
            this.player.sendTitle(Logger.color(Config.getCustomConfig1().getString("Settings.Title")), this.seconds + "." + this.time, 0, 60, 0);
        }

        if (this.seconds == 0 && this.time == 0) {
            Random random = new Random();
            int chance = random.nextInt(101);
            if (chance <= Config.getCustomConfig1().getInt("Settings.Chance")) {
                this.player.sendTitle(Logger.color("&aGelukt!"), "", 1, 60, 15);

            } else {
                this.player.sendTitle(Logger.color("&cMislukt!"), "", 1, 60, 15);
            }

            Main.aanhetlockpicken.remove(player.getUniqueId());
            Main.overvalklaar.put(player.getUniqueId(), 0);
            this.cancel();
        }

    }
}