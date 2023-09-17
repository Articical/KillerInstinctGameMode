package uk.rythefirst.ki.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import uk.rythefirst.ki.Main;
import uk.rythefirst.ki.voting.Vote;

public class GameManager {

	// Get the PlayerManager instance from the main class.
	PlayerManager pm = Main.pm;

	public GUIs GuiManager = new GUIs();

	// This is used to aid with randomly assiging roles.
	Random Rand;

	// This is used to determine whether all player chats are silenced.
	public Boolean chatSilence = false;

	// Used to determine whether the game is running or not.
	public Boolean isRunning = false;

	// A
	public Vote currentVote = null;

	// Create Integers to allow us to limit certain role player counts.
	Integer currentKillers;
	Integer currentDocs;
	Integer currentSeasons;
	Integer currentDetectives;
	Integer currentHost;

	// Define the Max amount of players certain roles can have.
	Integer MaxKillers = 2;
	Integer MaxDoc = 1;
	Integer MaxSeason = 1;
	Integer MaxDetect = 1;
	Integer MaxHost = 1;

	// Create a List where we can store our currently dead players.
	List<Player> deadPlayers = new ArrayList<Player>();

	// Create Lists to store which players are apart of roles than contain more than
	// one person.
	List<Player> Killers = new ArrayList<Player>();
	List<Player> Innocents = new ArrayList<Player>();
	List<Player> Spect = new ArrayList<Player>();
	List<Player> PlayingNow = new ArrayList<Player>();

	// Create Player instances where we can store who has any roles that only
	// require 1 person.
	public Player Doc = null;
	public Player Season = null;
	public Player Detective = null;
	public Player Host = null;

	// Method to be called when the game begins.
	public void BeginGame() {

		// Store the TreeMap from the player manager instance into a variable for easy
		// use here.
		TreeMap<Player, Integer> pmap = pm.KIPlayers;

		// Create a list of Players who will need their roles to be randomized when role
		// assigning is done.
		ArrayList<Player> randomize = new ArrayList<Player>();

		// Loop through every player in the TreeMap stored above and set their roles
		// accordingly.
		for (Map.Entry<Player, Integer> entry : pmap.entrySet()) {
			// If their assigned role is equal to role "0", add them to the "randomize"
			// list, repeat this while assiging the appropriate roles to players.
			if (entry.getValue() == 0) {
				randomize.add(entry.getKey());
			} else if (entry.getValue() == 2) {
				if (currentKillers < MaxKillers) {
					currentKillers++;
					Killers.add(entry.getKey());
					PlayingNow.add(entry.getKey());
				} else {
					pm.setRole(entry.getKey(), 0);
					PlayingNow.add(entry.getKey());
				}
			} else if (entry.getValue() == 1) {
				Innocents.add(entry.getKey());
				PlayingNow.add(entry.getKey());
			} else if (entry.getValue() == 3) {
				if (currentDocs < MaxDoc) {
					currentDocs++;
					Doc = entry.getKey();
					PlayingNow.add(entry.getKey());
				} else {
					pm.setRole(entry.getKey(), 0);
					PlayingNow.add(entry.getKey());
				}
			} else if (entry.getValue() == 4) {
				if (currentDetectives < MaxDetect) {
					currentDetectives++;
					PlayingNow.add(entry.getKey());
				} else {
					pm.setRole(entry.getKey(), 0);
					PlayingNow.add(entry.getKey());
				}
			} else if (entry.getValue() == 5) {
				if (currentSeasons < MaxSeason) {
					currentSeasons++;
					PlayingNow.add(entry.getKey());
				} else {
					pm.setRole(entry.getKey(), 0);
					PlayingNow.add(entry.getKey());
				}
			} else if (entry.getValue() == 20 && !(currentHost == MaxHost)) {
				Host = entry.getKey();
			} else if (entry.getValue() == 21) {
				Spect.add(entry.getKey());
			} else {
				Spect.add(entry.getKey());
			}
		}

		// If no host is present, fail to start.
		if (Host == null) {
			Bukkit.getLogger().log(Level.SEVERE, "No host has been assigned. Game will not start.");
			String[] M = { Utils.MessageTitle, "&4&lThe game failed to start.", "&4&lNo host has been assigned." };
			Utils.BroadcastCentral(M);
			return;
		}

		GuiManager.loadHeads(PlayingNow);

		isRunning = true;

	}

	// Returns the List of dead players.
	public List<Player> getDeadPlayers() {
		return deadPlayers;
	}

	// Returns the Integer value of the total dead player amount.
	public Integer deadPlayerCount() {
		return deadPlayers.size();
	}

	// Set who the season dead role player is.
	public void setSeasonPlayer(Player p) {
		Season = p;
	}

	// Check if a player is the season dead role.
	public Boolean isSeasonPlayer(Player p) {
		return p == Season;
	}

	// Check if a player is a killer.
	public Boolean isKiller(Player p) {
		return Killers.contains(p);
	}

	// Check if a player is a spectator.
	public Boolean isSpect(Player p) {
		return Spect.contains(p);
	}

	// Check if a player is an innocent.
	public Boolean isInno(Player p) {
		return Innocents.contains(p);
	}

	// Check if a player is the doctor.
	public Boolean isDoc(Player p) {
		return Doc == p;
	}

	// Check if a player is the Detective.
	public Boolean isDetect(Player p) {
		return Detective == p;
	}

	// Check if a player is playing.
	public Boolean isPlaying(Player p) {
		return PlayingNow.contains(p);
	}

	// Add a player to the dead player list.
	public void setDead(Player p) {
		deadPlayers.add(p);
	}

	// Remove a player from the dead player list.
	public void revive(Player p) {
		if (deadPlayers.contains(p)) {
			deadPlayers.remove(p);
		}
	}
}
