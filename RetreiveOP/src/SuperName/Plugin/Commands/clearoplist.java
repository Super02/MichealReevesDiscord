package SuperName.Plugin.Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import SuperName.Plugin.Main;
import SuperName.Plugin.Utils.Utils;

public class clearoplist implements CommandExecutor {
	private Main plugin;
	
	public clearoplist (Main pl) {
		plugin = pl;
}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		ArrayList<String> list = (ArrayList<String>) plugin.getConfig().getStringList("OPlist");
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("OPtest.Clearoplist")) {
			player.sendMessage(Utils.chat("&6" + player.getDisplayName() + ", &aGetting ready to clear list.."));
			list.clear();
			plugin.getConfig().set("OPlist", list);
			plugin.saveConfig();
			player.sendMessage(Utils.chat("&aSuccessfuly cleared list"));
		} else {
			player.sendMessage(Utils.chat("&cError: &a110"));
		return true;
	}
		return true;
} else {
	sender.sendMessage(Utils.chat("&6" + "" + ", &cGetting ready to clear list.."));
	list.clear();
	plugin.getConfig().set("OPlist", list);
	plugin.saveConfig();
	sender.sendMessage(Utils.chat("&b&lSuccessfuly cleared list"));
return true;
}
}
}
			
	


