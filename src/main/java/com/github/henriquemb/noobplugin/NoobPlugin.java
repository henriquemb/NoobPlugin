package com.github.henriquemb.noobplugin;

import co.aikar.commands.PaperCommandManager;
import com.github.henriquemb.noobplugin.commands.ReloadCommand;
import com.github.henriquemb.noobplugin.events.FirstJoinEvent;
import com.github.henriquemb.noobplugin.events.JoinPlayerEvent;
import com.github.henriquemb.noobplugin.events.QuitPlayerEvent;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoobPlugin extends JavaPlugin {
    @Getter @Setter
    private static Model model;
    @Getter @Setter
    private static NoobPlugin main;
    @Getter @Setter
    private static CommandSender sender;
    @Getter @Setter
    private PaperCommandManager manager;
    @Override
    public void onEnable() {
        setMain(this);
        setModel(new Model());
        setSender(Bukkit.getConsoleSender());
        setManager(new PaperCommandManager(this));

        getMain().getConfig().options().copyDefaults(true);
        getMain().saveConfig();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinPlayerEvent(), this);
        pm.registerEvents(new FirstJoinEvent(), this);
        pm.registerEvents(new QuitPlayerEvent(), this);

        manager.registerCommand(new ReloadCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }
}
