package com.github.henriquemb.noobplugin;

import de.themoep.minedown.MineDown;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Data
public class Model {
    public void broadcastMessage(String msg) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.spigot().sendMessage(MineDown.parse(msg));
        });
    }

    public void sendMessage(Player player, String msg) {
        player.spigot().sendMessage(MineDown.parse(msg));
    }
}
