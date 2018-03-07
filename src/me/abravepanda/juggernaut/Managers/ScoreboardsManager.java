package me.abravepanda.juggernaut.Managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.abravepanda.juggernaut.Main;

public class ScoreboardsManager {
	
	public static ScoreboardManager m;
	public static Scoreboard b;
	public static Objective o;
	public static Score livesCount;
	public static Score killsCount;
	public static Score livesMsg;
	public static Score killsMsg;
	public static Score serverName;
	public static Score blanktop;
	public static Score blankmid;
	
	public static void scoreGame(Player p) {
		
		m = Bukkit.getScoreboardManager();
		b = m.getNewScoreboard();
		o = b.registerNewObjective("juggernaut", "");
		
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName("§b§lCross Community Games");
		
		serverName = o.getScore("§cThe Juggernaut Game");
		serverName.setScore(6);
		
		blanktop = o.getScore(" ");
		blanktop.setScore(5);
		
		livesMsg = o.getScore("§aLives");
		livesMsg.setScore(4);
		
		livesCount = o.getScore(Main.playerManager.get(p).getLives() + "");
		livesCount.setScore(3);
		
		blankmid = o.getScore("  ");
		blankmid.setScore(2);
		
		killsMsg = o.getScore("§aKills");
		killsMsg.setScore(1);
		
		killsCount = o.getScore(Main.playerManager.get(p).getKills() + "");
		killsCount.setScore(0);
		
		p.setScoreboard(b);
		
	}

}
