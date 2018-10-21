package SuperName.Plugin.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import SuperName.Plugin.Main;
import SuperName.Plugin.Utils.Utils;

public class ReloadConfig implements CommandExecutor{
	private Main plugin;
	
	public ReloadConfig (Main pl) {
		plugin = pl;
}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("OPtest.ReloadConfig")) {
			sender.sendMessage("Reloading config...");
			plugin.reloadConfig();
			sender.sendMessage(Utils.chat("&aReload completed succesful!"));
			return true;
		
	}
		sender.sendMessage("No permission");
		return true;
	}
}
