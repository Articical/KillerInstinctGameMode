package uk.rythefirst.ki.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import uk.rythefirst.ki.Main;
import uk.rythefirst.ki.internal.GUIs;
import uk.rythefirst.ki.internal.GameManager;
import uk.rythefirst.ki.voting.Vote;

public class InventoryClose implements Listener {

	GameManager gm;
	GUIs GUIManager;

	@EventHandler
	public void InvClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		Inventory inv = e.getInventory();
		gm = Main.gm;
		if (gm.currentVote == null) {
			return;
		}
		if (GUIManager.killVotingGUIs.containsKey(p) && inv == GUIManager.killVotingGUIs.get(p)) {
			Vote currentVote = gm.currentVote;
			if (currentVote.hasVoted(p)) {
				return;
			} else {
				p.openInventory(inv);
			}
		}
	}

}
