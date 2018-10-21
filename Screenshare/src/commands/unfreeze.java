package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import files.ConfigManager;
import utils.Utils;

public class unfreeze implements CommandExecutor{
    private main main;
    public unfreeze(main main, ConfigManager cfgm) {
        this.main = main;
        this.cfgm = cfgm;
    }
    private ConfigManager cfgm;
    
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if(sender.hasPermission("Screenshare.unfreeze")) {
    	if (args.length >= 0 && Bukkit.getPlayerExact(args[0]) != null) {
    		Player target = Bukkit.getServer().getPlayer(args[0]);
    		if(!(main.frozen.contains(target))) {
    		} else {
    			main.joinedss.remove(target);
                main.frozen.remove(target);
                sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.On_UnFreeze").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
                target.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Un_Freeze").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
                return true;
    		}
    	} else {
    		sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Specify_Player").replace("{USER}", sender.getName())));
    		return true;
    	}
    	return true;
    		}
		return true;
    		}
}