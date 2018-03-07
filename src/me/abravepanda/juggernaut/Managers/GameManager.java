package me.abravepanda.juggernaut.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import me.abravepanda.juggernaut.Main;
import me.abravepanda.juggernaut.Utils.GameProgress;
import net.md_5.bungee.api.ChatColor;

public class GameManager {
	
	public static GameProgress gameStatus;
	public static int playersNeeded = 2;
	
	public int taskId;
	public int lobbyCountdown = 10;
	
	
	public void lobbyWait(Player p) {
		
		int online = Bukkit.getOnlinePlayers().size();
	
		Bukkit.getServer().broadcastMessage(ChatColor.GREEN + p.getName() + ChatColor.GRAY + " has joined the game." + ChatColor.GREEN + "" + ChatColor.BOLD + " (" + online + "/" + playersNeeded + ")");
	
		onlineCheck(online);
		
		
		
	}
	public boolean onlineCheck(int online) {
		if(online >= playersNeeded) {
			if(this.getGameStatus() != GameProgress.STARTING && this.getGameStatus() != GameProgress.INPROGRESS) {
				this.setGameStatus(GameProgress.STARTING);
				startCountdown();
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void startCountdown() {
		BukkitScheduler scheduler = Main.instance.getServer().getScheduler();
		taskId = scheduler.scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			
			@Override
			public void run() {
				if(lobbyCountdown > 0) {
					onlineCheck(Main.instance.playersInGame.size());
					if(onlineCheck(Main.instance.playersInGame.size()) == true) {
						lobbyCountdown = lobbyCountdown - 1;
						Bukkit.getServer().broadcastMessage("§eThe game will start in §a" + (lobbyCountdown + 1) + " §eseconds.");
						for(Player online : Bukkit.getOnlinePlayers()) {
							online.playSound(online.getLocation(), Sound.BLOCK_NOTE_PLING, 2, 2);
							online.setLevel((lobbyCountdown + 1));
						}
					} else {
						Bukkit.getServer().broadcastMessage("§c" + playersNeeded + "§e until game start.");
						Bukkit.getServer().getScheduler().cancelTask(taskId);
						lobbyCountdown = 10;
					}
					
					
				} else {
					Bukkit.getServer().getScheduler().cancelTask(taskId);	
					Bukkit.getServer().broadcastMessage("§eGood luck! The game is starting!");
					startGame();
				}
			}
		}, 0, 20L);
		
	}
	
	public void startGame() {
		this.setGameStatus(GameProgress.INPROGRESS);
		
		for(Player onlinePlayers : Main.instance.playersInGame) {
			PlayerManager pm = Main.playerManager.get(onlinePlayers);
			pm.setLives(30);
			ScoreboardsManager.scoreGame(onlinePlayers);
		}
	}
	
	public void setGameStatus(GameProgress type) {
		GameManager.gameStatus = type;
	}
	
	public GameProgress getGameStatus() {
		return GameManager.gameStatus;
	}
	
}
