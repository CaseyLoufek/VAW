package vaw.mod.util.handlers;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import vaw.mod.init.ItemInit;
import vaw.mod.util.Config;

public class CraftingHandler {
	public static void RegisterRecipes() {
		
	}

	public static void removeRecipes() {
		 ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>)ForgeRegistries.RECIPES;
	        ArrayList<IRecipe> recipes = Lists.newArrayList(recipeRegistry.getValues());
	        
	        for (IRecipe r : recipes)
            {
                ItemStack output = r.getRecipeOutput();
                if (output.getItem() == Item.getItemFromBlock(Blocks.TORCH) && Config.disableVanillaTorches == true)
                {
                    recipeRegistry.remove(r.getRegistryName());
                    //recipeRegistry.register(DummyRecipe.from(r));
                }
                //remove old bonemeal recipe for new crafting
                if (output.getMetadata() == 15 && output.getItem() == Items.DYE && Config.shardsFromBone == true)
                {
                    recipeRegistry.remove(r.getRegistryName());
                    //recipeRegistry.register(DummyRecipe.from(r));
                }
                //remove wool from string recipe for new crafting
                if (output.getMetadata() == 0 && output.getItem() == Item.getItemFromBlock(Blocks.WOOL) && Config.woolSystem == true)
                {
                    recipeRegistry.remove(r.getRegistryName());
                    //recipeRegistry.register(DummyRecipe.from(r));
                }
	}
	}
}