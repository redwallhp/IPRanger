package io.github.redwallhp.ipranger;

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


}
