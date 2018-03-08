package me.abravepanda.juggernaut;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.abravepanda.juggernaut.Command.KitsCommand;
import me.abravepanda.juggernaut.Events.JoinEvent;
import me.abravepanda.juggernaut.Events.LeaveEvent;
import me.abravepanda.juggernaut.Events.GameControl.DeathEvent;
import me.abravepanda.juggernaut.Managers.GameManager;
import me.abravepanda.juggernaut.Managers.PlayerManager;
import me.abravepanda.juggernaut.Utils.Messages;
import net.md_5.bungee.api.ChatColor;



public class Main extends JavaPlugin {
	
	public static Main instance;
	public GameManager gameManager;
	
	public static HashMap<Player, PlayerManager> playerManager = new HashMap<Player, PlayerManager>();
	public ArrayList<Player> playersInGame = new ArrayList<Player>();
	public ArrayList<Player> playersLeftGame = new ArrayList<Player>();
	
	@Override
	public void onEnable() {
		
		gameInformationStartup();
		
		instance = this;
		gameManager = new GameManager();
		
		registerEvents();
		registerConfig();
		registerCommands();
		
		configChecks();
		
	}
	
	@Override
	public void onDisable() {
		
		gameInformationQuit();
		
	}
	
	public void registerConfig() {
		saveDefaultConfig();
	}
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
		this.getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
		this.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
	}
	
	public void registerCommands() {
		this.getCommand("juggernaut").setExecutor(new KitsCommand());
	}
	
	public void gameInformationStartup() {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		
		console.sendMessage("");
		console.sendMessage(ChatColor.GOLD + "-----------------------------------");
		console.sendMessage(ChatColor.GREEN + "Starting the Juggernaut Game (" + this.getDescription().getVersion() + ")");
		console.sendMessage("");
		console.sendMessage(ChatColor.GREEN + "Plugin Created By A_Brave_Panda");
		console.sendMessage(ChatColor.GOLD + "-----------------------------------");
		console.sendMessage("");
	}
	
	public void gameInformationQuit() {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		
		console.sendMessage("");
		console.sendMessage(ChatColor.GOLD + "-----------------------------------");
		console.sendMessage(ChatColor.RED + "Disabling the Juggernaut Game (" + this.getDescription().getVersion() + ")");
		console.sendMessage("");
		console.sendMessage(ChatColor.RED + "Plugin Created By A_Brave_Panda");
		console.sendMessage(ChatColor.GOLD + "-----------------------------------");
		console.sendMessage("");
	}
	
	public void configChecks() {
		
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		
		console.sendMessage(ChatColor.GOLD + "-----------------------------------");
		console.sendMessage(ChatColor.GREEN + "Config Checks for the Juggernaut Game");
		console.sendMessage("");
		console.sendMessage(ChatColor.GREEN + "Checking if Tiers are evenly divisible by Lives...");
		if(Messages.livesCount % Messages.tiersIncrement == 0) {
			console.sendMessage(ChatColor.DARK_GREEN + "Success!");
			console.sendMessage(ChatColor.GOLD + "-----------------------------------");
		} else {
			console.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + Messages.livesCount + " is not evenly divisilbe by " + Messages.tiersIncrement + ".");
			console.sendMessage(" ");
			console.sendMessage(ChatColor.RED + "Disabling Plugin...");
			this.getServer().getPluginManager().disablePlugin(this);
		}
		
		
	}
	
	public File getKitsFile() {

		File kitsFile = new File(getDataFolder() + File.separator + "TierKits", "Kits.yml");
		return kitsFile;
	}

}
