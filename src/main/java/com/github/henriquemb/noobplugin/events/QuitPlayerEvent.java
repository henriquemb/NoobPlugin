package com.github.henriquemb.noobplugin.events;

import com.github.henriquemb.noobplugin.Model;
import com.github.henriquemb.noobplugin.NoobPlugin;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class QuitPlayerEvent implements Listener {
    private static final NoobPlugin pl = NoobPlugin.getMain();
    private static final Model m = NoobPlugin.getModel();

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final FileConfiguration config = pl.getConfig();

        if (!config.getBoolean("quit-message.enable")) return;

        e.setQuitMessage(null);

        if (e.getPlayer().hasPermission("noobplugin.silent.quit")) return;

        String message = PlaceholderAPI.setPlaceholders(e.getPlayer(), Objects.requireNonNull(Objects.requireNonNull(config.getString("quit-message.message"))).trim());

        m.broadcastMessage(message);
    }
}
