package vaw.mod.objects.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import vaw.mod.init.BlockInit;
import vaw.mod.init.ItemInit;
import vaw.mod.util.Reference;

public class BlockGarlic extends BlockCrops
{

	public BlockGarlic(String name, Material material) {
		super();
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.VampiresAndWerewolves);
		
		BlockInit.BLOCKS.add(this);
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
