package com.uicias.homeplugin.commands;

import com.uicias.homeplugin.HomePlugin;
import com.uicias.homeplugin.manager.HomePlayer;
import com.uicias.homeplugin.utils.DbUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
This file SetHomeCommand is part of a project HomesPlugin.
It was created on 05/06/2020 at 20:47 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from HomesPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class SethomeCommand implements CommandExecutor {

    final int MAX_HOMES = 10;

    public SethomeCommand() {

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;
        HomePlayer pl = HomePlugin.homePlugin.getHomePlayer(player);

        int nbHomes = pl.getHomes().size();

        if(strings.length == 0){
            player.sendMessage("§cEuhhhhh tu seras gentil de me mettre un nom ton home =)");
            return false;
        }

        if(!pl.getHomes().containsKey(strings[0])){
            try{
                
                if(nbHomes + 1 > MAX_HOMES){
                    player.sendMessage("§cTu peux pas creer ce home car tu dépasses déjà la limite...");
                    return false;
                }

                PreparedStatement ps = DbUtil.getInstance().preparedStatement("INSERT INTO Homes( `name`, `loc`, `uuid`) VALUES(?, ?, ?)");
                ps.setString(1, strings[0]);
                ps.setString(2, player.getLocation().getWorld().getName() + ";" + player.getLocation().getX() + ";" + player.getLocation().getY() + ";" + player.getLocation().getZ() + ";" + player.getLocation().getYaw() + ";" + player.getLocation().getPitch());
                ps.setString(3, player.getUniqueId().toString());
                ps.executeUpdate();

                pl.getHomes().put(strings[0], player.getLocation());
                player.sendMessage("§7Home ajouté : §8" + strings[0]);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            try{
                PreparedStatement ps = DbUtil.getInstance().preparedStatement("UPDATE Homes SET `loc` = ? WHERE name = ? AND uuid = ?");
                ps.setString(1, player.getLocation().getWorld().getName() + ";" + player.getLocation().getX() + ";" + player.getLocation().getY() + ";" + player.getLocation().getZ() + ";" + player.getLocation().getYaw() + ";" + player.getLocation().getPitch());
                ps.setString(2, strings[0]);
                ps.setString(3, player.getUniqueId().toString());
                ps.executeUpdate();

                pl.getHomes().remove(strings[0]);
                pl.getHomes().put(strings[0], player.getLocation());
                player.sendMessage("§7Home modifié : §8" + strings[0]);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }



        return true;
    }
}
