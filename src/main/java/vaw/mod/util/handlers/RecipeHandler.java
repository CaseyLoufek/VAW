package vaw.mod.util.handlers;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import vaw.mod.init.BlockInit;
import vaw.mod.init.ItemInit;
import vaw.mod.util.Config;

public class RecipeHandler 
{
	public static void RegisterCrafting()
	{

		//OreDictionary.registerOre("hideCut", new ItemStack(ItemInit.PIG_SKIN));
		
		OreDictionary.registerOre("tanninRich",  new ItemStack(Blocks.LOG, 1, 0));
		OreDictionary.registerOre("tanninRich",  new ItemStack(Blocks.LOG2, 1, 1));
		OreDictionary.registerOre("tanninRich",  new ItemStack(Blocks.LOG, 1, 2));
		
		OreDictionary.registerOre("tanninPoor",  new ItemStack(Blocks.LOG, 1, 1));
		OreDictionary.registerOre("tanninPoor",  new ItemStack(Blocks.LOG, 1, 3));
		OreDictionary.registerOre("tanninPoor",  new ItemStack(Blocks.LOG2, 1, 0));
		
			//default to brown
			//GameRegistry.addShapedRecipe(new ResourceLocation("NewBedHide"), new ResourceLocation("NewBeds"), new ItemStack(Items.BED, 1, 12),new Object[]{ "FFF", "FFF", "WWW", 'F', "furCut", 'W', "plankWood"});
				

		 ItemInit.ACORN = Item.getByNameOrId("dynamictrees:oakseed");
		 ItemInit.ACORN_DARK = Item.getByNameOrId("dynamictrees:darkoakseed");
		 ItemInit.ACORN_AUTUMN = Item.getByNameOrId("dynamictreesbop:orangeautumnseed");
		 ItemInit.ACORN_DYING = Item.getByNameOrId("dynamictreesbop:oakdyingseed");
		 ItemInit.ACORN_FLOWERING = Item.getByNameOrId("dynamictreesbop:floweringoakseed");
		 ItemInit.ACORN_HELL = Item.getByNameOrId("dynamictreesbop:hellbarkseed");
		 ItemInit.BAMBOO_SHOOT = Item.getByNameOrId("dynamictreesbop:bambooseed");
		 ItemInit.PEAR = Item.getByNameOrId("bop:pear");
		 ItemInit.PEACH = Item.getByNameOrId("bop:peach");
		 ItemInit.PERSIMMON = Item.getByNameOrId("bop:persimmon");
		 ItemInit.SPRUCE_CONE = Item.getByNameOrId("dynamictrees:spruceseed");
		 ItemInit.FIR_CONE = Item.getByNameOrId("dynamictreesbop:firseed");
		 ItemInit.PINE_CONE = Item.getByNameOrId("dynamictreesbop:pineseed");
		 ItemInit.REDWOOD_CONE = Item.getByNameOrId("dynamictreesbop:redwoodseed");
		 

		OreDictionary.registerOre("treeCone", new ItemStack(ItemInit.SPRUCE_CONE));
		OreDictionary.registerOre("treeCone", new ItemStack(ItemInit.FIR_CONE));
		OreDictionary.registerOre("treeCone", new ItemStack(ItemInit.PINE_CONE));
		OreDictionary.registerOre("treeCone", new ItemStack(ItemInit.REDWOOD_CONE));
	}
	
	
	
	public static void RegisterSmelting()
	{
		//GameRegistry.addSmelting(ItemInit.MUSHROOM_STEW_RAW, new ItemStack(Items.MUSHROOM_STEW), 1);

	}

}


