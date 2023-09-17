package uk.rythefirst.ki.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.ki.Main;

public class GUIs {

	TreeMap<Player, ItemStack> pheadmap = new TreeMap<Player, ItemStack>();

	public HashMap<Player, Inventory> killVotingGUIs = new HashMap<Player, Inventory>();

	public void loadHeads(List<Player> players) {
		new BukkitRunnable() {

			@Override
			public void run() {
				TreeMap<Player, ItemStack> pmap = new TreeMap<Player, ItemStack>();

				for (Player player : players) {
					ItemStack is = new ItemStack(Material.PLAYER_HEAD);

					SkullMeta meta = (SkullMeta) is.getItemMeta();
					meta.setOwningPlayer(player);
					meta.setDisplayName(ChatColor.GOLD + player.getName());

					is.setItemMeta(meta);

					pmap.put(player, is);
				}

				new BukkitRunnable() {

					@Override
					public void run() {

						for (Map.Entry<Player, ItemStack> entry : pmap.entrySet()) {

							pheadmap.put(entry.getKey(), entry.getValue());

						}

						Bukkit.getLogger().log(Level.INFO, "Player heads loaded.");

					}

				}.runTask(Main.instance);

			}

		}.runTaskAsynchronously(Main.instance);
	}

	public void loadKillVotingGUIs(List<Player> Playing, List<Player> Dead) {

		for (Player player : Playing) {
			Inventory in = Bukkit.createInventory(null, 18,
					"" + ChatColor.DARK_RED + "Vote for who you wish to Kill off!");

			for (Map.Entry<Player, ItemStack> entry : pheadmap.entrySet()) {
				if (!(entry.getKey() == player)) {
					in.addItem(entry.getValue());
				}
			}

			ItemStack confirmBtn = new ItemStack(Material.GREEN_CONCRETE);

			ItemMeta confirmmeta = confirmBtn.getItemMeta();

			confirmmeta.setDisplayName(ChatColor.GREEN + "Cast Vote");

			killVotingGUIs.put(player, in);

		}

	}

}
