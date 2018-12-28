package vaw.mod.objects.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import vaw.mod.Main;
import vaw.mod.init.ItemInit;
import vaw.mod.util.IHasModel;
import vaw.mod.util.Reference;

public class ItemBucket extends Item implements IHasModel
{

	protected int maxStackSize = 1;
	
	public ItemBucket(String name)
	{
        this.setMaxStackSize(1);
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
	

	@Override
	public Item getContainerItem() 
	{
		return Items.BUCKET;
	}
	
	
	@Override
	public boolean hasContainerItem() 
	{
		return true;
	}
	
}
