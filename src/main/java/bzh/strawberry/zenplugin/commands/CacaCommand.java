package bzh.strawberry.zenplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;


/*
This file CacaCommand is part of a project HomesPlugin.
It was created on 27/01/2021 at 22:45 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from ZenPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class CacaCommand implements CommandExecutor {

    public CacaCommand(){}

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String... strings) {
        Player p = (Player) commandSender;
        ItemStack caca = new ItemStack(Material.PODZOL, 1);
        p.getWorld().dropItemNaturally(p.getLocation(), caca).setVelocity(new Vector(0,0,0));
        for(Player player : Bukkit.getOnlinePlayers())
            player.sendMessage("§7[§3ZenPlugin§7] §e" + p.getName() + "§6 vient de faire sa crotte!");
        return true;
    }
}
