package vaw.mod.init;


import java.util.Iterator;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vaw.mod.Main;
import vaw.mod.entity.passive.EntityAfricanAurochs;
import vaw.mod.entity.passive.EntityAurochs;
import vaw.mod.renderer.entity.RenderAfricanAurochs;
import vaw.mod.renderer.entity.RenderAurochs;
import vaw.mod.util.Config;
import vaw.mod.util.Reference;

public class EntityInit {

    private static int entityId = 1;
    
    public static void init()
    {
    }

    public static void postInit()
    {
    	for (Biome b: ForgeRegistries.BIOMES) 
    	{
        	//clear vanilla spawns
    		if(Config.noVanillaAnimals == true)
    		{
    			Iterator<SpawnListEntry> itr = b.getSpawnableList(EnumCreatureType.CREATURE).iterator();
    			while(itr.hasNext())
    			{
					if ((itr.next().entityClass == EntityPig.class) && Config.enablePigs == true) {
						itr.remove();
					}
    			    // modify actual fooList using itr.remove()
    			}
    			itr = b.getSpawnableList(EnumCreatureType.CREATURE).iterator();
    			while(itr.hasNext())
    			{
					if ((itr.next().entityClass == EntityChicken.class) && Config.enableFowl == true) {
						itr.remove();
					}
    			}
    			itr = b.getSpawnableList(EnumCreatureType.CREATURE).iterator();
    			while(itr.hasNext())
    			{
    				if ((itr.next().entityClass == EntityHorse.class) && Config.enableHorses == true) {
    					itr.remove();
    				}    				

    			}
    			itr = b.getSpawnableList(EnumCreatureType.CREATURE).iterator();
    			while(itr.hasNext())
    			{
    				if ((itr.next().entityClass == EntityDonkey.class) && Config.enableHorses == true) {
    					itr.remove();
    				}
    			}
    			itr = b.getSpawnableList(EnumCreatureType.CREATURE).iterator();
    			while(itr.hasNext())
    			{
    				if ((itr.next().entityClass == EntityLlama.class) && Config.enableLlamas == true) {
    					itr.remove();
    				}
    			}
    			itr = b.getSpawnableList(EnumCreatureType.CREATURE).iterator();
    			while(itr.hasNext())
    			{
    				if ((itr.next().entityClass == EntityCow.class) && Config.enableCattle == true) {
    					itr.remove();
    				}
    			}
    			itr = b.getSpawnableList(EnumCreatureType.CREATURE).iterator();
    			while(itr.hasNext())
    			{
    				if ((itr.next().entityClass == EntitySheep.class) && Config.enableSheep == true) {
						itr.remove();
					}
				}
    			itr = b.getSpawnableList(EnumCreatureType.CREATURE).iterator();
    			while(itr.hasNext())
    			{
    				if ((itr.next().entityClass == EntityPolarBear.class) && Config.enableBears == true) {
						itr.remove();
					}
				}
    			itr = b.getSpawnableList(EnumCreatureType.CREATURE).iterator();
    			while(itr.hasNext())
    			{
    				if ((itr.next().entityClass == EntityOcelot.class) && Config.enableCats == true) {
						itr.remove();
					}
				}
    			itr = b.getSpawnableList(EnumCreatureType.CREATURE).iterator();
    			while(itr.hasNext())
    			{
    				if ((itr.next().entityClass == EntityParrot.class) && Config.enableParrots == true) {
						itr.remove();
					}
				}
    			itr = b.getSpawnableList(EnumCreatureType.CREATURE).iterator();
    			while(itr.hasNext())
    			{
    				if ((itr.next().entityClass == EntityWolf.class) && Config.enableCanines == true) {
						itr.remove();
					}
				}
    			itr = b.getSpawnableList(EnumCreatureType.CREATURE).iterator();
    			while(itr.hasNext())
    			{
    				if ((itr.next().entityClass == EntityRabbit.class) && Config.enableRabbits == true) {
						itr.remove();
					}
				}
    			
    		}
						
    		//b.getSpawnableList(EnumCreatureType.AMBIENT).clear();
    		//b.getSpawnableList(EnumCreatureType.WATER_CREATURE).clear();
    		
    		//disable unnatural mobs
    		if(Config.noUnnaturalMobs == true) b.getSpawnableList(EnumCreatureType.MONSTER).clear();
    	}

    	registerEntity("aurochs", EntityAurochs.class, entityId++, 1116416,  4073990);
    	registerEntity("aurochs_african", EntityAfricanAurochs.class, entityId++, 1116419,  4073995);
        

		if(Config.enableCattle == true && Config.dynamicBiomes == false)
		{
			if(Config.wildBovine == true)
			{
				EntityRegistry.addSpawn(EntityAurochs.class, 6, 2, 7, EnumCreatureType.CREATURE, Biomes.FOREST); //change the values to vary the spawn rarity, biome, etc. 
		    	EntityRegistry.addSpawn(EntityAurochs.class, 6, 2, 7, EnumCreatureType.CREATURE, Biomes.FOREST_HILLS); //change the values to vary the spawn rarity, biome, etc.  
		    	EntityRegistry.addSpawn(EntityAurochs.class, 6, 2, 7, EnumCreatureType.CREATURE, Biomes.MUTATED_FOREST); //change the values to vary the spawn rarity, biome, etc.
		    	EntityRegistry.addSpawn(EntityAurochs.class, 6, 2, 7, EnumCreatureType.CREATURE, Biomes.BIRCH_FOREST); //change the values to vary the spawn rarity, biome, etc. 
		    	EntityRegistry.addSpawn(EntityAurochs.class, 6, 2, 7, EnumCreatureType.CREATURE, Biomes.BIRCH_FOREST_HILLS); //change the values to vary the spawn rarity, biome, etc. 
		    	EntityRegistry.addSpawn(EntityAurochs.class, 6, 2, 7, EnumCreatureType.CREATURE, Biomes.MUTATED_BIRCH_FOREST); //change the values to vary the spawn rarity, biome, etc. 
		    	EntityRegistry.addSpawn(EntityAurochs.class, 6, 2, 7, EnumCreatureType.CREATURE, Biomes.MUTATED_BIRCH_FOREST_HILLS); //change the values to vary the spawn rarity, biome, etc. 
		    	EntityRegistry.addSpawn(EntityAurochs.class, 6, 2, 7, EnumCreatureType.CREATURE, Biomes.TAIGA); //change the values to vary the spawn rarity, biome, etc. 
		    	EntityRegistry.addSpawn(EntityAurochs.class, 6, 2, 7, EnumCreatureType.CREATURE, Biomes.TAIGA_HILLS); //change the values to vary the spawn rarity, biome, etc. 
		    	EntityRegistry.addSpawn(EntityAurochs.class, 6, 2, 7, EnumCreatureType.CREATURE, Biomes.MUTATED_TAIGA); //change the values to vary the spawn rarity, biome, etc. 
		    	EntityRegistry.addSpawn(EntityAurochs.class, 6, 2, 7, EnumCreatureType.CREATURE, Biomes.COLD_TAIGA); //change the values to vary the spawn rarity, biome, etc. 
		    	EntityRegistry.addSpawn(EntityAurochs.class, 6, 2, 7, EnumCreatureType.CREATURE, Biomes.COLD_TAIGA_HILLS); //change the values to vary the spawn rarity, biome, etc. 
		    	EntityRegistry.addSpawn(EntityAurochs.class, 6, 2, 7, EnumCreatureType.CREATURE, Biomes.MUTATED_TAIGA_COLD); //change the values to vary the spawn rarity, biome, etc. 
		    	
		    	EntityRegistry.addSpawn(EntityAfricanAurochs.class, 6, 3, 8, EnumCreatureType.CREATURE, Biomes.MUTATED_MESA); //change the values to vary the spawn rarity, biome, etc
		    	
			}
		}

    }
    private static void registerEntity(String name, Class<? extends Entity> cls, int id)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), cls, name, id++, Main.instance, 80, 3, true);
    }

    private static void registerEntity(String name, Class<? extends Entity> cls, int id, int primaryEggColor, int secondaryEggColor)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), cls, name, id++, Main.instance, 80, 3, true, primaryEggColor, secondaryEggColor);
    }
    

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityAurochs.class, RenderAurochs.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityAfricanAurochs.class, RenderAfricanAurochs.FACTORY);
    }

}