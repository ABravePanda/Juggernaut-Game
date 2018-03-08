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
import me.abravepanda.juggernaut.Utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class DeathEvent implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Player killer = p.getKiller();
		
		if(Main.instance.gameManager.getGameStatus() == GameProgress.INPROGRESS || Main.instance.gameManager.getGameStatus() == GameProgress.STARTING) {
			if(Main.instance.playersInGame.contains(p)) {
				
				PlayerManager pm = Main.playerManager.get(p);
				pm.setLives(pm.getLives() - 1);
				
				PlayerManager killerm = Main.playerManager.get(killer);
				
				if(killerm != null) {
				killerm.setKills(killerm.getKills() + 1);
				ScoreboardsManager.scoreGame(killer);
				Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " was killed by " + killer.getName() + ".");
				}
				
				
				
				
				//set players inv 
				
				if(pm.getLives() == 0) {

					Main.instance.playersInGame.remove(p);
					pm.setSpectator(true);
					
					// TODO: setup the spectator
					
				} else {
					
					if(pm.getLives() % Messages.tiersIncrement == 0) {
						p.sendMessage(ChatColor.GREEN + "Tier Upgraded!");
						pm.setTier(pm.getTier() + 1);
						ScoreboardsManager.scoreGame(p);
					}
					
					
					
				}
				
				ScoreboardsManager.scoreGame(p);
				
			}
		}
		
	}

}
