package io.github.redwallhp.ipranger;

import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;


public class PlayerListener implements Listener {


    private IPRanger plugin;


    public PlayerListener(IPRanger plugin) {
        this.plugin = plugin;
        plugin.getProxy().getPluginManager().registerListener(plugin, this);
    }


    @EventHandler
    public void onPlayerJoin(final LoginEvent event) {

        String name = event.getConnection().getName();
        String ip = event.getConnection().getAddress().getHostString();

        if (matchIp(ip)) {
            String cmd = String.format("ban %s %s", name, plugin.getConfig().getMessage());
            plugin.getProxy().getPluginManager().dispatchCommand(plugin.getProxy().getConsole(), cmd);
            plugin.getLogger().info(String.format("Banned %s (%s): %s", name, ip, plugin.getConfig().getMessage()));
        }

    }


    private boolean matchIp(String ip) {
        String[] splitIp = ip.split("\\.");
        for (String pat : plugin.getConfig().getIps()) {
            String[] splitPat = pat.split("\\.");
            int count = 0;
            for (int i = 0; i < splitIp.length; i++) {
                if (splitPat[i].equals(splitIp[i]) || splitPat[i].equals("*")) {
                    count++;
                }
            }
            if (count > 3) return true;
        }
        return false;
    }


}
