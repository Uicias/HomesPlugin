package com.uicias.homeplugin.commands;

import com.uicias.homeplugin.HomePlugin;
import com.uicias.homeplugin.manager.HomePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/*
This file DelHomeCommand is part of a project HomesPlugin.
It was created on 05/06/2020 at 20:47 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from HomesPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class DelhomeCommand implements CommandExecutor, TabCompleter {

    public DelhomeCommand() {

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;
        HomePlayer pl = HomePlugin.homePlugin.getHomePlayer(player);

        if(strings.length == 0){
            player.sendMessage("§7Nop ! T'as pas mit le nom du home !");
            return false;
        }

        if(!pl.getHomes().containsKey(strings[0])){
            player.sendMessage("§7Nop ! T'as pas de home a ce nom !");
            return false;
        }
        pl.getHomes().remove(strings[0]);
        player.sendMessage("§7Home §3"+ strings[0] + "§7 supprimé !");

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
