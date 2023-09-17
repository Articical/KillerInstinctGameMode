package uk.rythefirst.ki.internal;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Utils {

	public static String MessageTitle = ChatColor.translateAlternateColorCodes('&', "&4&lKiller &6&lInstinct");

	public static void BroadcastCentral(String[] Message) {

		for (Player p : Bukkit.getOnlinePlayers()) {

			for (int i = 0; i < Message.length; i++) {
				Messages.sendCenteredMessage(p, Message[i]);
			}

		}

	}

}
