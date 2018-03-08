package me.abravepanda.juggernaut.Managers;

import java.util.UUID;

public class PlayerManager {

	private UUID uuid;
	private boolean inGame;
	private int lives;
	private int kills;
	private int tier;
	private boolean spectator;
	
	public PlayerManager(UUID uuid, boolean inGame, int lives, int tier, int kills, boolean spectator) {
		this.setUuid(uuid);
		this.setLives(lives);
		this.setTier(tier);
		this.setKills(kills);
		this.setInGame(inGame);
		this.setSpectator(spectator);
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public boolean isInGame() {
		return inGame;
	}
	
	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public boolean isSpectator() {
		return spectator;
	}

	public void setSpectator(boolean spectator) {
		this.spectator = spectator;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}
	
	
	
}
