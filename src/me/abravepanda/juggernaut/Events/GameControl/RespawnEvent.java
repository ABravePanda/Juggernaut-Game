package me.abravepanda.juggernaut.Events.GameControl;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.abravepanda.juggernaut.Main;
import me.abravepanda.juggernaut.Managers.PlayerManager;
import me.abravepanda.juggernaut.Utils.GameProgress;
import me.abravepanda.juggernaut.Utils.InventoryStringDeSerializer;

public class RespawnEvent implements Listener {

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		
		Player p = e.getPlayer();
		
		if(Main.instance.gameManager.getGameStatus() == GameProgress.INPROGRESS) {
		
			if(Main.instance.playersInGame.contains(p)) {
				PlayerManager pm = Main.playerManager.get(p);
				int tier = pm.getTier();
				
				File kitsFile = Main.instance.getKitsFile();
				FileConfiguration kitsFileConfig = YamlConfiguration.loadConfiguration(kitsFile);
				
				
				Inventory inv = p.getInventory();
				
				String contents = kitsFileConfig.getString(tier + "");
				
				if(contents != null) {
				
					ItemStack[] unbase64Contents = null;
					try {
						unbase64Contents = InventoryStringDeSerializer.itemStackArrayFromBase64(contents);
					} catch (IOException er) {
						// TODO Auto-generated catch block
						er.printStackTrace();
					}
					
					inv.setContents(unbase64Contents);
				}
				
				
				
				
				
			}
		
		}
		
	}
	
}
