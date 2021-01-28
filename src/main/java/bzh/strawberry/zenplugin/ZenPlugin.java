package bzh.strawberry.zenplugin;

import bzh.strawberry.zenplugin.commands.CacaCommand;
import bzh.strawberry.zenplugin.commands.HatCommand;
import bzh.strawberry.zenplugin.listeners.Listeners;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.*;


/*
This file HomePlugin is part of a project HomesPlugin.
It was created on 05/06/2020 at 19:44 by Uicias.
This file as the whole project shouldn't be modify by others without the express permission from HomesPlugin author(s).
Also this comment shouldn't get remove from the file. (see Licence.md)
*/
public class ZenPlugin extends JavaPlugin {

    public static ZenPlugin zenPlugin;

    @Override
    public void onEnable(){
        zenPlugin = this;

        getServer().getLogger().info("Ajout des commandes");
        Objects.requireNonNull(getServer().getPluginCommand("hat")).setExecutor(new HatCommand());
        Objects.requireNonNull(getServer().getPluginCommand("caca")).setExecutor(new CacaCommand());
        getServer().getLogger().info("Ajout des commandes DONE");
        getServer().getLogger().info("Ajout des listeners");
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getServer().getLogger().info("Ajout des listeners DONE");
        getServer().getLogger().info("Ajout du/des craft customs");
        getServer().addRecipe(new ShapedRecipe(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE)).shape("GGG", "GAG", "GGG").setIngredient('G', Material.GOLD_BLOCK).setIngredient('A', Material.GOLDEN_APPLE));
        getServer().getLogger().info("Ajout du/des craft customs DONE");

    }

    @Override
    public void onDisable(){ }


}
