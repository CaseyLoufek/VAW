package vaw.mod.entity.ai;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import vaw.mod.entity.IEntityAdvanced;
import vaw.mod.util.Config;

//Species Specific AI class tweaks
public class EntityAIImprovedMate extends EntityAIBase
{
    private final EntityAnimal theAnimal;
    private final Class <? extends EntityAnimal > mateClass;
    World theWorld;
    private EntityAnimal targetMate;
    /** Delay preventing a baby from spawning immediately when two mate-able animals find each other. */
    int spawnBabyDelay;
    /** The speed the creature moves at during mating behavior. */
    double moveSpeed;

    public EntityAIImprovedMate(EntityAnimal animal, double speedIn, Class <? extends EntityAnimal > mateType)
    {
        this.theAnimal = animal;
        this.theWorld = animal.world;
        this.mateClass = mateType;
        this.moveSpeed = speedIn;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
	public boolean shouldExecute()
    {
        if (!this.theAnimal.isInLove())
        {
            return false;
        }
        else
        {
            this.targetMate = this.getNearbyMate();
            return this.targetMate != null;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting()
    {
        return this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60;
    }

    /**
     * Resets the task
     */
    @Override
	public void resetTask()
    {
        this.targetMate = null;
        this.spawnBabyDelay = 0;
    }

    /**
     * Updates the task
     */
    @Override
	public void updateTask()
    {
        this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, this.theAnimal.getVerticalFaceSpeed());
        this.theAnimal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
        ++this.spawnBabyDelay;

        if (this.spawnBabyDelay >= 60 && this.theAnimal.getDistance(this.targetMate) < 9.0D)
        {
            this.spawnBaby();
        }
    }

    /**
     * Loops through nearby animals and finds another animal of the same type that can be mated with. Returns the first
     * valid mate found.
     */
    private EntityAnimal getNearbyMate()
    {
    	List<EntityAnimal> list = this.theWorld.<EntityAnimal>getEntitiesWithinAABB(this.mateClass, this.theAnimal.getEntityBoundingBox().grow(8.0D));
        double d0 = Double.MAX_VALUE;
        EntityAnimal entityanimal = null;

        for (EntityAnimal entityanimal1 : list)
        {
            if (this.theAnimal.canMateWith(entityanimal1) && this.theAnimal.getDistance(entityanimal1) < d0)
            {
                entityanimal = entityanimal1;
                d0 = this.theAnimal.getDistance(entityanimal1);
            }
        }

        return entityanimal;
    }

	/**
     * Spawns a baby animal of the same type.
     */
    private void spawnBaby()
    {
    	if (Config.enablePregnancy == true)
    	{
            IEntityAdvanced animal = (IEntityAdvanced) this.theAnimal;
            if (animal.getSex() == 0)
        	{
        		this.theAnimal.setGrowingAge(24000);
        		animal.getOtherParentData((IEntityAdvanced) this.targetMate);
            	this.targetMate.setGrowingAge(6000);
        	}
            else
            {
                animal = (IEntityAdvanced) this.targetMate;
        		this.targetMate.setGrowingAge(24000);
        		animal.getOtherParentData((IEntityAdvanced) this.theAnimal);
            	this.theAnimal.setGrowingAge(6000);
            }
            	
    		animal.setPregnancyTime(1);

            for (int i = 0; i < 7; ++i)
            {
            	Random random = this.theAnimal.getRNG();
                double d0 = random.nextGaussian() * 0.02D;
                double d1 = random.nextGaussian() * 0.02D;
                double d2 = random.nextGaussian() * 0.02D;
                double d3 = random.nextDouble() * this.theAnimal.width * 2.0D - this.theAnimal.width;
                double d4 = 0.5D + random.nextDouble() * this.theAnimal.height;
                double d5 = random.nextDouble() * this.theAnimal.width * 2.0D - this.theAnimal.width;
                if (random.nextInt(2) == 1) this.theAnimal.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.theAnimal.posX + d3, this.theAnimal.posY + d4, this.theAnimal.posZ + d5, d0, d1, d2, new int[0]);
                else this.theAnimal.world.spawnParticle(EnumParticleTypes.HEART, this.theAnimal.posX + d3, this.theAnimal.posY + d4, this.theAnimal.posZ + d5, d0, d1, d2, new int[0]);
            }
            for (int i = 0; i < 7; ++i)
            {
            	Random random = this.targetMate.getRNG();
                double d0 = random.nextGaussian() * 0.02D;
                double d1 = random.nextGaussian() * 0.02D;
                double d2 = random.nextGaussian() * 0.02D;
                double d3 = random.nextDouble() * this.targetMate.width * 2.0D - this.targetMate.width;
                double d4 = 0.5D + random.nextDouble() * this.targetMate.height;
                double d5 = random.nextDouble() * this.targetMate.width * 2.0D - this.targetMate.width;
                if (random.nextInt(2) == 1) this.targetMate.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.targetMate.posX + d3, this.targetMate.posY + d4, this.targetMate.posZ + d5, d0, d1, d2, new int[0]);
                else this.targetMate.world.spawnParticle(EnumParticleTypes.HEART, this.targetMate.posX + d3, this.targetMate.posY + d4, this.targetMate.posZ + d5, d0, d1, d2, new int[0]);
            }
            
            this.theAnimal.resetInLove();
            this.targetMate.resetInLove();
            return;
            
    	}
    	else
    	{
    	
	    	EntityAgeable entityageable = this.theAnimal.createChild(this.targetMate);
	        final net.minecraftforge.event.entity.living.BabyEntitySpawnEvent event = new net.minecraftforge.event.entity.living.BabyEntitySpawnEvent(theAnimal, targetMate, entityageable);
	        final boolean cancelled = net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
	        if (cancelled) {
	            //Reset the "inLove" state for the animals
	            this.theAnimal.setGrowingAge(6000);
	            this.targetMate.setGrowingAge(6000);
	            this.theAnimal.resetInLove();
	            this.targetMate.resetInLove();
	            return;
	        }
	
	        entityageable = event.getChild();
	
	        if (entityageable != null)
	        {
	        	EntityPlayer entityplayer = this.theAnimal.getLoveCause();
	
	            if (entityplayer == null && this.targetMate.getLoveCause() != null)
	            {
	                entityplayer = this.targetMate.getLoveCause();
	            }
	
	            if (entityplayer != null)
	            {
	                entityplayer.addStat(StatList.ANIMALS_BRED);
	
	                if (this.theAnimal instanceof EntityCow)
	                {
	                    //entityplayer.addStat(AchievementList.BREED_COW);
	                }
	            }
	
	
	            IEntityAdvanced animal = (IEntityAdvanced) this.theAnimal;
	            if (Config.enableSexes && animal.getSex() == 0)
	        	{
	        		this.theAnimal.setGrowingAge(24000);
	        	}
	            else
	            {
	            	this.theAnimal.setGrowingAge(6000);
	            }
	            animal = (IEntityAdvanced) this.targetMate;
	            if (Config.enableSexes && animal.getSex() == 0)
	        	{
	        		this.targetMate.setGrowingAge(24000);
	        	}
	            else
	            {
	            	this.targetMate.setGrowingAge(6000);
	            }
	            this.theAnimal.resetInLove();
	            this.targetMate.resetInLove();
	            
	            entityageable.setGrowingAge(animal.getChildAge());
	            entityageable.setLocationAndAngles(this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, 0.0F, 0.0F);
	            this.theWorld.spawnEntity(entityageable);
	            Random random = this.theAnimal.getRNG();
	
	            for (int i = 0; i < 7; ++i)
	            {
	                double d0 = random.nextGaussian() * 0.02D;
	                double d1 = random.nextGaussian() * 0.02D;
	                double d2 = random.nextGaussian() * 0.02D;
	                double d3 = random.nextDouble() * this.theAnimal.width * 2.0D - this.theAnimal.width;
	                double d4 = 0.5D + random.nextDouble() * this.theAnimal.height;
	                double d5 = random.nextDouble() * this.theAnimal.width * 2.0D - this.theAnimal.width;
	                this.theWorld.spawnParticle(EnumParticleTypes.HEART, this.theAnimal.posX + d3, this.theAnimal.posY + d4, this.theAnimal.posZ + d5, d0, d1, d2, new int[0]);
	            }
	
	            if (this.theWorld.getGameRules().getBoolean("doMobLoot") && animal.isTame())
	            {
	                this.theWorld.spawnEntity(new EntityXPOrb(this.theWorld, this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, random.nextInt(7) + 1));
	            }
	        }
    	}

    }
}