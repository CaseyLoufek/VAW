package vaw.mod.objects.items;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vaw.mod.Main;
import vaw.mod.init.ItemInit;
import vaw.mod.util.Reference;

public class ItemFuel extends Item
{
	protected int maxStackSize = 1;
    
	public ItemFuel(String name)
	{
        //this.setMaxStackSize(Config.bottleStackSize);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.VampiresAndWerewolves);
		
		ItemInit.ITEMS.add(this);
	}




	@Override
	public Item getContainerItem() 
	{
		return Items.GLASS_BOTTLE;
	}
	
	@Override
	public boolean hasContainerItem() 
	{
		return true;
	}

	@Override
    public Item setMaxStackSize(int maxStackSize)
    {
        this.maxStackSize = maxStackSize;
        return this;
    }
	
    /**
     * Returns the maximum size of the stack for a specific item. *Isn't this more a Set than a Get?*
     */
    @Override // Use ItemStack sensitive version below.
    public int getItemStackLimit()
    {
        return this.maxStackSize;
    }

    /**
     * Gets the maximum number of items that this stack should be able to hold.
     * This is a ItemStack (and thus NBT) sensitive version of Item.getItemStackLimit()
     *
     * @param stack The ItemStack
     * @return The maximum number this item can be stacked to
     */
    @Override
    public int getItemStackLimit(ItemStack stack)
    {
        return this.getItemStackLimit();
    }
}
