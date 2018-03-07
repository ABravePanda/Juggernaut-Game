package me.abravepanda.juggernaut.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.abravepanda.juggernaut.Main;
import me.abravepanda.juggernaut.Managers.GameManager;
import me.abravepanda.juggernaut.Utils.GameProgress;

public class LeaveEvent implements Listener {
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		//checks if game is started
		if(GameManager.gameStatus == GameProgress.INPROGRESS || GameManager.gameStatus == GameProgress.STARTING) {
			//if so, remove the player from active and add them to "left" players.
			if(Main.playerManager.containsKey(p)) {
				Main.playerManager.remove(p);
				Main.instance.playersLeftGame.add(p);
			}
		} else {
			//if not just add them to left players
			Main.instance.playersLeftGame.add(p);
		}
		
	}
	
}
