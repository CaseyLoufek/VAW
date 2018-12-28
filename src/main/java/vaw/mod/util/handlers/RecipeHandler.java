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
				
		if (Config.enableFowl)
		{
			GameRegistry.addShapedRecipe(new ResourceLocation("EggCake"), new ResourceLocation("recipes"), new ItemStack(Item.getItemFromBlock(Blocks.CAKE), 1), new Object[]{ "BBB", "SES", "FFF", 'B', Items.MILK_BUCKET, 'S', Items.SUGAR, 'E', "egg", 'F', Items.WHEAT});
		}

		if (Config.paperFromWood)
		{
			GameRegistry.addShapedRecipe(new ResourceLocation("PaperWood"), new ResourceLocation("recipes"),  new ItemStack(Items.PAPER, 2), new Object[]{ "WWW", 'W', "logWood"});
		}
		
		if (Config.sugarFromBeets) GameRegistry.addShapedRecipe(new ResourceLocation("recipes"), new ResourceLocation("SugarBeets"), new ItemStack(Items.SUGAR, 2), "BBB", 'B', Items.BEETROOT);
		if (Config.craftableNametags) GameRegistry.addShapedRecipe(new ResourceLocation("recipes"), new ResourceLocation("NameTag"),new ItemStack(Items.NAME_TAG, 1), " S ", " PI", " P ", 'S', Items.STRING, 'P', Items.PAPER, 'I', new ItemStack(Items.DYE, 1, EnumDyeColor.BLACK.getDyeDamage()));
		if (Config.craftableSaddles) GameRegistry.addShapedRecipe(new ResourceLocation("Saddle"), new ResourceLocation("Saddle"),new ItemStack(Items.SADDLE, 1), "LLL", "LSL", " I ", 'L', Items.LEATHER, 'S', Items.STRING, 'I', Items.IRON_INGOT);
		if (Config.craftableHorseArmor == true)
		{
			GameRegistry.addShapedRecipe(new ResourceLocation("IronHorseArmor"), new ResourceLocation("IronHorseArmor"), new ItemStack(Items.IRON_HORSE_ARMOR), "  I", "IWI", "IWI", 'I', Item.getItemFromBlock(Blocks.IRON_BLOCK), 'W', new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 15));
			GameRegistry.addShapedRecipe(new ResourceLocation("GoldHorseArmor"), new ResourceLocation("GoldHorseArmor"), new ItemStack(Items.GOLDEN_HORSE_ARMOR), "  G", "GWG", "GWG", 'G', Item.getItemFromBlock(Blocks.GOLD_BLOCK), 'W', new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 14));
			GameRegistry.addShapedRecipe(new ResourceLocation("DiamondHorseArmor"), new ResourceLocation("DiamondHorseArmor"), new ItemStack(Items.DIAMOND_HORSE_ARMOR), "  D", "DWD", "DWD", 'D', Item.getItemFromBlock(Blocks.DIAMOND_BLOCK), 'W', new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 4));
		}

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


