package be.rivzer.bankoverval.Commands;

import be.rivzer.bankoverval.Logger;
import be.rivzer.bankoverval.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class bankov implements CommandExecutor {

    private Main plugin;

    public bankov(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("bankov").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Logger.color("&f---------------------"));
            sender.sendMessage(Logger.color("&cDeze commands zijn niet bedoeld voor de console!"));
            sender.sendMessage(Logger.color("&f---------------------"));
            return true;
        }

        Player p = (Player) sender;

        if(args.length == 0){
            if(p.hasPermission("bankov.help")){
                p.sendMessage(Logger.color("&f---------------------"));
                p.sendMessage("");
                p.sendMessage(Logger.color("&6/bankov help &7- &eBekijk de commands."));
                p.sendMessage("");
                p.sendMessage(Logger.color("&6/bankov create &7- &eCreate a bankov deur."));
                p.sendMessage("");
                p.sendMessage(Logger.color("&6/bankov info &7- &eBekijk info over deze plugin."));
                p.sendMessage("");
                p.sendMessage(Logger.color("&f---------------------"));
            }
            return true;
        }
        else if(args[0].equalsIgnoreCase("create")){
            if(p.hasPermission("bankov.create")){
                if(Main.playerbezig.contains(p.getUniqueId())){
                    p.sendMessage(Logger.color("&cU bent al een bankov deur aan het maken"));
                    return true;
                }
                Main.playerbezig.add(p.getUniqueId());
                p.sendMessage(Logger.color("&7Rechter klik nu op een &bdeur &7op deze toe te voegen aan de bankoverval config!"));
            }
        }

        return false;
    }

}
