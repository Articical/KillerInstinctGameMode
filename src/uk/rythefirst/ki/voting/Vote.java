package uk.rythefirst.ki.voting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.ki.Main;
import uk.rythefirst.ki.events.VoteFinishEvent;
import uk.rythefirst.ki.internal.GUIs;
import uk.rythefirst.ki.internal.GameManager;

public class Vote {

	TreeMap<Player, Player> votes = new TreeMap<Player, Player>();
	TreeMap<Player, Integer> voteCounts = new TreeMap<Player, Integer>();

	Integer count;

	List<Player> votedPlayers = new ArrayList<Player>();
	List<Player> votingPlayers = new ArrayList<Player>();

	GameManager gm;

	GUIs GUIManager;

	public Vote(List<Player> votingPlayers) {

		gm = Main.gm;

		GUIManager = gm.GuiManager;

		GUIManager.loadKillVotingGUIs(votingPlayers, gm.getDeadPlayers());

		this.votingPlayers = votingPlayers;

		for (Player p : votingPlayers) {
			p.openInventory(GUIManager.killVotingGUIs.get(p));
		}

	}

	public void CastVote(Player voter, Player voted) {
		if (!(votes.containsKey(voter))) {
			votes.put(voter, voted);
			votedPlayers.add(voter);
			if (votedPlayers.size() == votingPlayers.size()) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.sendTitle(ChatColor.DARK_RED + "Killer Instinct", ChatColor.GOLD + "All Players have now voted!",
							1, 5, 1);
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					Player votedP = calculateResult();
					VoteFinishEvent vfe = new VoteFinishEvent(1, votedP, count);
					Bukkit.getPluginManager().callEvent(vfe);
				}
			}
		} else {
			voter.closeInventory();
			voter.sendTitle(ChatColor.DARK_RED + "Nice Try!", ChatColor.GOLD + "You've already voted.", 1, 3, 1);
		}
	}

	public Player calculateResult() {
		Player CurrentTop = votingPlayers.get(0);
		for (Player p : votingPlayers) {
			voteCounts.put(p, 0);
		}
		for (Map.Entry<Player, Player> entry : votes.entrySet()) {
			count = voteCounts.get(entry.getValue());
			count++;
			voteCounts.put(entry.getValue(), count);
			if (count > voteCounts.get(CurrentTop)) {
				CurrentTop = entry.getValue();
			}
		}
		return CurrentTop;
	}

	public boolean hasVoted(Player p) {
		return votedPlayers.contains(p);
	}

}
