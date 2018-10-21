package SuperName.Plugin;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import SuperName.Plugin.Commands.AddOP;
import SuperName.Plugin.Commands.GiveMeOp;
import SuperName.Plugin.Commands.OPlist;
import SuperName.Plugin.Commands.ReloadConfig;
import SuperName.Plugin.Commands.RemoveOP;
import SuperName.Plugin.Commands.clearoplist;
import SuperName.Plugin.Commands.ogma;
import SuperName.Plugin.Commands.ogmc;
import SuperName.Plugin.Commands.ogms;
import SuperName.Plugin.Commands.ogmsp;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		System.out.println("OPtest started."); //Print in console that plugin has been enabled
		
		
		
		registerConfig(); //Update the config (if the user has changed it) so other classes can get updated information
		
		getCommand("GiveMeOp").setExecutor(new GiveMeOp(this));
		getCommand("OPlist").setExecutor(new OPlist(this));
		getCommand("AddOP").setExecutor(new AddOP(this));
		getCommand("ClearOpList").setExecutor(new clearoplist(this));
		getCommand("ogmsp").setExecutor(new ogmsp(this));
		getCommand("ogma").setExecutor(new ogma(this));
		getCommand("ogms").setExecutor(new ogms(this));
		getCommand("ogmc").setExecutor(new ogmc(this));
		getCommand("RemoveOP").setExecutor(new RemoveOP(this));
		getCommand("ReloadConfig").setExecutor(new ReloadConfig(this));
	}
	
	    void registerConfig() { //Function to register the edited config
			saveDefaultConfig();
			File file = new File(getDataFolder(), "config.yml");
			
			if(!file.exists()) {
				getConfig().options().copyDefaults(true);
				saveConfig();
			}
		
	}

	public void onDisable() { //Print in console that plugin has been disabled
		System.out.println("OPtest has been disabled.");
	}
}
