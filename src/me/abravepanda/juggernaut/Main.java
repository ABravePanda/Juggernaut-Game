package me.abravepanda.juggernaut;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.abravepanda.juggernaut.Events.JoinEvent;
import me.abravepanda.juggernaut.Events.LeaveEvent;
import me.abravepanda.juggernaut.Events.GameControl.DeathEvent;
import me.abravepanda.juggernaut.Managers.GameManager;
import me.abravepanda.juggernaut.Managers.PlayerManager;
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
		
	}
	
	@Override
	public void onDisable() {
		
		gameInformationQuit();
		
	}
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
		this.getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
		this.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
	}
	
	public void gameInformationStartup() {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		
		console.sendMessage(ChatColor.GREEN + "-----------------------------------");
		console.sendMessage(ChatColor.GREEN + "Starting the Juggernaut Game (" + this.getDescription().getVersion() + ")");
		console.sendMessage("");
		console.sendMessage(ChatColor.GREEN + "Plugin Created By A_Brave_Panda");
		console.sendMessage(ChatColor.GREEN + "-----------------------------------");
	}
	
	public void gameInformationQuit() {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		
		console.sendMessage(ChatColor.RED + "-----------------------------------");
		console.sendMessage(ChatColor.RED + "Disabling the Juggernaut Game (" + this.getDescription().getVersion() + ")");
		console.sendMessage("");
		console.sendMessage(ChatColor.RED + "Plugin Created By A_Brave_Panda");
		console.sendMessage(ChatColor.RED + "-----------------------------------");
	}

}
