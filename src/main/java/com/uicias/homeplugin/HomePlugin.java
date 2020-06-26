package com.uicias.homeplugin;

import com.uicias.homeplugin.commands.*;
import com.uicias.homeplugin.listeners.Listeners;
import com.uicias.homeplugin.manager.HomePlayer;
import com.uicias.homeplugin.utils.DbUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/*
This file HomePlugin is part of a project HomesPlugin.
It was created on 05/06/2020 at 19:44 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from HomesPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class HomePlugin extends JavaPlugin {

    public static HomePlugin homePlugin;
    public static DbUtil dbUtil;
    private List<HomePlayer> players;
    private Map<String, Location> warps;

    @Override
    public void onEnable(){
        homePlugin = this;
        this.players = new ArrayList<>();

        getServer().getLogger().info("Ajout des commandes");
        Objects.requireNonNull(getServer().getPluginCommand("home")).setExecutor(new HomeCommand());
        Objects.requireNonNull(getServer().getPluginCommand("sethome")).setExecutor(new SethomeCommand());
        Objects.requireNonNull(getServer().getPluginCommand("delhome")).setExecutor(new DelhomeCommand());
        Objects.requireNonNull(getServer().getPluginCommand("warp")).setExecutor(new WarpCommand());
        Objects.requireNonNull(getServer().getPluginCommand("setwarp")).setExecutor(new SetWarpCommand());
        Objects.requireNonNull(getServer().getPluginCommand("delwarp")).setExecutor(new DelWarpCommand());
        getServer().getLogger().info("Ajout des commandes DONE");
        getServer().getLogger().info("Ajout des listeners");
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getServer().getLogger().info("Ajout des listeners DONE");
        getServer().getLogger().info("Ajout de la BDD");
        dbUtil = DbUtil.getInstance();
        getServer().getLogger().info("Ajout de la BDD DONE");
        getServer().getLogger().info("Ajout du/des craft customs");
        getServer().addRecipe(new ShapedRecipe(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE)).shape("GGG", "GAG", "GGG").setIngredient('G', Material.GOLD_BLOCK).setIngredient('A', Material.GOLDEN_APPLE));
        getServer().getLogger().info("Ajout du/des craft customs DONE");
        getServer().getLogger().info("Ajout des warps");
        this.warps = new HashMap<>();
        try{
            PreparedStatement ps = dbUtil.preparedStatement("SELECT * FROM Warps");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String[] location = rs.getString("loc").split(";");
                this.warps.put(rs.getString("name"), new Location(HomePlugin.homePlugin.getServer().getWorld(location[0]), Double.parseDouble(location[1]), Double.parseDouble(location[2]), Double.parseDouble(location[3]), Float.parseFloat(location[4]), Float.parseFloat(location[5])));
                getLogger().info("  -> Ajout du warp : " + rs.getString("name"));
            }
            if(!rs.isClosed())
                rs.close();
            if(!ps.isClosed())
                ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        getServer().getLogger().info("Ajout des warps DONE");

    }

    public List<HomePlayer> getPlayers(){ return this.players; }

    public HomePlayer getHomePlayer(Player p){ return this.players.stream().filter(pl -> pl.getPlayer().getUniqueId() == p.getUniqueId()).findFirst().orElse(null); }

    @Override
    public void onDisable(){
        try{
            dbUtil.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            getServer().getLogger().severe("[SQL] An internal error occurred while closing the connection to the database..");
        }
    }

    public Map<String, Location> getWarps() { return this.warps;}

}
