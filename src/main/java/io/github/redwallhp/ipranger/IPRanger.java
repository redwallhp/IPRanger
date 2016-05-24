package io.github.redwallhp.ipranger;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;


public class IPRanger extends Plugin {


    private Config config;


    @Override
    public void onEnable() {
        this.config = new Config(this);
        new PlayerListener(this);
        new ReloadCommand(this);
        super.onEnable();
    }


    public Config getConfig() {
        return config;
    }


    public void staffBroadcast(String message) {
        for (ProxiedPlayer p : getProxy().getPlayers()) {
            if (p.hasPermission("ipranger.notify")) {
                p.sendMessage(new ComponentBuilder(message).color(ChatColor.GREEN).create());
            }
        }
    }


}
