package vaw.mod.objects.items;

import net.minecraft.item.Item;
import vaw.mod.init.ItemInit;
import vaw.mod.util.Reference;

public class ItemBase extends Item
{

	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.VampiresAndWerewolves);
		
		ItemInit.ITEMS.add(this);
	}


	
}
