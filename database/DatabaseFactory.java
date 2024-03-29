package me.SirSlender.ToAInstances.util.database;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DatabaseFactory {
	private final JavaPlugin plugin;
	private final List<Converter> converters = new ArrayList<Converter>();

	public DatabaseFactory(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * Register a new converter.
	 * 
	 * @param converter Converter to register
	 */
	public void registerConverter(Converter converter) {
		converters.add(converter);
	}

	/**
	 * Construct a database
	 * 
	 * @param builder Database configuration
	 * @return New database
	 */
	public Database getDatabase(DatabaseConfigBuilder builder) {
		return new Database(this, builder);
	}

	/**
	 * Generates a default config section with the name of MySQL (defaults to sqlite)
	 */
	public void generateConfigSection() {
		FileConfiguration config = plugin.getConfig();
		config.addDefault("database.StorageType", "YML");
		config.addDefault("database.host", "127.0.0.1");
		config.addDefault("database.port", 3306);
		config.addDefault("database.user", "root");
		config.addDefault("database.password", "INSERT PASSWORD");
		config.addDefault("database.database", plugin.getName());
		config.options().copyDefaults(true);
		plugin.saveConfig();
	}

	protected void doConversion(Database database) {
		for(Converter converter : converters) {
			converter.onDatabaseLoad(database);
		}
	}
}