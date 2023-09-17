package uk.rythefirst.ki.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import uk.rythefirst.ki.Main;
import uk.rythefirst.ki.internal.GameManager;

public class Deaths implements Listener {

	@EventHandler
	public void PDeath(PlayerDeathEvent e) {

		GameManager gm = Main.gm;

		if (gm.isPlaying(e.getEntity())) {

			if (gm.deadPlayerCount() == 0) {
				gm.setSeasonPlayer(e.getEntity());
			}

		}
	}

}
