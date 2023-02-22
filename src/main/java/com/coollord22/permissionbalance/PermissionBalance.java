package com.coollord22.permissionbalance;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.logging.Level;

public class PermissionBalance extends JavaPlugin {
    public HashMap<String, BigDecimal> balLimit;

    @Override
    public void onEnable() {
        loadConfig();
        registerListener();
        this.getCommand("permissionbalance").setExecutor(this);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("permissionbalance") && args.length > 0) {
            if(args[0].equalsIgnoreCase("reload") && sender.hasPermission("permissionbalance.admin.reload")) {
                this.reloadConfig();
                loadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[&aPermissionBalance&f] &aSuccessfully reloaded config!"));
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    private void loadConfig() {
        balLimit = new HashMap<>();
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        if(config.contains("bal-limit")) {
            if(config.getConfigurationSection("bal-limit").getKeys(false).size() > 0)  {
                for(String key : config.getConfigurationSection("bal-limit").getKeys(false)) {
                    BigDecimal balance = new BigDecimal(config.getString("bal-limit." + key), MathContext.DECIMAL128);
                    balLimit.put(key, balance);
                }
            }
        }
    }

    private void registerListener() {
        Bukkit.getPluginManager().registerEvents(new Methods(this), this);
    }
}
