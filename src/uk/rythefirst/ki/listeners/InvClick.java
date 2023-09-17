package uk.rythefirst.ki.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import uk.rythefirst.ki.Main;
import uk.rythefirst.ki.internal.GUIs;
import uk.rythefirst.ki.internal.GameManager;

public class InvClick implements Listener {

	@EventHandler
	public void invclick(InventoryClickEvent e) {

		GameManager gm = Main.gm;
		GUIs GuiManager = gm.GuiManager;

		if (!(GuiManager.killVotingGUIs.containsKey(e.getWhoClicked()))) {
			return;
		}

		if (e.getInventory() == GuiManager.killVotingGUIs.get(e.getWhoClicked())) {

			if (e.getCurrentItem().getType() == Material.PLAYER_HEAD) {
				// SkullMeta sm = (SkullMeta) e.getCurrentItem().getItemMeta();
				// Player voted = (Player) sm.getOwningPlayer();
			}

			e.setCancelled(true);
		}

	}

}
