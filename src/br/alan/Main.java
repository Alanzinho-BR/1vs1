package br.alan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.alan.commands.Commands;
import br.alan.events.Events;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {
    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }


    public static ArrayList<Player> cooldown = new ArrayList<>();
    public static HashMap<Player, Location> back = new HashMap<>();
    public static List<String> duel = new ArrayList<>();
    public static List<String> playing = new ArrayList<>();
    public static List<String> freeze = new ArrayList<>();
    public static String noperm = color("&cSem permissao");
    public static String prefix = color("&8[&5Magic&8] ");
    public static String no = color(String.valueOf(prefix) + "&dVocê não pode fazer isso");
    public static Main pl;

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, (Plugin) this);
        Bukkit.getLogger().info("[1vs1]: carregando config");
        Bukkit.getLogger().info("[1vs1]: Plugin e conf carregada");
        pl = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        registerCommands();
        registerEvents();
    }


    public void onDisable() {
        Bukkit.getLogger().info("[1vs1]: Configuraçao salvada");
        saveConfig();
        Bukkit.getLogger().info("[1vs1]: Configuracao salvada");
    }

    private void registerCommands() {
        getCommand("duel").setExecutor(new Commands());
        getCommand("1v1").setExecutor(new Commands());
        getCommand("accept").setExecutor(new Commands());
    }

    private void registerEvents() {
        PluginManager alan = Bukkit.getPluginManager();
        alan.registerEvents(new Events(), this);

    }
}
