package com.github.henriquemb.noobplugin.events;

import com.github.henriquemb.noobplugin.Model;
import com.github.henriquemb.noobplugin.NoobPlugin;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class FirstJoinEvent implements Listener {
    private static final Model m = NoobPlugin.getModel();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        final FileConfiguration config = NoobPlugin.getMain().getConfig();

        if (!config.getBoolean("noob-message.enable")) return;

        e.setJoinMessage(null);

        if (p.hasPermission("noobplugin.silent.join")) return;
        if (e.getPlayer().hasPlayedBefore()) return;

        final String role = Objects.requireNonNull(config.getString("noob-message.role")).trim();
        final String duration = Objects.requireNonNull(config.getString("noob-message.duration")).trim();
        final String message = PlaceholderAPI.setPlaceholders(p, Objects.requireNonNull(Objects.requireNonNull(config.getString("noob-message.message"))).trim());

        if (p.hasPermission(String.format("group.%s", role))) return;

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                String.format("lp user %s parent addtemp %s %s", p.getDisplayName(), role, duration)
        );

        m.broadcastMessage(message.replace("%player%", p.getDisplayName()));
    }
}
