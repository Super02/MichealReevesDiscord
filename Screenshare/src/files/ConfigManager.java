package files;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import commands.main;

public class ConfigManager {

	private main plugin = main.getPlugin(main.class);

	// Files & File Configs Here
	public FileConfiguration messagescfg;
	public File messagesfile;
	// --------------------------

	public void setup() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdirs();
		}

		messagesfile = new File(plugin.getDataFolder(), "messages.yml");

		if (!messagesfile.exists()) {
			plugin.saveResource("messages.yml", false);
		}

		messagescfg = YamlConfiguration.loadConfiguration(messagesfile);
	}

	public FileConfiguration getMessages() {
		return messagescfg;
	}

	public void reloadMessages() {
		messagescfg = YamlConfiguration.loadConfiguration(messagesfile);

	}
}