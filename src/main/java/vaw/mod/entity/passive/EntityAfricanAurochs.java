package vaw.mod.entity.passive;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import vaw.mod.entity.IEntityAdvancedMilkable;
import vaw.mod.util.Config;

public class EntityAfricanAurochs extends EntityAurochs
{

    private int parentTamability;
    private float parentMaxHealth;
    private int parentMaxMilk;

    private int groupTimer = 0;
    
	public EntityAfricanAurochs(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}
	

    @Override
    public String getZoopediaName()
    {
    	return "Scrub Aurochs";
    }
    
   @Override
@Nullable
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
   {
   	super.onInitialSpawn(difficulty, livingdata);

       int randroll = this.rand.nextInt(2);
		if (Config.enableSexes == true)
		{
	        if (randroll == 1)
			{
	            this.dataManager.set(SEX, Integer.valueOf(1));	
			}
	        else
	        {
	            this.dataManager.set(SEX, Integer.valueOf(0));
	        }
	        randroll = this.rand.nextInt(2);
       }

		
		randroll = this.rand.nextInt(2);
		if (randroll == 1)
		{
   		this.dataManager.set(TAMABILITY, Integer.valueOf(1));
		}
       else
       {
   		this.dataManager.set(TAMABILITY, Integer.valueOf(0));
       }
		this.setHunger(foodMax);
		this.setHappiness(0);
		this.setMaxMilk(MathHelper.clamp(this.rand.nextInt(5) + this.rand.nextInt(6), 3, 9));
		this.setCurMilk(this.getMaxMilk());
		this.setMilkTimer(0);

       if (this.rand.nextInt(5) == 0)
       {
           this.setGrowingAge(childAge);
           this.setOldAge(0);
       }    	

       this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getModifiedMaxHealth());
       this.setHealth(this.getMaxHealth());
       //new
       this.setupTamedAI();
       return livingdata;
   }

    //
    @Override
    public EntityCow createChild(EntityAgeable ageable)
    {
    	IEntityAdvancedMilkable entitycow = (IEntityAdvancedMilkable)ageable;

        //Domestication process
        int newTamability = (entitycow.getTamability() + this.getTamability()) / 2;
        int randroll = this.rand.nextInt(2) + 1;
        if (this.isTame() || entitycow.isTame())
        {
        	newTamability += randroll;	
        }
        else
        {
        	newTamability -= randroll;
        }
        newTamability = MathHelper.clamp(newTamability, -5, 5);
        

        IEntityAdvancedMilkable entitynewcow;
        	entitynewcow = new EntityAfricanAurochs(this.world);
        //check nearby players for taming
        List<EntityPlayer> players = this.world.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().grow(9.0D));
		for (Entity e : players)
		{
			if (e != null)
			{
	    		entitynewcow.setOwnerId(e.getPersistentID());
	    		entitynewcow.setTamed(true);
			}
		}
        entitynewcow.setTamability(newTamability);
        
        if (Config.newHorseBreeding == true)
		{
	        
	        double d1 = this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() + ageable.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
	        entitynewcow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Math.round(d1 / 1.5D + (this.rand.nextDouble() * 0.5D)));
	        if (entitynewcow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() > 30.0D) entitynewcow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
	        if (entitynewcow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() < 20.0D) entitynewcow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	        
	        entitynewcow.setMaxMilk((this.getMaxMilk() + entitycow.getMaxMilk()) /2);
	        if (this.rand.nextInt(3) == 0) entitynewcow.setMaxMilk(entitynewcow.getMaxMilk()+this.rand.nextInt(3) - 1);
	        entitynewcow.setMaxMilk(MathHelper.clamp(entitynewcow.getMaxMilk(), 3, 6));
	        entitynewcow.setMilkTimer(0);
	        entitynewcow.setCurMilk(0);;
        }
		else
		{
	        double d1 = this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() + ageable.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() + this.getModifiedMaxHealth();
	        entitynewcow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(d1 / 3.0D);
		}
        
        EntityCow entityreturncow = (EntityCow) entitynewcow;
        

		if (Config.enableSexes == true)
		{
			entitynewcow.setSex(Integer.valueOf(this.rand.nextInt(2)));;			
		}
		
		entitynewcow.setHunger(foodMax);
		entitynewcow.setHappiness(0);
        
        return entityreturncow;
    }
    
    /**
     * Returns randomized max health
     */
    private float getModifiedMaxHealth()
    {
        return 20.0F + this.rand.nextInt(5) + this.rand.nextInt(5);
    }

    @Override
	public EntityCow createChild()
    {
        //Domestication process
        int newTamability = (this.parentTamability + this.getTamability()) / 2;
        int randroll = this.rand.nextInt(2) + 1;
        if (this.isTame())
        {
        	newTamability += randroll;	
        }
        else
        {
        	newTamability -= randroll;
        }
        newTamability = MathHelper.clamp(newTamability, -5, 5);
        

        IEntityAdvancedMilkable entitynewcow;
        	entitynewcow = new EntityAfricanAurochs(this.world);
        	
        //check nearby players for taming
        List<EntityPlayer> players = this.world.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().grow(9.0D));
		for (Entity e : players)
		{
			if (e != null)
			{
	    		entitynewcow.setOwnerId(e.getPersistentID());
	    		entitynewcow.setTamed(true);
			}
		}
        entitynewcow.setTamability(newTamability);
        
        EntityCow entityreturncow = (EntityCow) entitynewcow;
        if (Config.newHorseBreeding == true)
		{
	        
	        double d1 = this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() + this.parentMaxHealth;
	        entityreturncow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Math.round(d1 / 1.5D + (this.rand.nextDouble() * 0.5D)));
	        //entityreturncow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(d1 / (1.5D + (this.rand.nextDouble() * 1.0D)));
	        if (entityreturncow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() > 30.0D) entityreturncow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
	        if (entityreturncow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() < 20.0D) entityreturncow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	        
	        entitynewcow.setMaxMilk((this.getMaxMilk() + this.parentMaxMilk) /2);
	        if (this.rand.nextInt(3) == 0) entitynewcow.setMaxMilk(entitynewcow.getMaxMilk()+this.rand.nextInt(3) - 1);
	        entitynewcow.setMaxMilk(MathHelper.clamp(entitynewcow.getMaxMilk(), 3, 6));
	        entitynewcow.setMilkTimer(0);
	        entitynewcow.setCurMilk(0);
        }
		else
		{
	        double d1 = this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() + this.parentMaxHealth + this.getModifiedMaxHealth();
	        entityreturncow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(d1 / 3.0D);
		}
        

		if (Config.enableSexes == true)
		{
			entitynewcow.setSex(Integer.valueOf(this.rand.nextInt(2)));;			
		}
		
		entitynewcow.setHunger(foodMax);
		entitynewcow.setHappiness(0);
        
        return entityreturncow;
    }
    

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
	public void onLivingUpdate()
    {
    	if (Config.enablePregnancy && this.getPregnancyTime() > 0)
    	{
    		int p = this.getPregnancyTime();
    		if (p >= EntityAurochs.pregnancyLength)
    		{
    			//use alt child gen from stored data on other parent
    			EntityAgeable entityageable = this.createChild();
    	        final net.minecraftforge.event.entity.living.BabyEntitySpawnEvent event = new net.minecraftforge.event.entity.living.BabyEntitySpawnEvent(this, this, entityageable);
    	        final boolean cancelled = net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
    	        if (cancelled) {
    	            //Reset the "inLove" state for the animals
    	            this.setGrowingAge(6000);
    	            this.resetInLove();
    	            return;
    	        }

    	        entityageable = event.getChild();

    	        if (entityageable != null)
    	        {
	        		this.setGrowingAge(6000);
    	            this.resetInLove();
    	            
    	            entityageable.setGrowingAge(this.getChildAge());
    	            entityageable.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
    	            this.world.spawnEntity(entityageable);
    	            Random random = this.getRNG();

    	            for (int i = 0; i < 7; ++i)
    	            {
    	                double d0 = random.nextGaussian() * 0.02D;
    	                double d1 = random.nextGaussian() * 0.02D;
    	                double d2 = random.nextGaussian() * 0.02D;
    	                double d3 = random.nextDouble() * this.width * 2.0D - this.width;
    	                double d4 = 0.5D + random.nextDouble() * this.height;
    	                double d5 = random.nextDouble() * this.width * 2.0D - this.width;
    	                this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + d3, this.posY + d4, this.posZ + d5, d0, d1, d2, new int[0]);
    	            }

    	            if (this.world.getGameRules().getBoolean("doMobLoot") && this.isTame())
    	            {
    	                this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, random.nextInt(7) + 1));
    	            }
    	        }
    	        
        		this.setPregnancyTime(0);
    		}
    		else this.setPregnancyTime(p+1);
    	}
    	
    	super.onLivingUpdate();
        
		this.setMilkTimer(this.getMilkTimer()+this.getMaxMilk());
		if (this.getMilkTimer() >= 10000)
		{
			if (this.getCurMilk() < this.getMaxMilk())
			{
				this.setCurMilk(this.getCurMilk()+1);	
			}
			this.setMilkTimer(0);
		}

		this.groupTimer += 1;
		if (this.groupTimer >= 600)
		{
	    	this.setupTamedAI();
	    	if (Config.enableAdvancedStats == true)
	    	{
	    		List<EntityAnimal> list = this.world.<EntityAnimal>getEntitiesWithinAABB(EntityCow.class, this.getEntityBoundingBox().grow(9.0D));
//	    		EntityAnimal entityanimal = null;
//		        double d0 = Double.MAX_VALUE;
		        int breedchance = 0;
		        //group size happiness
		        if (list.size() == this.getPreferedGroupSize())
		        {
		        	breedchance = 50;
		        	this.setHappiness(this.getHappiness()+3);
		        }
		        else if (list.size() >= this.getPreferedGroupSize()-1 && list.size() <= this.getPreferedGroupSize()+1 )
		        {
		        	breedchance = 25;
		        	this.setHappiness(this.getHappiness()+1);
		        }
		        else if (list.size() <= this.getPreferedGroupSize()-3 || list.size() >= this.getPreferedGroupSize()+3 )
		        {
		        	this.setHappiness(this.getHappiness()-2);
		        }

		        //wild breeding
		        Random random = this.getRNG();
		        if (list.size() >= 2 && list.size() <= this.getPreferedGroupSize() * 3)
		        {
		        	if (Config.enableWildBreeding == true && !(this.isChild()) && !(this.isInLove())  && (this.getGrowingAge() == 0))
		        	{
		        		if ( (this.getHappiness() + breedchance) >= random.nextInt(100)) this.setInLove(null);
		        	}
		        }	
	    	}
			this.groupTimer = 0;
		}

        //new
		if (Config.enableAdvancedStats == true)
		{
        	//aging process
            int i = this.getOldAge();

            ++i;
            this.setOldAge(i);

            if (i == ageMax && Config.enableOldAge == true)
            {
                this.setDead();
            }
        
            int hunger = 0;
            Biome biome = this.world.getBiome(new BlockPos(this));
            if ( BiomeDictionary.hasType(biome, Type.HOT)) hunger -= 3;
            if ( BiomeDictionary.hasType(biome, Type.COLD)) hunger += 1;
            if ( BiomeDictionary.hasType(biome, Type.DRY))  hunger -= 3;
            if ( BiomeDictionary.hasType(biome, Type.WET)) hunger += 1;
            
	        this.setHunger(this.getHunger() - ((this.getMaxHealth() + this.getMaxMilk() + hunger + this.getStrength()) / 50000) );
	        
	        if (this.getHunger() <= 0 && Config.enableStarvation == true)
	        {
	        	this.damageEntity(DamageSource.STARVE, 1.0F);
	        	this.setHunger(3.0F);
	        	this.setHappiness(this.getHappiness()-10);
	        }
		}

		
        if (!this.world.isRemote)
        {
            if (this.rand.nextInt(900) == 0 && this.deathTime == 0 && this.getHunger() > 3.0F && this.getHealth() < this.getMaxHealth())
            {
                this.heal(1.0F);
                this.setHunger(this.getHunger()-4.0F);
            }
        }
    }
    
    //cactus immunity
   @Override
   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
   {
   	this.setHappiness(this.getHappiness()-50);
   	if ( par1DamageSource == DamageSource.CACTUS ) return false; 
       return super.attackEntityFrom(par1DamageSource, par2);
   }

    
}
