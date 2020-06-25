package com.uicias.homeplugin.commands;

import com.uicias.homeplugin.HomePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/*
This file WarpCommand is part of a project HomesPlugin.
It was created on 25/06/2020 at 02:03 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from HomesPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class WarpCommand implements CommandExecutor, TabCompleter {

    public WarpCommand(){}

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;

        if(strings.length == 0){
            player.sendMessage("§7Warps : §8" + HomePlugin.homePlugin.getWarps().keySet().toString());
            return true;
        }
        else{
            if(HomePlugin.homePlugin.getWarps().containsKey(strings[0])){

                HomePlugin.homePlugin.getServer().getScheduler().runTaskAsynchronously(HomePlugin.homePlugin, () -> {

                    if(!HomePlugin.homePlugin.getWarps().get(strings[0]).getChunk().isLoaded())
                        HomePlugin.homePlugin.getWarps().get(strings[0]).getChunk().load();

                    HomePlugin.homePlugin.getServer().getScheduler().runTask(HomePlugin.homePlugin, () -> player.teleport(HomePlugin.homePlugin.getWarps().get(strings[0])));

                });
                player.sendMessage("§7Téléportation au warp : §e" + strings[0] + " §7en cours...");
                return true;

            }
            else{
                player.sendMessage("§cAucun warp avec ce nom...");
                return false;
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return new ArrayList<>(HomePlugin.homePlugin.getWarps().keySet());
    }
}
