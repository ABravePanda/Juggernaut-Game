package me.abravepanda.juggernaut.Utils;

import org.bukkit.ChatColor;

import me.abravepanda.juggernaut.Main;

public class Messages {
	
	public static int countdownTime = Main.instance.getConfig().getInt("Countdown");
	public static int playerQuota = Main.instance.getConfig().getInt("PlayerQuota");
	
	public static int livesCount = Main.instance.getConfig().getInt("Lives");
	public static int tiersCount = Main.instance.getConfig().getInt("Tiers");
	public static int tiersIncrement = Main.instance.getConfig().getInt("TierIncrement");
	
	public static String joinMessage = ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("JoinMessage"));

	public static String lobbyTitle = ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("LobbyTitle"));
	public static String lobbySubtitle = ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("LobbySubtitle"));
	
	public static String lobbyCountdownMessage = ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("LobbyCountdown"));
	
	public static String gameStartMessage = ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("GameStart"));
	public static String gameStartTitle = ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("GameStartTitle"));
	public static String gameStartSubtitle = ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("GameStartSubtitle"));
	
}
