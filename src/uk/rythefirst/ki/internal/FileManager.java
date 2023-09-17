package uk.rythefirst.ki.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import uk.rythefirst.ki.Main;

public class FileManager {

	// Misc static values.

	private static Plugin pl = Main.instance;
	private static String reg = "#~#";

	// End of misc static values.

	// All static values to do with the wins list.

	private static File WinsFile = new File(pl.getDataFolder() + "/wins.yml");

	private static YamlConfiguration WinsCNF;

	private static TreeMap<String, Integer> winsmap = new TreeMap<String, Integer>();

	// End of wins static values.

	public FileManager() {

		// Load the wins list into memory.

		List<String> winslst = null;

		if (!(WinsFile.exists())) {
			try {
				WinsFile.createNewFile();
				WinsCNF = YamlConfiguration.loadConfiguration(WinsFile);
				WinsCNF.set("data", new ArrayList<String>());
				winslst = new ArrayList<String>();
			} catch (IOException e) {
				Bukkit.getLogger().log(Level.SEVERE, "Failed to create Wins file!");
			}
		} else {
			WinsCNF = YamlConfiguration.loadConfiguration(WinsFile);
			winslst = WinsCNF.getStringList("data");
		}

		if (!(winslst == null) && !(winslst.size() == 0)) {
			for (int i = 0; i < winslst.size(); i++) {
				String current = winslst.get(i);
				String[] strsplit = current.split(reg);
				winsmap.put(strsplit[0], Integer.parseInt(strsplit[1]));
			}
		}

	}

}
