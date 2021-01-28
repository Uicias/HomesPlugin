package bzh.strawberry.zenplugin.listeners;

import bzh.strawberry.zenplugin.ZenPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;

/*
This file listeners is part of a project HomesPlugin.
It was created on 06/06/2020 at 20:04 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from HomesPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class Listeners implements Listener {

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (e.getEntityType() == EntityType.CREEPER)
            e.blockList().clear();
    }

    @EventHandler
    public void onEntityGrief(EntityBreakDoorEvent e) {
        if (e.getEntity() instanceof Zombie) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.getEntity().sendMessage("§7Lieu de la mort : §8Monde:" + e.getEntity().getLocation().getWorld().getName() + " §8X:" + (int) e.getEntity().getLocation().getX() + " Y: " + (int) e.getEntity().getLocation().getY() + " Z: " + (int) e.getEntity().getLocation().getZ());
    }

    @EventHandler
    public void onPlayerEnterBed(PlayerBedEnterEvent e){
        long time = e.getPlayer().getLocation().getWorld().getTime();
        if(!(time > 0 && time < 12300)){
            e.getPlayer().getLocation().getWorld().setTime(1000);
            for(Player p : Bukkit.getOnlinePlayers())
                p.sendMessage("§7[§3ZenPlugin§7] §8On se réveille ! §e" + e.getPlayer().getName() + "§8 vient de faire passer la nuit.");
        }
    }

}
