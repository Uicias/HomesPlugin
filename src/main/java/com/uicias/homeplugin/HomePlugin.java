package com.uicias.homeplugin;

import com.uicias.homeplugin.commands.DelhomeCommand;
import com.uicias.homeplugin.commands.HomeCommand;
import com.uicias.homeplugin.commands.SethomeCommand;
import com.uicias.homeplugin.listeners.Listeners;
import com.uicias.homeplugin.manager.HomePlayer;
import com.uicias.homeplugin.utils.DbUtil;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    @Override
    public void onEnable(){
        homePlugin = this;
        Objects.requireNonNull(getServer().getPluginCommand("home")).setExecutor(new HomeCommand());
        Objects.requireNonNull(getServer().getPluginCommand("sethome")).setExecutor(new SethomeCommand());
        Objects.requireNonNull(getServer().getPluginCommand("delhome")).setExecutor(new DelhomeCommand());
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        dbUtil = DbUtil.getInstance();
        this.players = new ArrayList<>();

    }

    public List<HomePlayer> getPlayers(){ return this.players; }

    public HomePlayer getHomePlayer(Player p){ return this.players.stream().filter(pl -> pl.getPlayer().getUniqueId() == p.getUniqueId()).findFirst().orElse(null); }

}
