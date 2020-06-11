package com.uicias.homeplugin.commands;

import com.uicias.homeplugin.HomePlugin;
import com.uicias.homeplugin.manager.HomePlayer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/*
This file HomeCommand is part of a project HomesPlugin.
It was created on 05/06/2020 at 20:47 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from HomesPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class HomeCommand implements CommandExecutor, TabCompleter {

    public HomeCommand() {

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        String homeName = "";
        Location loc;
        Player player = (Player) commandSender;
        HomePlayer pl = HomePlugin.homePlugin.getHomePlayer(player);
        int nbHomes = pl.getHomes().size();

        if(strings.length > 0){
            homeName = strings[0];
        }
        else{
            player.sendMessage("§7Mes homes : §3" + pl.getHomes().keySet().toString());
            return true;
        }

        loc = pl.getHomes().get(homeName);

        if(loc == null){
            player.sendMessage("§cPas de home pour ce nom...");
            return false;
        }

        player.teleport(loc);
        player.sendMessage("§8You've been teleported.");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player)commandSender;
        HomePlayer pl = HomePlugin.homePlugin.getHomePlayer(player);

        ArrayList<String> ret = new ArrayList<>(pl.getHomes().keySet());

        return ret;
    }
}
