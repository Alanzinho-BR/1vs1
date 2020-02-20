package br.alan.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Random implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
        if(!(s instanceof Player)){
            s.sendMessage("§cApenas jogadores podem usar esse comando!");
            return true;
        }

        Player p = (Player) s;



        return false;
    }
}
