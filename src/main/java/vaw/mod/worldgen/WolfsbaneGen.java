package vaw.mod.worldgen;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.IWorldGenerator;
import vaw.mod.init.BlockInit;

public class WolfsbaneGen implements IWorldGenerator
{
    public WolfsbaneGen()
    {
    }


	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider)
	{
		int x = (chunkX*16) + random.nextInt(16) + 8;
		int z = (chunkZ*16) + random.nextInt(16) + 8;
		
		BlockPos lowPos = new BlockPos(x,32,z);        

		Biome biome = world.getBiome(lowPos);

		//10% coverage
		if (biome == Biomes.MUTATED_FOREST)
		{
			if (random.nextFloat() > 0.10) return;
		}
		else
		if (random.nextFloat() > 0.05) return;
		
	    if( (BiomeDictionary.hasType(biome, Type.HILLS) || BiomeDictionary.hasType(biome, Type.MOUNTAIN)) && !BiomeDictionary.hasType(biome, Type.HOT))
	    {

	    	BlockBush flower = (BlockBush) BlockInit.WOLFSBANE;
			for (int i = 0; i < 16; ++i)
	        {

				x = (chunkX*16) + random.nextInt(16) + 8;
				z = (chunkZ*16) + random.nextInt(16) + 8;
				lowPos = new BlockPos(x,32,z);       
				BlockPos pos = world.getTopSolidOrLiquidBlock(lowPos);
				//BlockPos pos = new BlockPos(x,y,z);        

				
				if(world.isAirBlock(pos) && flower.canBlockStay(world, pos, flower.getDefaultState()))
				{
					world.setBlockState(pos, flower.getDefaultState());
				}
	        }
		}
		
	}
	
}