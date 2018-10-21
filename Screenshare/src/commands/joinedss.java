package commands;





import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import commands.main;
import files.ConfigManager;
import utils.Utils;

public class joinedss implements CommandExecutor, Listener {
    private main main;
    public joinedss(main main, ConfigManager cfgm) {
        this.main = main;
        this.cfgm = cfgm;
    }
    private ConfigManager cfgm;
    
    public void Countdown(Player target) {
    	new BukkitRunnable() {
		    public void run() {
		    	if(!(main.joinedss.contains(target)) && main.frozen.contains(target)) {
		    	main.cmdexec.add(target);
		    	Bukkit.dispatchCommand(Bukkit.getConsoleSender(), main.getConfig().getString("CommandOnTimeDone").
            			replace("{USER}", target.getName()).
            			replace("{DISPLAY_NAME}", target.getDisplayName()).
            			replace("{TIME}", main.getConfig().getString("TimeToJoinScreenShare")));
		    	if(main.getConfig().getBoolean("UnFreezePlayerOnTimeDone") == true) {
		    		main.frozen.remove(target);
		    	}
		    	main.joinedss.remove(target);
		    	main.cmdexec.remove(target);
		    }
		    }
		}.runTaskLater(main, Long.valueOf(main.getConfig().getString("TimeToJoinScreenShare")));
}


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if(sender.hasPermission("Screenshare.joinedss")) {
    		if(args.length >= 2 && (args[1].equalsIgnoreCase("false") || args[1].equalsIgnoreCase("true"))) {
        if (!(args.length == 0 || Bukkit.getPlayerExact(args[0]) == null)) {
        		Player target = Bukkit.getServer().getPlayer(args[0]);
        		if(args[1].equalsIgnoreCase("false")) {
        			if(!(main.frozen.contains(target)) || main.joinedss.contains(target)) {
        				if(!(main.frozen.contains(target))) {
        					sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Messages.On_Joinedss_Player_NotFrozen").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
        					return true;
        				} else {
        				sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Messages.On_Joinedss_Already_true").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
        				return true;
        				}
        			}
        			if(!(main.joinedss.contains(target))) {
        				 //TODO Can't remove player not bypassed
        			} else {
        			Countdown(target);
        			main.joinedss.remove(target);
                    sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.On_UnSS").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
                    target.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Un_SS").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
                    return true;
        			}
        			
        		} else if(args[1].equalsIgnoreCase("true")) {
        			if(!(main.frozen.contains(target)) || main.joinedss.contains(target)) {
        				if(!(main.frozen.contains(target))) {
        					sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Messages.On_Joinedss_Player_NotFrozen").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
        					return true;
        				} else {
        				sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Messages.On_Joinedss_Already_true").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
        				return true;
        				}
        			}
        			main.joinedss.add(target);
                    sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.On_JoinedSS").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
                    target.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.JoinedSS").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
        		return true;
        		}
        		 
        	}
        	 //TODO Invalid args
            sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Specify_Player_JoinedSS").replace("{USER}", sender.getName())));
            return true;
        } else if(args.length == 1) {
        if(!(Bukkit.getPlayerExact(args[0]) == null)) {
        
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (!(main.joinedss.contains(target))) {
            main.joinedss.add(target);
            sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.On_JoinedSS").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
            target.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.JoinedSS").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
    		return true;
        } else {
        	Countdown(target);
            main.joinedss.remove(target);
            sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.On_UnSS").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
            target.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Un_SS").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
            return true;
        }
    } else {
    	sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Specify_Player_JoinedSS").replace("{USER}", sender.getName())));
    	return true;
    }
        }
    		sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Specify_Player_JoinedSS").replace("{USER}", sender.getName())));
    		return true;
    	}
		return true;
    }
}