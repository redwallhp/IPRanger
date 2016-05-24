package io.github.redwallhp.ipranger;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;


public class ReloadCommand extends Command {


    private IPRanger plugin;


    public ReloadCommand(IPRanger plugin) {
        super("ipranger-reload", "ipranger.reload");
        this.plugin = plugin;
        plugin.getProxy().getPluginManager().registerCommand(plugin, this);
    }


    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        plugin.getConfig().load();
        commandSender.sendMessage(new TextComponent("Config reloaded"));
    }

}
