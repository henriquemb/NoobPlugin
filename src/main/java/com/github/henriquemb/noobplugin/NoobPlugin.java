package com.github.henriquemb.noobplugin;

import com.github.henriquemb.noobplugin.commands.ReloadCommand;
import com.github.henriquemb.noobplugin.events.FirstJoinEvent;
import com.github.henriquemb.noobplugin.events.JoinPlayerEvent;
import com.github.henriquemb.noobplugin.events.QuitPlayerEvent;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoobPlugin extends JavaPlugin {
    @Getter @Setter
    private static Model model;
    @Getter @Setter
    private static NoobPlugin main;
    @Getter @Setter
    private static CommandSender sender;
    @Override
    public void onEnable() {
        setMain(this);
        setModel(new Model());
        setSender(Bukkit.getConsoleSender());

        getMain().getConfig().options().copyDefaults(true);
        getMain().saveConfig();

        /*
         * Register Events
         */
        this.getServer().getPluginManager().registerEvents(new FirstJoinEvent(), this);
        this.getServer().getPluginManager().registerEvents(new JoinPlayerEvent(), this);
        this.getServer().getPluginManager().registerEvents(new QuitPlayerEvent(), this);

        /*
         * Register Commands
         */
        this.getCommand("noobplugin").setExecutor(new ReloadCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }
}
