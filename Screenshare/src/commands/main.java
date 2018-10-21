
package commands;


import java.io.File;
import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import files.ConfigManager;





public class main extends JavaPlugin {
	private ConfigManager cfgm;
	
	  public ArrayList<Player> frozen = new ArrayList<>();
	  public ArrayList<Player> cmdexec = new ArrayList<>();
	  public ArrayList<Player> joinedss = new ArrayList<>();
	  public ArrayList<Player> staff = new ArrayList<>();
		
		public void onEnable() {
			loadConfigManager();
			File file = new File(getDataFolder(), "config.yml");
			
			if(!file.exists()) {
				saveDefaultConfig();
			}
		
		getServer().getPluginManager().registerEvents(new freeze(this, cfgm), this);
		getCommand("freeze").setExecutor(new freeze(this, cfgm));
		getCommand("joinedss").setExecutor(new joinedss(this, cfgm));
		getCommand("unfreeze").setExecutor(new unfreeze(this, cfgm));
		getCommand("joinedsslist").setExecutor(new joinedsslist(this, cfgm));
		getCommand("sslist").setExecutor(new screensharedlist(this, cfgm));
		System.out.println("ScreenShare Advanced started");
	}
	public void onDisable() {
		System.out.println("Plugin disabled");
	}
	
	
	
	
	
	public void loadConfigManager(){
		cfgm = new ConfigManager();
		cfgm.setup();
		cfgm.reloadMessages();
		
	}
}
