package com.uicias.homeplugin.listeners;

import com.uicias.homeplugin.HomePlugin;
import com.uicias.homeplugin.manager.HomePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/*
This file listeners is part of a project HomesPlugin.
It was created on 06/06/2020 at 20:04 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from HomesPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class Listeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        HomePlugin.homePlugin.getPlayers().add(new HomePlayer(e.getPlayer()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        try{
            HomePlugin.homePlugin.getHomePlayer(e.getPlayer()).onDisconnect();
        }
        catch(NullPointerException npe){
            HomePlugin.homePlugin.getLogger().info("The leaving player was not found.");
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e){
        if(e.getEntityType() == EntityType.CREEPER)
            e.blockList().clear();
    }

    @EventHandler
    public void onEntityGrief(EntityBreakDoorEvent e){
        if(e.getEntity() instanceof Zombie){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        e.getEntity().sendMessage("§7Lieu de la mort : §8Monde:" + e.getEntity().getLocation().getWorld().getName() +  " §8X:" +(int) e.getEntity().getLocation().getX() + " Y: " + (int)e.getEntity().getLocation().getY() + " Z: " + (int)e.getEntity().getLocation().getZ());
    }

}
