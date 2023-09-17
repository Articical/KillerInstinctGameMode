package uk.rythefirst.ki.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.ki.Main;
import uk.rythefirst.ki.internal.GameManager;

public class PChat implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {

		GameManager gm = Main.gm;

		Player p = e.getPlayer();

		if (gm.isRunning) {
			if (!(gm.chatSilence)) {
				if (gm.isKiller(p)) {
					for (Player player : Bukkit.getOnlinePlayers()) {
						if (gm.isKiller(player) || player.hasPermission("ki.staff.chat")) {
							player.sendMessage("" + ChatColor.DARK_RED + ChatColor.BOLD + "KILLER " + ChatColor.RESET
									+ p.getName() + " - " + e.getMessage());
						}
					}
				}
			}
		}

	}

}
