package SuperName.Plugin.Commands;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import SuperName.Plugin.Main;
import SuperName.Plugin.Utils.Utils;

public class AddOP implements CommandExecutor {
	
	
	private Main plugin;
	
	public AddOP (Main pl) {
		plugin = pl;
}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		ArrayList<String> list = (ArrayList<String>) plugin.getConfig().getStringList("OPlist");
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("OPtest.AddOp")) {
				if(list.contains(player.getName())) {
		if(!(args.length == 0)) { //Check if player has entered a player name
			Player target = Bukkit.getPlayer(args[0]); //Create a target as we now that it he have filled out arg0
			if(!(target == null)) { //Check if player is online/is a plyer
				if(!(list.contains(args[0]))) { //Check if player is already on OPlist
				player.sendMessage(Utils.chat("&aSuccess! &2Succesfuly added " + args[0] + " to the OPlist")); //Send message to command executer saying that command was succesful
				target.sendMessage(Utils.chat("&a&lYou're now on the OPlist!")); //Send a message to opped player saying he is on the op list
				list.add(target.getName()); //Add target to OP-list
				plugin.getConfig().set("OPlist", list); //Save list varible to config
				plugin.saveConfig(); //Save the config
				return true;
				
					
				} else {

					player.sendMessage(Utils.chat("&cPlayer already in OPlist!")); //If player is already in OP list send a error message
					return true;
					}
					
			}
			else{
				player.sendMessage(Utils.chat("&cPlayer not found!")); //If player does not exist send a error message
				return true;
			}
			} else {
		player.sendMessage(Utils.chat("&cYou need to specify a player!")); //If you did not specify a player (Did not include any arguments)
		return true;
		}
		} else {
			player.sendMessage(Utils.chat("&cYou're not on the OP list"));
		}
	
	} else {
		player.sendMessage(Utils.chat("&cError: &a 679"));
		return true;
	}
		return true;

	} else {
		if(!(args.length == 0)) { //Check if player has entered a player name
			Player target = Bukkit.getPlayer(args[0]); //Create a target as we now that it he have filled out arg0
			if(!(target == null)) { //Check if player is online/is a player
				if(!(list.contains(args[0]))) { //Check if player is already on OPlist
				sender.sendMessage(Utils.chat("&aSuccess! &2Succesfuly added " + args[0] + " to the OPlist")); //Send message to command executer saying that command was succesful
				target.sendMessage(Utils.chat("&a&lYou're now on the OPlist!")); //Send a message to opped player saying he is on the op list
				list.add(target.getName()); //Add target to OP-list
				plugin.getConfig().set("OPlist", list); //Save list varible to config
				plugin.saveConfig(); //Save the config
				return true;
				} else {
					sender.sendMessage(Utils.chat("&cPlayer already in OPlist!")); //If player is already in OP list send a error message
					return true;
				}
			} else {
				sender.sendMessage(Utils.chat("&cPlayer not found!")); //If player does not exist send a error message
				return true;
			}
		} else {
			sender.sendMessage(Utils.chat("&cYou need to specify a player!")); //If you did not specify a player (Did not include any arguments)
			return true;
		}
	}
	
}
}





