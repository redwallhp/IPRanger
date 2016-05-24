package io.github.redwallhp.ipranger;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;


public class PlayerListener implements Listener {


    private IPRanger plugin;


    public PlayerListener(IPRanger plugin) {
        this.plugin = plugin;
        plugin.getProxy().getPluginManager().registerListener(plugin, this);
    }


    @EventHandler
    public void onPlayerJoin(final PostLoginEvent event) {

        String name = event.getPlayer().getName();
        String ip = event.getPlayer().getAddress().getHostString();

        if (matchIp(ip)) {
            try {
                String message = plugin.getConfig().getMessage();
                MCBouncerRequest req = new MCBouncerRequest(plugin.getConfig().getApiKey());
                req.ban(event.getPlayer(), message);
                event.getPlayer().disconnect(new TextComponent("Banned: " + message));
                plugin.getLogger().info(String.format("Banned %s (%s): %s", name, ip, message));
                plugin.staffBroadcast(String.format("User %s has been automatically banned (%s)", name, message));
            } catch (APIException ex) {
                plugin.getLogger().warning(String.format("Error banning user %s: %s", name, ex.getMessage()));
            }
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
