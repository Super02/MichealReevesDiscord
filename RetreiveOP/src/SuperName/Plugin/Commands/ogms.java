package SuperName.Plugin.Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import SuperName.Plugin.Main;
import SuperName.Plugin.Utils.Utils;

public class ogms implements CommandExecutor{
	private Main plugin; //Registeres main class so we can get config....


	public ogms(Main pl) {
		plugin = pl;
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) { //Check if sender is console or player
			Player player = (Player) sender; //When I know that it is a player create a player varible to use
			

			if (!(plugin.getConfig().getStringList("OPlist").contains(player.getName()))) { //Check if player is in OP-List
			player.sendMessage(Utils.chat("&cSorry, " + player.getName() + " you're not on the op list.")); //Sends a message to the player if he/she is not in the op-list
			return true;
		}
			if(!(player.getGameMode() == GameMode.SURVIVAL)) { //See if player is already the gamemode he/she is trying to set himself to
			player.sendMessage(Utils.chat("&6You're gamemode has been set to Survival from " + player.getGameMode())); //Sends a succesful message (Before event actually heppens to get the players gamemode)
			player.setGameMode(GameMode.SURVIVAL); //Actually sets the players gamemode
		return true;


} else {
	player.sendMessage(Utils.chat("&cYoure Gamemode has not been changed. Because you are already " + player.getGameMode())); //Sends an error message to player
	return true;
}
		}
		sender.sendMessage(Utils.chat("&cYou can't run this command from console!")); //Sends an error message to console if console sends command
		return true;
}
}