package SuperName.Plugin.Commands;


import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import SuperName.Plugin.Main;
import SuperName.Plugin.Utils.Utils;

public class GiveMeOp implements CommandExecutor {
	
	private Main plugin;


	public GiveMeOp(Main pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	
		if (sender instanceof Player) { //Check if sender is console or player
			Player player = (Player) sender; //When I know that it is a player create a player varible to use
			
			

			

			if(player.isOp() == false) {

			
			if (!(plugin.getConfig().getStringList("OPlist").contains(player.getName()))) {
			player.sendMessage(Utils.chat("&cSorry, " + player.getName() + " you're not on the op list."));
			return true;
		}
			else {
				if(plugin.getConfig().getBoolean("Extra_secure_mode") == false) {
				Bukkit.getConsoleSender().sendMessage(Utils.chat("&6&l!{&4&lWARNING&6&l}! " + player.getName() + " &4&lJUST GOT OPPED BY /GIVEMEOP")); //Print in console to make sure it was authorized
				player.setOp(true); //Give player op
				player.sendMessage(Utils.chat("&a&lYou have been succesfuly opped. Enjoy!")); //Send opped player a message saying he got opped
				return true;
			} else {
				List<String> OPlist = plugin.getConfig().getStringList("OPlist");
				for(String op : OPlist) {
					if(Bukkit.getOnlinePlayers().contains(op)) {
				Bukkit.getServer().getPlayer(op).sendMessage(op);
					}
				return true;
			}

		}
			}
			}
		else {
			player.sendMessage(Utils.chat("&6" + player.getDisplayName() +  "&2, you're already opped!")); //If player is op then send message saying he is op
			return true;
		}

	}
		Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&4&lCannot run from console!"));
		return true;
	}
}