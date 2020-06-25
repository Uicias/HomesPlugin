package com.uicias.homeplugin.commands;

import com.uicias.homeplugin.HomePlugin;
import com.uicias.homeplugin.utils.DbUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
This file DelWarpCommand is part of a project HomesPlugin.
It was created on 25/06/2020 at 01:56 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from HomesPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class DelWarpCommand implements CommandExecutor, TabCompleter {

    public DelWarpCommand(){}

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String... strings){

        Player player = (Player) commandSender;

        if(!player.isOp()){
            player.sendMessage("§cYou need to be §4OPPED§c to use that command");
            return false;
        }

        if(strings.length > 0){

            if(HomePlugin.homePlugin.getWarps().containsKey(strings[0])){
                try{
                    PreparedStatement ps = DbUtil.getInstance().preparedStatement("DELETE FROM `Warps` WHERE name = ?");
                    ps.setString(1, strings[0]);
                    ps.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                HomePlugin.homePlugin.getWarps().remove(strings[0]);
                player.sendMessage("§7Warp : §b" + strings[0] + " §7supprimé avec succès.");
            }
            else{
                player.sendMessage("§7Ce warp n'existe pas !");
                return false;
            }
        }
        else{
            player.sendMessage("§7Merci de préciser un nom pour le warp.");
            return false;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return new ArrayList<>(HomePlugin.homePlugin.getWarps().keySet());
    }
}
