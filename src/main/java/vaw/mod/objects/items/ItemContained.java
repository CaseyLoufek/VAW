package vaw.mod.objects.items;

import net.minecraft.item.Item;
import vaw.mod.Main;
import vaw.mod.init.ItemInit;
import vaw.mod.util.Reference;

public class ItemContained extends Item
{

	public ItemContained(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.VampiresAndWerewolves);
		
		ItemInit.ITEMS.add(this);
	}


	

	@Override
	public Item getContainerItem() 
	{
		return ItemInit.STAKE_WOOD;
	}
	
	@Override
	public boolean hasContainerItem() 
	{
		return true;
	}
	
}
