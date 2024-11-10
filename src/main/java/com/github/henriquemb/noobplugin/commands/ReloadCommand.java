package com.github.henriquemb.noobplugin.commands;

import com.github.henriquemb.noobplugin.Model;
import com.github.henriquemb.noobplugin.NoobPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    private static final NoobPlugin pl = NoobPlugin.getMain();
    private static final Model m = NoobPlugin.getModel();
    final FileConfiguration config = pl.getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player p = (Player) sender;

        if(!p.hasPermission("noobplugin.reload")) {
            m.sendMessage(p, config.getString("no-permission"));
            return true;
        }

        if(args.length == 0) {
            m.sendMessage(p, "&cUsage: /noobplugin reload");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            reload(p);
        }

        return true;
    }

    private void reload(Player p) {
        pl.reloadConfig();

        m.sendMessage(p, "&cNoobPlugin reloaded");
    }
}
