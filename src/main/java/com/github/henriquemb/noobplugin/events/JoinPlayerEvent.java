package com.github.henriquemb.noobplugin.events;

import com.github.henriquemb.noobplugin.Model;
import com.github.henriquemb.noobplugin.NoobPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class JoinPlayerEvent implements Listener {
    private static final Model m = NoobPlugin.getModel();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        final FileConfiguration config = NoobPlugin.getMain().getConfig();

        if (!config.getBoolean("join-message.enable")) return;

        e.setJoinMessage(null);

        if (p.hasPermission("noobplugin.silent.join")) return;
        if (!e.getPlayer().hasPlayedBefore()) return;

        m.broadcastMessage(Objects.requireNonNull(Objects.requireNonNull(config.getString("join-message.message")).replace("%player%", p.getDisplayName())).trim());
    }
}
