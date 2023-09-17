package uk.rythefirst.ki.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.ki.internal.Messages;

public class Ki implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return false;
		}

		Player p = (Player) sender;

		if (args.length < 1) {
			Messages.sendCenteredMessage(p, ChatColor.DARK_RED + "Killer Instinct");
			Messages.sendCenteredMessage(p, ChatColor.DARK_RED + "Season 5");
			Messages.sendCenteredMessage(p, ChatColor.GOLD + "Roles");
			Messages.sendCenteredMessage(p, ChatColor.GOLD + "Butchers - It's your job to kill the lot of them.");
		}

		return false;
	}

}
