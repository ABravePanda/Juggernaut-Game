package me.abravepanda.juggernaut;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.abravepanda.juggernaut.Events.JoinEvent;
import me.abravepanda.juggernaut.Events.LeaveEvent;
import me.abravepanda.juggernaut.Managers.GameManager;
import me.abravepanda.juggernaut.Managers.PlayerManager;



public class Main extends JavaPlugin {
	
	public static Main instance;
	public GameManager gameManager;
	
	public static HashMap<Player, PlayerManager> playerManager = new HashMap<Player, PlayerManager>();
	public ArrayList<Player> playersInGame = new ArrayList<Player>();
	public ArrayList<Player> playersLeftGame = new ArrayList<Player>();
	
	@Override
	public void onEnable() {
		
		instance = this;
		gameManager = new GameManager();
		
		registerEvents();
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
		this.getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
	}

}
