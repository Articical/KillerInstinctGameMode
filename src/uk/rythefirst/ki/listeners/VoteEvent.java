package uk.rythefirst.ki.listeners;

import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.ki.events.onVoteEvent;
import uk.rythefirst.ki.internal.GameManager;
import uk.rythefirst.ki.voting.Vote;

public class VoteEvent implements Listener {

	GameManager gm;

	public void onVote(onVoteEvent e) {

		if (!(gm.currentVote == null)) {
			Vote CurrentVote = gm.currentVote;
			CurrentVote.CastVote(e.whoVoted(), e.votedFor());
		}

		e.whoVoted().closeInventory();
		e.whoVoted().sendTitle(ChatColor.DARK_RED + "Killer Instinct",
				ChatColor.GOLD + "You've voted: " + e.votedFor().getName(), 1, 5, 1);
	}

}
