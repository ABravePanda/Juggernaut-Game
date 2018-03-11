package me.abravepanda.juggernaut.Events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.abravepanda.juggernaut.Main;
import me.abravepanda.juggernaut.Managers.GameManager;
import me.abravepanda.juggernaut.Managers.PlayerManager;
import me.abravepanda.juggernaut.Utils.GameProgress;
import me.abravepanda.juggernaut.Utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class JoinEvent implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		
		Player p = e.getPlayer();
		
		
		//checks if the game is started or in progress
		if(GameManager.gameStatus == GameProgress.INPROGRESS || GameManager.gameStatus == GameProgress.STARTING) {
			//if so, add player as spectator
			p.setGameMode(GameMode.ADVENTURE);
			
			Main.playerManager.put(p, new PlayerManager(p.getUniqueId(), false, 0, 0, 0, true));
			
			PlayerManager pm = Main.playerManager.get(p);
			
			if(pm.isSpectator()) {
				p.setDisplayName(ChatColor.GRAY + p.getName());
			} else {
				p.setDisplayName(ChatColor.WHITE + p.getName());
			}
			
		} else {
			//if not, add the player to the game and update lobby count
			Main.playerManager.put(p, new PlayerManager(p.getUniqueId(), true, Messages.livesCount, 0, 0, false));
			Main.instance.playersInGame.add(p);
			Main.instance.gameManager.lobbyWait(p);
		}
		
	}
	
}
