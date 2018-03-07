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
		
		if(GameManager.gameStatus == GameProgress.INPROGRESS || GameManager.gameStatus == GameProgress.STARTING) {
			Main.playerManager.put(p, new PlayerManager(p.getUniqueId(), false, 0, true));
		} else {
			Main.playerManager.put(p, new PlayerManager(p.getUniqueId(), true, 30, false));
			Main.instance.playersInGame.add(p);
			Main.instance.gameManager.lobbyWait(p);
		}
		
	}
	
}
