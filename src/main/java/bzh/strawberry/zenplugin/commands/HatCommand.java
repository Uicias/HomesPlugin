package bzh.strawberry.zenplugin.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/*
This file HatCommand is part of a project HomesPlugin.
It was created on 27/06/2020 at 23:45 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from HomesPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class HatCommand implements CommandExecutor {

    public HatCommand(){}

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;
        ItemStack itemEnMain = player.getInventory().getItemInMainHand();

        if(itemEnMain.getType() == Material.AIR){
            player.sendMessage("§7Vous n'avez pas d'items en main..");
            return false;
        }

        ArrayList<Material> bannedItems = new ArrayList<>();
        bannedItems.add(Material.DIAMOND_SWORD);
        bannedItems.add(Material.GOLDEN_SWORD);
        bannedItems.add(Material.IRON_SWORD);
        bannedItems.add(Material.WOODEN_SWORD);
        bannedItems.add(Material.STONE_SWORD);
        bannedItems.add(Material.DIAMOND_PICKAXE);
        bannedItems.add(Material.GOLDEN_PICKAXE);
        bannedItems.add(Material.IRON_PICKAXE);
        bannedItems.add(Material.WOODEN_PICKAXE);
        bannedItems.add(Material.STONE_PICKAXE);
        bannedItems.add(Material.DIAMOND_AXE);
        bannedItems.add(Material.IRON_AXE);
        bannedItems.add(Material.GOLDEN_AXE);
        bannedItems.add(Material.WOODEN_AXE);
        bannedItems.add(Material.STONE_AXE);
        bannedItems.add(Material.DIAMOND_HOE);
        bannedItems.add(Material.GOLDEN_HOE);
        bannedItems.add(Material.IRON_HOE);
        bannedItems.add(Material.WOODEN_HOE);
        bannedItems.add(Material.STONE_HOE);
        bannedItems.add(Material.IRON_HOE);
        bannedItems.add(Material.DIAMOND_SHOVEL);
        bannedItems.add(Material.GOLDEN_SHOVEL);
        bannedItems.add(Material.IRON_SHOVEL);
        bannedItems.add(Material.WOODEN_SHOVEL);
        bannedItems.add(Material.STONE_SHOVEL);
        bannedItems.add(Material.BOW);
        bannedItems.add(Material.FLINT_AND_STEEL);
        bannedItems.add(Material.SHEARS);
        bannedItems.add(Material.FISHING_ROD);
        bannedItems.add(Material.CARROT_ON_A_STICK);
        bannedItems.add(Material.SHIELD);
        bannedItems.add(Material.ELYTRA);

        if(bannedItems.contains(itemEnMain.getType())){
            player.sendMessage("§cVous ne pouvez pas mettre cet item sur votre tête !");
            return false;
        }

        player.getInventory().setItemInMainHand(player.getInventory().getHelmet());
        player.getInventory().setHelmet(itemEnMain);

        return true;
    }
}
