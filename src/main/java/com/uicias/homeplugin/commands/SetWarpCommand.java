package com.uicias.homeplugin.commands;

import com.uicias.homeplugin.HomePlugin;
import com.uicias.homeplugin.utils.DbUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
This file SetWarpCommand is part of a project HomesPlugin.
It was created on 25/06/2020 at 01:40 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from HomesPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class SetWarpCommand implements CommandExecutor {

    public SetWarpCommand() {
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String... strings) {

        if (commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage("Seul un joueur peut creer un home !");
            return false;
        }

        Player player = (Player) commandSender;

        if(!player.isOp()){
            player.sendMessage("§cYou need to be §4OPPED§c to use that command");
            return false;
        }

        if(strings.length == 0){
            player.sendMessage("§7Merci de préciser un nom pour le warp !");
            return false;
        }

        if (!HomePlugin.homePlugin.getWarps().containsKey(strings[0])) {
            try {

                PreparedStatement ps = DbUtil.getInstance().preparedStatement("INSERT INTO Warps( `name`, `loc`) VALUES(?, ?)");
                ps.setString(1, strings[0]);
                ps.setString(2, player.getLocation().getWorld().getName() + ";" + player.getLocation().getX() + ";" + player.getLocation().getY() + ";" + player.getLocation().getZ() + ";" + player.getLocation().getYaw() + ";" + player.getLocation().getPitch());
                ps.executeUpdate();

                HomePlugin.homePlugin.getWarps().put(strings[0], player.getLocation());
                player.sendMessage("§7Warp ajouté : §8" + strings[0]);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            try {
                PreparedStatement ps = DbUtil.getInstance().preparedStatement("UPDATE Warps SET `loc` = ? WHERE name = ?");
                ps.setString(1, player.getLocation().getWorld().getName() + ";" + player.getLocation().getX() + ";" + player.getLocation().getY() + ";" + player.getLocation().getZ() + ";" + player.getLocation().getYaw() + ";" + player.getLocation().getPitch());
                ps.setString(2, strings[0]);
                ps.executeUpdate();

                HomePlugin.homePlugin.getWarps().remove(strings[0]);
                HomePlugin.homePlugin.getWarps().put(strings[0], player.getLocation());
                player.sendMessage("§7Warp modifié : §8" + strings[0]);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return true;
    }

}
