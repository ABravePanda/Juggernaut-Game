package me.abravepanda.juggernaut.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import me.abravepanda.juggernaut.Main;
import me.abravepanda.juggernaut.Utils.GameProgress;
import me.abravepanda.juggernaut.Utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class GameManager {
	
	public static GameProgress gameStatus;
	public static int playersNeeded = Messages.playerQuota;
	
	public int taskId;
	public int taskId2;
	public int lobbyCountdown = Messages.countdownTime;
	
	
	public void lobbyWait(Player p) {
		
		int online = Bukkit.getOnlinePlayers().size();
		
		String joinMessage = Messages.joinMessage.replace("%player%", p.getName()).replace("%online%", Main.instance.playersInGame.size() + "").replace("%minimum%",GameManager.playersNeeded + "");
	
		Bukkit.getServer().broadcastMessage(joinMessage);
	
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
						
						String lobbyCountdownMsg = Messages.lobbyCountdownMessage.replace("%time%", (lobbyCountdown + 1) + "").replace("%online%", Main.instance.playersInGame.size() + "");
						
						Bukkit.getServer().broadcastMessage(lobbyCountdownMsg);
						
						for(Player online : Bukkit.getOnlinePlayers()) {
							
							String lobbyTitle = Messages.lobbyTitle.replace("%time%", (lobbyCountdown + 1) + "").replace("%player%", online.getName()).replace("%online%", Main.instance.playersInGame.size() + "");
							String lobbySubTitle = Messages.lobbySubtitle.replace("%time%", (lobbyCountdown + 1) + "").replace("%player%", online.getName()).replace("%online%", Main.instance.playersInGame.size() + "");
							
							online.sendTitle(lobbyTitle, lobbySubTitle);
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
					Bukkit.getServer().broadcastMessage(Messages.gameStartMessage);
					
					for(Player online : Bukkit.getOnlinePlayers()) {
						online.sendTitle(Messages.gameStartTitle, Messages.gameStartSubtitle);
						online.playSound(online.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 2, 1);
						online.setLevel(0);
					}
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
			pm.setInGame(true);
		}
	}
	
	public void setGameStatus(GameProgress type) {
		GameManager.gameStatus = type;
	}
	
	public GameProgress getGameStatus() {
		return GameManager.gameStatus;
	}
	
}
