package vaw.mod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import vaw.mod.objects.items.ItemModSeedFood;
import vaw.mod.objects.tools.ToolStake;

public class ItemInit 
{

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	//public static final ToolMaterial TOOL_FLINT = EnumHelper.addToolMaterial("tool_flint", 0, 39, 2.0F, 1.0F, 5);  

	//Resources
	//public static final Item BONE_MEATY = new ItemContained("bone_meaty");
	
	//public static final Item BOWL_WATER = new ItemBowl("bowl_water");
	 	
	//public static final Item FAT_BOTTLE = new ItemFuel("fat_bottle");

	//public static final Item ZOOPEDIA = new ItemBase("zoopedia").setMaxStackSize(1);
	
	//Tools 
	public static final Item STAKE_WOOD = new ToolStake("stake_wood", Item.ToolMaterial.WOOD);
	public static final Item GARLIC = new ItemModSeedFood("garlic",  2, 0.2f, BlockInit.GARLICS, Blocks.FARMLAND);

	//public static final Item MALLARD_RAW = new ItemModFood("mallard_raw", "Mallard", 4, 0.2f, false, 0.3f,
	//	    new PotionEffect(Potion.getPotionById(9), 300, 1), 
	//	    new PotionEffect(Potion.getPotionById(19), 300, 1) );
	
	public static Item ACORN;
	public static Item ACORN_DARK;
	public static Item ACORN_DYING;
	public static Item ACORN_HELL;
	public static Item ACORN_AUTUMN;
	public static Item ACORN_FLOWERING;
	public static Item BAMBOO_SHOOT;
	public static Item PEAR; 
	public static Item PEACH; 
	public static Item PERSIMMON;
	public static Item PINE_CONE;
	public static Item SPRUCE_CONE;
	public static Item REDWOOD_CONE;
	public static Item FIR_CONE;
}











