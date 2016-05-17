package io.github.redwallhp.ipranger;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;


public class Config {


    private IPRanger plugin;
    private Configuration configuration;


    public Config(IPRanger plugin) {
        this.plugin = plugin;
        copyDefault();
        load();
    }


    public String getMessage() {
        return configuration.getString("message", "Compromised account");
    }


    public List<String> getIps() {
        return configuration.getStringList("ips");
    }


    private void copyDefault() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
            File file = new File(plugin.getDataFolder(), "config.yml");
            if (!file.exists()) {
                try (InputStream in = plugin.getResourceAsStream("config.yml")) {
                    Files.copy(in, file.toPath());
                } catch (IOException ex) {
                    plugin.getLogger().info("Error copying default configuration.");
                    ex.printStackTrace();
                }
            }
        }
    }


    public void load() {
        try {
            File file = new File(plugin.getDataFolder(), "config.yml");
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (Exception ex) {
            plugin.getLogger().info("Error loading configuration.");
            ex.printStackTrace();
        }
    }


    public void save() {
        try {
            File file = new File(plugin.getDataFolder(), "config.yml");
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        } catch (Exception ex) {
            plugin.getLogger().info("Error saving configuration.");
            ex.printStackTrace();
        }
    }


}
