package me.abravepanda.juggernaut.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.abravepanda.juggernaut.Main;
import me.abravepanda.juggernaut.Managers.GameManager;
import me.abravepanda.juggernaut.Utils.GameProgress;

public class LeaveEvent implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		if(GameManager.gameStatus == GameProgress.INPROGRESS || GameManager.gameStatus == GameProgress.STARTING) {
			if(Main.playerManager.containsKey(p)) {
				Main.playerManager.remove(p);
				Main.instance.playersLeftGame.add(p);
			}
		} else {
			Main.instance.playersLeftGame.add(p);
		}
		
	}
	
}
