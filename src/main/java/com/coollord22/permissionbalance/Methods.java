package com.coollord22.permissionbalance;

import net.ess3.api.events.UserBalanceUpdateEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.math.BigDecimal;
import java.util.Map;

public class Methods implements Listener {
    private final PermissionBalance plugin;

    public Methods(PermissionBalance plugin)  {
        this.plugin = plugin;
    }

    public BigDecimal getBalLimit(final Player player) {
        BigDecimal limit = plugin.balLimit.get("default");

        for (Map.Entry<String, BigDecimal> entry : plugin.balLimit.entrySet()) {
            if(player.hasPermission("permissionbalance.multiple." + entry.getKey()))
                limit = limit.max(entry.getValue());
        }
        return limit;
    }

    @EventHandler
    public void onBalUpdate(UserBalanceUpdateEvent event) {
        BigDecimal maxLimit = getBalLimit(event.getPlayer());
        if(event.getNewBalance().compareTo(maxLimit) > 0) {
            event.setNewBalance(maxLimit);
        }
    }
}
