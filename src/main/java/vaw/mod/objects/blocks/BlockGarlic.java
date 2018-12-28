package vaw.mod.objects.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import vaw.mod.Main;
import vaw.mod.init.BlockInit;
import vaw.mod.init.ItemInit;
import vaw.mod.util.IHasModel;
import vaw.mod.util.Reference;

public class BlockGarlic extends BlockCrops implements IHasModel
{

	public BlockGarlic(String name, Material material) {
		super();
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.VampiresAndWerewolves);
		
		BlockInit.BLOCKS.add(this);
	}
	
	@Override
	public void registerModels() 
	{
		// model handling
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	

    protected Item getSeed()
    {
        return ItemInit.GARLIC;
    }

    protected Item getCrop()
    {
        return ItemInit.GARLIC;
    }

    
}
