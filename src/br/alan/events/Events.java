package br.alan.events;

import br.alan.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

import static br.alan.Main.color;
import static br.alan.Main.pl;

public class Events implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        final Player p = e.getEntity().getPlayer();
        final Player k = p.getKiller();

        p.sendMessage(color(String.valueOf(Main.pl.prefix) + "&dVoce foi morto por &5" + k.getName()));

        Main.pl.back.put(p, p.getLocation());

        k.sendMessage(color(String.valueOf(Main.pl.prefix) + "&dVoce matou &5" + p.getName()));
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) pl, new Runnable() {
            public void run() {
                p.performCommand("spawn");
                k.performCommand("spawn");

                p.sendMessage(Main.color(String.valueOf(Main.pl.prefix) + "&dTeleportado para &5Spawn"));
                k.sendMessage(Main.color(String.valueOf(Main.pl.prefix) + "&dteleportado para &5Spawn"));

                p.getInventory().clear();
                p.getInventory().setArmorContents(null);
                k.getInventory().clear();
                k.getInventory().setArmorContents(null);
                Player[] arrayOfPlayer;
                int j = (arrayOfPlayer = Bukkit.getServer().getOnlinePlayers()).length;
                for (int i = 0; i < j; i++) {

                    Player online = arrayOfPlayer[i];
                    p.showPlayer(online);
                    k.showPlayer(online);
                }
                p.setGameMode(GameMode.ADVENTURE);
                Main.pl.playing.remove(p);
                Main.pl.playing.remove(k);
            }
        }, 120);
        e.getDrops().clear();
    }


    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        Player k = p.getKiller();

        p.teleport(Main.pl.back.get(p));
        p.setGameMode(GameMode.CREATIVE);
    }


    @EventHandler
    public void onPlayerMode(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (Main.pl.freeze.contains(p.getName())) {
            p.teleport(e.getFrom());
        }
    }
}
