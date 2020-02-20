package br.alan.commands;

import br.alan.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import static br.alan.Main.*;

public class Commands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        final Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("duel")) {

            if (args.length == 0) {
                p.sendMessage(color(String.valueOf(Main.pl.prefix) + "&c/duel <name>"));
            }
            if (args.length == 1) {
                //Impedindo que o player se convide para X1//
                if(args[0].equalsIgnoreCase(p.getDisplayName())){
                    p.sendMessage(Main.pl.no);
                    return true;
                }

                final Player t = Bukkit.getPlayer(args[0]);
                p.sendMessage(color(String.valueOf(Main.pl.prefix) + "&dVoce chamou &5" + t.getName() + " &dpara X1."));
                t.sendMessage(color(String.valueOf(Main.pl.prefix) + "&5" + p.getName() + " &dconvidou você para X1"));
                t.sendMessage(color(String.valueOf(Main.pl.prefix) + "&dUse &5/accept " + p.getName()));
                duel.add(t.getName());
                duel.add(p.getName());
            }
        }
        if (cmd.getName().equalsIgnoreCase("accept")) {

            if (args.length == 0) {
                p.sendMessage(color(String.valueOf(Main.pl.prefix) + "&c/accept <nick>"));
            }
            if (args.length == 1) {

                final Player t = Bukkit.getPlayer(args[0]);
                if (t.equals(p.getName())) {

                    p.sendMessage(Main.pl.no);
                    return true;
                }
                if (duel.contains(t.getName())) {

                    World w = Bukkit.getServer().getWorld(Main.pl.getConfig().getString("pos1.world"));
                    double x = Main.pl.getConfig().getDouble("pos1.x");
                    double y = Main.pl.getConfig().getDouble("pos1.y");
                    double z = Main.pl.getConfig().getDouble("pos1.z");

                    World w1 = Bukkit.getServer().getWorld(Main.pl.getConfig().getString("pos2.world"));
                    double x1 = Main.pl.getConfig().getDouble("pos2.x");
                    double y1 = Main.pl.getConfig().getDouble("pos2.y");
                    double z1 = Main.pl.getConfig().getDouble("pos2.z");

                    p.teleport(new Location(w, x, y, z));
                    t.teleport(new Location(w1, x1, y1, z1));

                    p.getInventory().clear();
                    p.getInventory().setArmorContents(null);
                    p.setHealth(20);
                    p.setFoodLevel(20);

                    t.getInventory().clear();
                    t.getInventory().setArmorContents(null);
                    t.setHealth(20);
                    t.setFoodLevel(20);

                    ItemStack sword = new ItemStack(Material.IRON_SWORD);
                    ItemStack fishingrod = new ItemStack(Material.FISHING_ROD);
                    ItemMeta fishingmeta = fishingrod.getItemMeta();
                    fishingmeta.setDisplayName(color("&fVara de pesca"));
                    fishingrod.addEnchantment(Enchantment.DURABILITY, 3);
                    fishingrod.setItemMeta(fishingmeta);

                    p.getInventory().addItem(sword);
                    p.getInventory().addItem(fishingrod);
                    t.getInventory().addItem(sword);
                    t.getInventory().addItem(fishingrod);

                    p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
                    p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                    p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
                    p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS, 1));

                    t.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
                    t.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                    t.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
                    t.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS, 1));

                    duel.remove(t.getName());
                    duel.remove(p.getName());
                    Main.playing.add(p.getName());
                    Main.playing.add(t.getName());
                    Main.cooldown.add(p);
                    Main.cooldown.add(t);
                    Main.freeze.add(p.getName());
                    Main.freeze.add(t.getName());
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) pl, new Runnable() {
                        public void run() {
                            p.sendMessage(Main.color(String.valueOf(Main.pl.prefix) + "&dComeçando em &55 &dSegundos!"));
                        }
                    }, 20L);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) pl, new Runnable() {
                        public void run() {
                            p.sendMessage(Main.color(String.valueOf(Main.pl.prefix) + "&dComeçando em &54 &dsegundos!"));
                        }
                    }, 40L);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) pl, new Runnable() {
                        public void run() {
                            p.sendMessage(Main.color(String.valueOf(Main.pl.prefix) + "&dComeçando em &53 &dsegundos!"));
                        }
                    }, 60L);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) pl, new Runnable() {
                        public void run() {
                            p.sendMessage(Main.color(String.valueOf(Main.pl.prefix) + "&dComeçando em &52 &dsegundos!"));
                        }
                    }, 80L);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) pl, new Runnable() {
                        public void run() {
                            p.sendMessage(Main.color(String.valueOf(Main.pl.prefix) + "&dComeçando em &51 &dsegundos!"));
                        }
                    }, 100L);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
                        public void run() {
                            Main.cooldown.remove(p);
                            Main.cooldown.remove(t);
                            Main.freeze.remove(p.getName());
                            Main.freeze.remove(t.getName());
                            p.sendMessage(Main.color(String.valueOf(Main.pl.prefix) + "&5Lutem!"));
                        }
                    }, 120L);

                    p.setGameMode(GameMode.ADVENTURE);
                    t.setGameMode(GameMode.ADVENTURE);
                    Player[] arrayOfPlayer;
                    int j = (arrayOfPlayer = Bukkit.getServer().getOnlinePlayers()).length;
                    for (int i = 0; i < j; i++) {
                        Player online = arrayOfPlayer[i];
                        p.hidePlayer(online);
                        t.hidePlayer(online);

                        p.showPlayer(t);
                        t.showPlayer(p);
                    }

                } else {

                    p.sendMessage(Main.no);
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("1v1")) {

            if (args.length == 0) {

                p.sendMessage(color(String.valueOf(Main.pl.prefix) + "&c/1v1 setpos1 &8| &esetar posicao 1"));
                p.sendMessage(color(String.valueOf(Main.pl.prefix) + "&c/1v1 setpos2 &8| &esetar posicao 2"));
                return true;
            }
            if (args.length == 1) {

                if (args[0].equalsIgnoreCase("setpos1")) {
                    if (!p.hasPermission("1v1.setpos1")) {

                        p.sendMessage(Main.pl.noperm);
                        return true;
                    }

                    pl.getConfig().set("pos1.world", p.getWorld().getName());
                    pl.getConfig().set("pos1.x", Double.valueOf(p.getLocation().getX()));
                    pl.getConfig().set("pos1.y", Double.valueOf(p.getLocation().getY()));
                    pl.getConfig().set("pos1.z", Double.valueOf(p.getLocation().getZ()));
                    pl.saveConfig();
                    p.sendMessage(color(String.valueOf(Main.pl.prefix) + "&dPosiçao 1 foi setada!"));
                }
                if (args[0].equalsIgnoreCase("setpos2")) {
                    if (!p.hasPermission("1v1.setpos2")) {

                        p.sendMessage(Main.pl.noperm);
                        return true;
                    }

                    pl.getConfig().set("pos2.world", p.getWorld().getName());
                    pl.getConfig().set("pos2.x", Double.valueOf(p.getLocation().getX()));
                    pl.getConfig().set("pos2.y", Double.valueOf(p.getLocation().getY()));
                    pl.getConfig().set("pos2.z", Double.valueOf(p.getLocation().getZ()));
                    pl.saveConfig();
                    p.sendMessage(color(String.valueOf(Main.pl.prefix) + "&dPosiçao 2 foi setada!"));

                }

            } else {

                p.sendMessage(color(String.valueOf(Main.pl.prefix) + "&dArgumento invalido"));
            }
        }
        return false;
    }
}
