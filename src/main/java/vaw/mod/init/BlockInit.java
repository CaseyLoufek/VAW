package vaw.mod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import vaw.mod.objects.blocks.BlockGarlic;
import vaw.mod.objects.blocks.BlockModPlant;

public class BlockInit 
{


	public static final List<Block> BLOCKS = new ArrayList<Block>();
	//public static final Block LEATHER_BLOCK = new BlockBase("leather_block", Material.CLOTH).setHardness(0.6F);
	//public static final Block FEATHER_BLOCK = new BlockFeathers("feather_block", Material.CLOTH);

	public static final Block WOLFSBANE = new BlockModPlant("wolfsbane", Material.PLANTS).setHardness(0.6F);
	public static final Block GARLICS = new BlockGarlic("garlics", Material.PLANTS);
	
	//public static final Block CANDLE = new BlockCandle("candle", Material.WOOD).setLightLevel(0.625F);
}
