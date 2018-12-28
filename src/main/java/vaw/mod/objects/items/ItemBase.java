package vaw.mod.objects.items;

import net.minecraft.item.Item;
import vaw.mod.Main;
import vaw.mod.init.ItemInit;
import vaw.mod.util.IHasModel;
import vaw.mod.util.Reference;

public class ItemBase extends Item implements IHasModel
{

	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.VampiresAndWerewolves);
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() 
	{
		// TODO Auto-generated method stub
		Main.proxy.registerItemRenderer(this, 0, "inventory");		
	}
	
}
