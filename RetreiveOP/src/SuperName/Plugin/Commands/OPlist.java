package SuperName.Plugin.Commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import SuperName.Plugin.Main;
import SuperName.Plugin.Utils.Utils;

public class OPlist implements CommandExecutor {
	
	
	private Main plugin;
	
	public OPlist (Main pl) {
		plugin = pl;
}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("OPtest.Oplist")) {
		if (!(sender instanceof Player)) { //Check if player is player or console
			if(!(plugin.getConfig().getStringList("OPlist").isEmpty())); {
				List<String> OPlist = plugin.getConfig().getStringList("OPlist");
				sender.sendMessage(Utils.chat("&aHere is the OPlist: ")); //Sending console version of OPlist
				for(String op : OPlist) {
				sender.sendMessage(Utils.chat("    - &c" + op));
				return true;
			}
				sender.sendMessage(Utils.chat("&aHere is the OPlist: "));
				sender.sendMessage(Utils.chat("&a - The &cOPlist &a is empty"));
				return true;
		}
		}
			else {
			if(!(plugin.getConfig().getStringList("OPlist").isEmpty())); {
			List<String> OPlist = plugin.getConfig().getStringList("OPlist");
			sender.sendMessage(Utils.chat("&aHere is the OPlist: ")); //Sending console version of OPlist
			for(String op : OPlist) {
			sender.sendMessage(Utils.chat("    - &c" + op));
			return true;
			}
			}
				sender.sendMessage(Utils.chat("&a - The &cOPlist &a is empty"));
				return true;
			}
			}
		return true; 
		
			
			
				
		
		

	}
}




