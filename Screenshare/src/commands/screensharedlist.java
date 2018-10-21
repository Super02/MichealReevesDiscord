package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import files.ConfigManager;
import utils.Utils;


public class screensharedlist implements CommandExecutor{
	
    private main main;
    public screensharedlist(main main, ConfigManager cfgm) {
        this.main = main;
        this.cfgm = cfgm;
    }
    private ConfigManager cfgm;
    
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if(sender.hasPermission("Screenshare.sslist")) {
    	sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Messages.sslist_message").replace("{SENDER}", sender.getName())));
    	for (Player ss: main.frozen) {
    		sender.sendMessage(Utils.chat("&6" + ss.toString()));
    	}
    	if(main.frozen.isEmpty()) {
    		sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Messages.NoOne_IsFrozen_Error").replace("{SENDER}", sender.getName())));
    	}
		return true;
    	}
		return true;
    }
    		}