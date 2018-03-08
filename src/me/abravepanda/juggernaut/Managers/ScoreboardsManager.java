package me.abravepanda.juggernaut.Managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.abravepanda.juggernaut.Main;
import me.abravepanda.juggernaut.Utils.Messages;

public class ScoreboardsManager {
	
	public static ScoreboardManager m;
	public static Scoreboard b;
	public static Objective o;
	public static Objective o1;
	public static Score livesCount;
	public static Score killsCount;
	public static Score tiersCount;
	public static Score livesMsg;
	public static Score killsMsg;
	public static Score tiersMsg;
	public static Score serverName;
	public static Score blanktop;
	public static Score blankmid;
	public static Score blankbot;
	
	public static void scoreGame(Player p) {
		
		m = Bukkit.getScoreboardManager();
		b = m.getNewScoreboard();
		o = b.registerNewObjective("juggernaut", "");
		
		o1 = b.registerNewObjective("Tier", "");
		o1.setDisplaySlot(DisplaySlot.BELOW_NAME);
		
		o1.getScore(Main.playerManager.get(p).getTier() + "").setScore(6);
		
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName("§b§lCross Community Games");
		
		serverName = o.getScore("§cThe Juggernaut Game");
		serverName.setScore(6);
		
		blanktop = o.getScore(" ");
		blanktop.setScore(5);
		
		livesMsg = o.getScore("§aLives:§6 " + Main.playerManager.get(p).getLives());
		livesMsg.setScore(4);
		
		blankmid = o.getScore("  ");
		blankmid.setScore(3);
		
		tiersMsg = o.getScore("§aTier:§6 " + Main.playerManager.get(p).getTier());
		tiersMsg.setScore(2);
		
		blankbot = o.getScore("   ");
		blankbot.setScore(1);
		
		killsMsg = o.getScore("§aKills:§6 " + Main.playerManager.get(p).getKills());
		killsMsg.setScore(0);

		
		p.setScoreboard(b);
		
	}

}
