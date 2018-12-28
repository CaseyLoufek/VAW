package vaw.mod.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import vaw.mod.Main;
import vaw.mod.init.BlockInit;
import vaw.mod.init.ItemInit;
import vaw.mod.util.IHasModel;
import vaw.mod.util.Reference;

public class BlockBase extends Block implements IHasModel
{

	public BlockBase(String name, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Reference.VampiresAndWerewolves);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void registerModels() 
	{
		// TODO Auto-generated method stub
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}


	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return true;
	}
}
