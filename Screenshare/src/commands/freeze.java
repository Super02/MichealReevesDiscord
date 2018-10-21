package commands;





import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import commands.main;
import files.ConfigManager;
import utils.Utils;

public class freeze implements CommandExecutor, Listener {
    private main main;
    private ConfigManager cfgm;
    public freeze(main main, ConfigManager cfgm) {
        this.main = main;
        this.cfgm = cfgm;
    }


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
    	if(sender.hasPermission("Screenshare.freeze")) {
    		if(args.length >= 2 && (args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("on"))) {
        if (!(args.length == 0 || Bukkit.getPlayerExact(args[0]) == null)) {
        		Player target = Bukkit.getServer().getPlayer(args[0]);
        		if(args[1].equalsIgnoreCase("off")) {
        			if(!(main.frozen.contains(target))) {
        				sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Messages.On_Player_already_off").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
        				return true;
        			}
        			main.joinedss.remove(target);
        			main.frozen.remove(target);
                    sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.On_UnFreeze").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
                    target.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Un_Freeze").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
                    return true;
        			
        		} else if(args[1].equalsIgnoreCase("on")) {
        			if(main.frozen.contains(target)) {
        				sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Messages.On_Player_already_on").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
        				return true;
        			}
        			if(target.hasPermission("Screenshare.bypass")) {
        				sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Messages.Player_Bypassing").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
        				return true;
        			} else {
        			main.frozen.add(target);
                    target.setAllowFlight(false);
                    sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.On_Freeze").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
                    target.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Freeze").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
                    Countdown(target);
        		}
        		}
        		 
        	}
            sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Specify_Player").replace("{USER}", sender.getName())));
            return true;
        } else if(args.length == 1) {
        if(!(Bukkit.getPlayerExact(args[0]) == null)) {
        
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (!(main.frozen.contains(target))) {
        	if(target.hasPermission("Screenshare.bypass")) {
				sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Messages.Player_Bypassing").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
				return true;
			} else {
            main.frozen.add(target);
            target.setAllowFlight(false);
            sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.On_Freeze").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
            target.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Freeze").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
            Countdown(target);
    		return true;
			}
        } else {
        	main.joinedss.remove(target);
            main.frozen.remove(target);
            sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.On_UnFreeze").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
            target.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Un_Freeze").replace("{USER}", sender.getName()).replace("{TARGET}", args[0])));
            return true;
        }
    } else {
    	sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Specify_Player").replace("{USER}", sender.getName())));
    	return true;
    }
        }
    		sender.sendMessage(Utils.chat(cfgm.getMessages().getString("Prefix")) + Utils.chat(main.getConfig().getString("Messages.Specify_Player").replace("{USER}", sender.getName())));
    		return true;
    	}
		return true;
    }




   @EventHandler
    public void move(PlayerMoveEvent e)
    {
	   if(main.frozen.contains(e.getPlayer())) {
        Location from=e.getFrom();
        Location to=e.getTo();
        double x=Math.floor(from.getX());
        double z=Math.floor(from.getZ());
        if(Math.floor(to.getX())!=x||Math.floor(to.getZ())!=z)
        {
            x+=.5;
            z+=.5;
            e.getPlayer().teleport(new Location(from.getWorld(),x,from.getY(),z,from.getYaw(),from.getPitch()));
        }
    }
    }
     
    HashMap<String, Long> moveTimes = new HashMap<String, Long>();
    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        Player p = e.getPlayer();
        if(main.frozen.contains(p)) {
        if(moveTimes.containsKey(p.getName())) {
            long oldTime = moveTimes.get(p.getName());
            long newTime = System.currentTimeMillis();
            if((newTime - oldTime) > (5 * 1000)) {
                moveTimes.remove(p.getName());
            }
        }
        else {
            moveTimes.put(p.getName(), System.currentTimeMillis());
            for (String op: main.getConfig().getStringList("Freeze_Message")) {
                p.sendMessage(Utils.chat(op)
                		.replace("{USER}", p.getName())
                		.replace("{DISPLAYNAME}", p.getDisplayName())
                		.replace("{UUID}", p.getUniqueId().toString())
                		);
            }
        }
    }
    	}
    @EventHandler
    public void onPlayerItemDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (main.frozen.contains(p)) {
            e.setCancelled(true);

        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (main.frozen.contains(p)) {
            e.setCancelled(true);
        }

    }
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if((!(main.cmdexec.contains((p.getPlayer()))))) { //If time is done running run next
        	if (main.frozen.contains(p) && main.getConfig().getBoolean("ExecuteCommandOnLeave") == true) { //If player is frozen and Execute command on leave is true run next
        		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), main.getConfig().getString("Command_On_Leave").replace("{USER}", p.getName()).replace("{DISPLAY_NAME}", p.getDisplayName()));
        		main.frozen.remove(p.getPlayer());
        }
    }
    }
    @EventHandler
    public void onHit(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (main.frozen.contains(e.getEntity())) {
                e.setCancelled(true);
            }
        }

    }
    @EventHandler
    public void PickupItem(PlayerPickupItemEvent e) {
        if (main.frozen.contains(e.getPlayer())) {
            e.setCancelled(true);
        }

    }
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
        	if (main.frozen.contains(e.getDamager())) {
            e.setCancelled(true);
        }
    	}
    }
}