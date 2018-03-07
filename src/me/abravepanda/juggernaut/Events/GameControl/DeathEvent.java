package me.abravepanda.juggernaut.Events.GameControl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.abravepanda.juggernaut.Main;
import me.abravepanda.juggernaut.Managers.PlayerManager;
import me.abravepanda.juggernaut.Managers.ScoreboardsManager;
import me.abravepanda.juggernaut.Utils.GameProgress;
import net.md_5.bungee.api.ChatColor;

public class DeathEvent implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		
		if(Main.instance.gameManager.getGameStatus() == GameProgress.INPROGRESS || Main.instance.gameManager.getGameStatus() == GameProgress.STARTING) {
			if(Main.instance.playersInGame.contains(p)) {
				PlayerManager pm = Main.playerManager.get(p);
				pm.setLives(pm.getLives() - 1);
				
				Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " has just died... They now have " + pm.getLives() + " lives.");
				
				if(pm.getLives() == 0) {
					//end players game
				} else {
					//continue
					
					switch (pm.getLives()) {
					
					case 29:
						break;
					case 28:
						break;
					case 27:
						break;
					
					}
					
				}
				
				ScoreboardsManager.scoreGame(p);
				
			}
		}
		
	}

}
