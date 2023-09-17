package uk.rythefirst.ki;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import uk.rythefirst.ki.commands.Ki;
import uk.rythefirst.ki.internal.FileManager;
import uk.rythefirst.ki.internal.GameManager;
import uk.rythefirst.ki.internal.MapManager;
import uk.rythefirst.ki.internal.PlayerManager;

public class Main extends JavaPlugin {

	public static PlayerManager pm;

	public static GameManager gm;

	public static MapManager mm;

	public static FileManager fm;

	public static Plugin instance;

	@Override
	public void onEnable() {

		instance = this;

		this.saveDefaultConfig();

		fm = new FileManager();

		mm = new MapManager();

		gm = new GameManager();

		pm = new PlayerManager();

		this.getCommand("ki").setExecutor(new Ki());

	}

	@Override
	public void onDisable() {

	}

}
