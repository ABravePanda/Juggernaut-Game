package me.abravepanda.juggernaut.Command;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.abravepanda.juggernaut.Main;
import me.abravepanda.juggernaut.Utils.InventoryStringDeSerializer;
import me.abravepanda.juggernaut.Utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class KitsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
		
			if(cmd.getName().equalsIgnoreCase("juggernaut")) {
				
				if(args[0].equalsIgnoreCase("kits")) {
				
					
					if(args[1].equalsIgnoreCase("save")) {
					
						for(int i = 0; i <= Messages.tiersCount; i++) {
							
							if(args[2].equals(i + "")) {
								
								p.sendMessage(ChatColor.GREEN + "Saving Kit with ID: " + args[2]);
								
								File kitsFile = Main.instance.getKitsFile();
								FileConfiguration kitsFileConfig = YamlConfiguration.loadConfiguration(kitsFile);
								
								
								Inventory inv = p.getInventory();
								ItemStack[] contents = inv.getContents();
								String base64Inv = InventoryStringDeSerializer.itemStackArrayToBase64(contents);
								
								kitsFileConfig.set(i + "", base64Inv);
								try {
									kitsFileConfig.save(kitsFile);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								
							}
							
						}
						
					}
					if(args[1].equalsIgnoreCase("load")) {
						for(int i = 0; i <= Messages.tiersCount; i++) {
							
							if(args[2].equals(i + "")) {
								
								p.sendMessage(ChatColor.GREEN + "Loading Kit with ID: " + args[2]);
								
								File kitsFile = Main.instance.getKitsFile();
								FileConfiguration kitsFileConfig = YamlConfiguration.loadConfiguration(kitsFile);
								
								
								Inventory inv = p.getInventory();
								
								String contents = kitsFileConfig.getString(i + "");
								ItemStack[] unbase64Contents = null;
								try {
									unbase64Contents = InventoryStringDeSerializer.itemStackArrayFromBase64(contents);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								inv.setContents(unbase64Contents);
								
								
							} 
							
						}
					}
				}
				
			}
		}
		
		
		return true;
	}

	
	
}
