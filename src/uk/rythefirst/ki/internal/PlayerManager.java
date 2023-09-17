package uk.rythefirst.ki.internal;

import java.util.TreeMap;

import org.bukkit.entity.Player;

public class PlayerManager {

	// Roles
	// 0 - Unassigned
	// 1 - Innocent (No special abilities)
	// 2 - Butcher (Max of 2, both have to agree to kill someone)
	// 3 - Doctor (Max of 1, can save a player every night provided they're alive)
	// 4 - Detective (Max of 1, can inspect a person and find out their role, every
	// night)

	// Season Roles
	// 5 - Spy - Can find out how many roles are active.
	// Seer - (First person who dies becomes the seer)

	// Misc Roles
	// 20 - Host
	// 21 - Spectator

	// 100 - No Role

	public TreeMap<Player, Integer> KIPlayers = new TreeMap<Player, Integer>();

	public Player seer;

	// Add a player to have a random role assigned.
	public void addPlayer(Player p) {
		KIPlayers.put(p, 0);
	}

	// Add a player with a set role.
	public void addPlayer(Player p, Integer i) {
		KIPlayers.put(p, i);
	}

	// Set a player's role.
	public void setRole(Player p, Integer i) {
		if (KIPlayers.containsKey(p)) {
			KIPlayers.remove(p);
		}
		KIPlayers.put(p, i);
	}

	// Get a players current role.
	public Integer getRole(Player p) {
		if (KIPlayers.containsKey(p)) {
			return KIPlayers.get(p);
		} else {
			return 100;
		}
	}

}
