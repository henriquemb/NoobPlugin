package com.github.henriquemb.noobplugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import com.github.henriquemb.noobplugin.Model;
import com.github.henriquemb.noobplugin.NoobPlugin;
import org.bukkit.entity.Player;

@CommandAlias("noobplugin")
public class ReloadCommand extends BaseCommand {
    private static final NoobPlugin pl = NoobPlugin.getMain();
    private static final Model m = NoobPlugin.getModel();

    @Subcommand("reload") @CommandPermission("noobplugin.reload")
    public void onCommand(Player p) {
        pl.reloadConfig();

        m.sendMessage(p, "&cNoobPlugin reloaded");
    }
}
