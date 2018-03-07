package me.abravepanda.juggernaut.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.abravepanda.juggernaut.Main;
import me.abravepanda.juggernaut.Managers.GameManager;
import me.abravepanda.juggernaut.Managers.PlayerManager;
import me.abravepanda.juggernaut.Utils.GameProgress;

public class JoinEvent implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		//checks if the game is started or in progress
		if(GameManager.gameStatus == GameProgress.INPROGRESS || GameManager.gameStatus == GameProgress.STARTING) {
			//if so, add player as spectator
			Main.playerManager.put(p, new PlayerManager(p.getUniqueId(), false, 0, 0, true));
		} else {
			//if not, add the player to the game and update lobby count
			Main.playerManager.put(p, new PlayerManager(p.getUniqueId(), true, 30, 0, false));
			Main.instance.playersInGame.add(p);
			Main.instance.gameManager.lobbyWait(p);
		}
		
	}
	
}
