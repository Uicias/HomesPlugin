package com.uicias.homeplugin.manager;

import com.uicias.homeplugin.HomePlugin;
import com.uicias.homeplugin.utils.DbUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/*
This file HomePlayer is part of a project HomesPlugin.
It was created on 06/06/2020 at 19:59 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from HomesPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class HomePlayer {

    private Player player;
    private Map<String, Location> homes;

    public HomePlayer(Player pl){

        this.player = pl;
        this.homes = new HashMap<>();

        try{

            PreparedStatement ps = DbUtil.getInstance().preparedStatement("SELECT * FROM Homes WHERE uuid = ?");
            ps.setString(1, pl.getUniqueId().toString());

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String[] location = rs.getString("loc").split(";");
                this.homes.put(rs.getString("name"), new Location(HomePlugin.homePlugin.getServer().getWorld(location[0]), Double.parseDouble(location[1]), Double.parseDouble(location[2]), Double.parseDouble(location[3]), Float.parseFloat(location[4]), Float.parseFloat(location[5])));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void onDisconnect(){
        HomePlugin.homePlugin.getPlayers().remove(this);
    }

    public Player getPlayer() {return player;}

    public Map<String, Location> getHomes(){return this.homes;}

}
