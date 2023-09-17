package uk.rythefirst.ki.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.ki.events.VoteFinishEvent;

public class VoteFinish implements Listener {

	@EventHandler
	public void onVoteEnd(VoteFinishEvent e) {

		Player p = e.getResultingPlayer();

		if (e.getType() == 1) {
			p.setHealth(0);
		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendTitle(ChatColor.DARK_RED + p.getName() + " was voted off.",
					ChatColor.GOLD + "They had a total of " + e.getTotalResponses() + " votes.", 1, 5, 1);
		}

	}

}
