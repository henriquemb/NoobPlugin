package com.github.henriquemb.noobplugin;

import lombok.Data;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Data
public class Model {
    public void broadcastMessage(String msg) {
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(processMessage(msg)));
    }

    public void sendMessage(Player player, String msg) {
        player.sendMessage(processMessage(msg));
    }

    private String processMessage(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
