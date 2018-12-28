package vaw.mod.entity.passive;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import vaw.mod.entity.IEntityAdvanced;
import vaw.mod.entity.IEntityAdvancedMilkable;
import vaw.mod.entity.ai.EntityAIGrainEater;
import vaw.mod.entity.ai.EntityAIHerdMovement;
import vaw.mod.entity.ai.EntityAIImprovedFollowParent;
import vaw.mod.entity.ai.EntityAIImprovedMate;
import vaw.mod.entity.ai.EntityAIItemSearch;
import vaw.mod.util.Config;

public class EntityAurochs extends EntityCow implements IEntityAdvancedMilkable
{
	///
    private static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityAurochs.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS,
			Items.REEDS, Item.getItemFromBlock(Blocks.YELLOW_FLOWER), Items.APPLE, Items.CARROT, Items.WHEAT, Items.BEETROOT, Items.GOLDEN_APPLE,
			Items.GOLDEN_CARROT, Items.MELON, Items.SPECKLED_MELON);
	
    private static final DataParameter<Boolean> SADDLE = EntityDataManager.<Boolean>createKey(EntityAurochs.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> OLD_AGE = EntityDataManager.<Integer>createKey(EntityAurochs.class, DataSerializers.VARINT);
    protected static final DataParameter<Integer> SEX = EntityDataManager.<Integer>createKey(EntityAurochs.class, DataSerializers.VARINT);
    protected static final DataParameter<Integer> TAMABILITY = EntityDataManager.<Integer>createKey(EntityAurochs.class, DataSerializers.VARINT);
    private static final DataParameter<Float> HUNGER = EntityDataManager.<Float>createKey(EntityAurochs.class, DataSerializers.FLOAT);
    private static final DataParameter<Integer> HAPPINESS = EntityDataManager.<Integer>createKey(EntityAurochs.class, DataSerializers.VARINT);
    ///
    private static final DataParameter<Integer> MAXMILK = EntityDataManager.<Integer>createKey(EntityAurochs.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> CURMILK = EntityDataManager.<Integer>createKey(EntityAurochs.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> MILK_TIMER = EntityDataManager.<Integer>createKey(EntityAurochs.class, DataSerializers.VARINT);

    
    private static final DataParameter<Integer> DATA_STRENGTH_ID = EntityDataManager.<Integer>createKey(EntityAurochs.class, DataSerializers.VARINT);

    //new ai

    private EntityAIAvoidEntity<EntityPlayer> avoidEntity;
    /** The tempt AI task for this mob, used to prevent taming while it is fleeing. */
    //private EntityAITempt aiTempt;

    private EntityAIPanic aiPanic;
    private EntityAIAttackMelee aiAttack;
    private EntityAIHerdMovement aiHerd;
    ///
    /** "The higher this value, the more likely the bovine is to be tamed." */
    protected int temper;
    
    private int parentTamability;
    private float parentMaxHealth;;
    private int parentMaxMilk;
    
    private int pregnancyTime;
    private int groupTimer = 0;
    static public int pregnancyLength = 18000;
    static public float foodMax = 75.0F;
    static public int ageMax = 528000; //22 'years'
    static public int childAge = -36000; //18 'monthes' 

    ////
    
    public EntityAurochs(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.35F);
        
    }


    @Override
    public String getZoopediaName()
    {
    	return "Aurochs";
    }

    @Override
    public float getZoopediaHealth()
    {
    	return this.getHealth();
    }
    
    @Override
    public float getZoopediaMaxHealth()
    {
    	return this.getMaxHealth();
    }
    
    public static void registerFixesCow(DataFixer fixer)
    {
    	EntityLiving.registerFixesMob(fixer, EntityAurochs.class);
    }


    @Override
	protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.aiPanic = new EntityAIPanic(this, 1.75D);
        this.aiAttack = new EntityAIAttackMelee(this, 1.75D, false);
        this.aiHerd = new EntityAIHerdMovement(this, 1.0D);
        this.tasks.addTask(1, this.aiAttack);
        this.tasks.addTask(2, this.aiPanic);
        this.tasks.addTask(3, this.aiHerd);
        this.tasks.addTask(4, new EntityAIImprovedMate(this, 1.0D, EntityCow.class));
        //this.tasks.addTask(4, new EntityAITempt(this, 1.0D, ItemInit.WHEAT_ON_A_STICK, false));
        this.tasks.addTask(5, new EntityAITempt(this, 1.0D, false, TEMPTATION_ITEMS));

        this.tasks.addTask(7, new  EntityAIImprovedFollowParent(this, 1.25D, EntityCow.class));
        this.tasks.addTask(8, new EntityAIItemSearch(this, 1.0D));
        this.tasks.addTask(9, new EntityAIEatGrass(this));
        this.tasks.addTask(9, new EntityAIGrainEater(this));
        this.tasks.addTask(10, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(12, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true, new Class[0]));
    }

    @Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
    }

    @Override
	protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_COW_AMBIENT;
    }

    protected SoundEvent getHurtSound()
    {
        return SoundEvents.ENTITY_COW_HURT;
    }

    @Override
	protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_COW_DEATH;
    }

    @Override
	protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
	protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
	@Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_COW;
    }


    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
        
    	//breeding item overrides
        if (stack != null)
        {
        	if (Config.enableAdvancedStats == true)
        	{
        		if (this.getHunger() < foodMax - 0.5F)
        		{
	        		if  (Config.enableWildBreeding == true)
	        		{
	                    if (this.isBreedingItem(stack)  && !(this.isChild()) && !(this.isInLove()) )
	                    {
	                        this.consumeItemFromStack(player, stack);
	                        return true;
	                    }
	                    else if (this.isBreedingItem(stack))
	                    {
	                        this.consumeItemFromStack(player, stack);
	                        return true;
	                    }
	        		}
	        		else
	        		{
	                    if (this.isBreedingItem(stack) && !(this.isChild()) && !(this.isInLove()) )
	                    {
	                        this.consumeItemFromStack(player, stack);
	                        this.setInLove(player);
	                        return true;
	                    }
	                    else if (this.isBreedingItem(stack))
	                    {
	                        this.consumeItemFromStack(player, stack);
	                        return true;
	                    }
	        		}
        		}
        		else if (this.isBreedingItem(stack) && Config.enableWildBreeding == true)
        		{
        			return false;
        		}

                if (this.isEdible(stack))
                {
                	if  (this.getHunger() <  foodMax - 0.5F)
                	{
    	                this.consumeItemFromStack(player, stack);
    	                return true;
                	}
                	else return false;
                }
                if (stack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild())
                {
                	if ( this.getSex() == 0)
                	{
                	}
                	else	
                	{
                		player.playSound(SoundEvents.ENTITY_COW_HURT, 1.0F, 1.0F);
                		this.setHappiness(this.getHappiness()-3);
                	}
                	return false;
                }
        	}
            
        }
        //
        
        
        //override of EntityAgeable to allow varied growth times
        if (stack != null && stack.getItem() == Items.SPAWN_EGG)
        {
            ItemStack itemstack = player.getHeldItem(hand);
            if (!this.world.isRemote)
            {
                Class <? extends Entity > oclass = EntityList.getClass(ItemMonsterPlacer.getNamedIdFrom(itemstack));

                if (oclass != null && this.getClass() == oclass)
                {
                    EntityAgeable entityageable = this.createChild(this);

                    if (entityageable != null)
                    {
                        entityageable.setGrowingAge(childAge);
                        entityageable.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
                        this.world.spawnEntity(entityageable);

                        if (stack.hasDisplayName())
                        {
                            entityageable.setCustomNameTag(stack.getDisplayName());
                        }

                        if (!player.capabilities.isCreativeMode)
                        {
                            stack.setCount(stack.getCount() -1);
                        }
                    }
                }
            }

            return true;
        } //
        else
        {
            return super.processInteract(player, hand);
        }
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

        	entitynewcow = new EntityAurochs(this.world);
        
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
	        //entitynewcow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(d1 / (1.5D + (this.rand.nextDouble() * 1.0D)));
	        if (entitynewcow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() > 30.0D) entitynewcow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
	        if (entitynewcow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() < 20.0D) entitynewcow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	        
	        entitynewcow.setMaxMilk((this.getMaxMilk() + entitycow.getMaxMilk()) /2);
	        if (this.rand.nextInt(3) == 0) entitynewcow.setMaxMilk(entitynewcow.getMaxMilk()+this.rand.nextInt(3) - 1);
	        entitynewcow.setMaxMilk(MathHelper.clamp(entitynewcow.getMaxMilk(), 0, 9));
	        entitynewcow.setMilkTimer(0);
	        entitynewcow.setCurMilk(0);
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
		//entitynewcow.oldAge = 0;
        
        return entityreturncow;
    }
    //


    @Override
	public float getEyeHeight()
    {
        return this.isChild() ? this.height : 1.3F;
    }
    
    
    //Getters and setters
    @Override
	public int getSex()
    {
        return this.dataManager.get(SEX).intValue();
    }
    
    @Override
	public void setSex(int sexIn)
    {
        this.dataManager.set(SEX, Integer.valueOf(sexIn));
    }

    @Override
	public float getHunger()
    {
        return this.dataManager.get(HUNGER).floatValue();
    }
    
    @Override
	public void setHunger(float hungerIn)
    {
    	//hungerIn = MathHelper.clamp_float(hungerIn, 0.0F, foodMax);
    	if (hungerIn > foodMax) hungerIn = foodMax;
        this.dataManager.set(HUNGER, Float.valueOf(hungerIn));
    }
    
    @Override
	public int getTamability()
    {

        return this.dataManager.get(TAMABILITY).intValue();
    }
    
    @Override
	public void setTamability(int tamabilityIn)
    {
        this.dataManager.set(TAMABILITY, Integer.valueOf(tamabilityIn));
    }

    @Override
	public int getHappiness()
    {
        return this.dataManager.get(HAPPINESS).intValue();
    }
    
    @Override
	public void setHappiness(int happinessIn)
    {
    	happinessIn = MathHelper.clamp(happinessIn, -100, 100);
    	if (happinessIn > 100) happinessIn = 100;
    	if (happinessIn < -100) happinessIn = -100;
        this.dataManager.set(HAPPINESS, Integer.valueOf(happinessIn));
    }

    //Species Getters and setters
    @Override
	public int getMaxMilk()
    {
        return this.dataManager.get(MAXMILK).intValue();
    }
    
    @Override
	public void setMaxMilk(int In)
    {
        this.dataManager.set(MAXMILK, Integer.valueOf(In));
    }
    
    @Override
	public void setCurMilk(int In)
    {
        this.dataManager.set(CURMILK, Integer.valueOf(In));
    }
    public int getCurMilk()
    {
        return this.dataManager.get(CURMILK).intValue();
    }
    
    @Override
	public void setMilkTimer(int In)
    {
        this.dataManager.set(MILK_TIMER, Integer.valueOf(In));
    }
    public int getMilkTimer()
    {
        return this.dataManager.get(MILK_TIMER).intValue();
    }

    /**
     * Returns randomized max health
     */
    private float getModifiedMaxHealth()
    {
        return 20.0F + this.rand.nextInt(5) + this.rand.nextInt(5);
    }

    @Override
	@Nullable
    public UUID getOwnerId()
    {
        return (UUID)((Optional<?>)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
    }

    @Override
	public void setOwnerId(@Nullable UUID uniqueId)
    {
        this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(uniqueId));
    }

    @Override
	public int getTemper()
    {
        return this.temper;
    }

    @Override
	public void setTemper(int temperIn)
    {
        this.temper = temperIn;
    }

    @Override
	public int increaseTemper(int p_110198_1_)
    {
        int i = MathHelper.clamp(this.getTemper() + p_110198_1_, 0, this.getMaxTemper());
        this.setTemper(i);
        return i;
    }

    @Override
	public int getMaxTemper()
    {
        return 75 - ( this.getTamability() * 5 );        	
    }

    @Override
	public boolean isTame()
    {
        return (this.getOwnerId() != null);
    }

    @Override
	public void setTamed(boolean tamed)
    {
        //new
        //this.setupTamedAI();
    }


    @Override
	public boolean setTamedBy(EntityPlayer player)
    {
        this.setOwnerId(player.getUniqueID());
        this.setTamed(true);
        return true;
    }

    protected void setupTamedAI()
    {
        if (this.avoidEntity == null)
        {
            this.avoidEntity = new EntityAIAvoidEntity<EntityPlayer>(this, EntityPlayer.class, 16.0F, 1.0D, 2.0D);
        }
        if (this.aiPanic == null)
        {
            this.aiPanic = new EntityAIPanic(this, 1.75D);
        }
        if (this.aiAttack == null)
        {
            this.aiAttack = new EntityAIAttackMelee(this, 1.75D, false);
        }
        if (this.aiHerd == null)
        {
            this.aiHerd =  new EntityAIHerdMovement(this, 1.0D);
        }

        this.tasks.removeTask(this.avoidEntity);
        this.tasks.removeTask(this.aiPanic);
        this.tasks.removeTask(this.aiAttack);
        this.tasks.removeTask(this.aiHerd);
        
        if (this.isChild())
    	{
        	this.tasks.addTask(2, this.aiPanic);
        	return;
    	}
        
        this.tasks.addTask(3, this.aiHerd);
        
        if (!this.isTame())
        {
        	if (this.getHappiness() <= -50)
        	{
        		this.tasks.addTask(6, this.avoidEntity);        		
        	}
            
    		this.tasks.addTask(1, this.aiAttack);
        }
    }

    
    //////////////////////////////
    ///init and save

    @Override
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(DATA_STRENGTH_ID, Integer.valueOf(0));
        this.dataManager.register(OLD_AGE, Integer.valueOf(0));
        this.dataManager.register(SEX, Integer.valueOf(0));
        this.dataManager.register(HUNGER, Float.valueOf(0));
        this.dataManager.register(HAPPINESS, Integer.valueOf(0));
        this.dataManager.register(TAMABILITY, Integer.valueOf(0));
        this.dataManager.register(MAXMILK, Integer.valueOf(0));
        this.dataManager.register(CURMILK, Integer.valueOf(0));
        this.dataManager.register(MILK_TIMER, Integer.valueOf(0));
        this.dataManager.register(OWNER_UNIQUE_ID, Optional.<UUID>absent());
        //this.dataManager.register(SADDLE, Boolean.valueOf(false));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
	public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        //compound.setBoolean("HasReproduced", this.getHasReproduced());
        //compound.setBoolean("Bred", this.isBreeding());
        compound.setInteger("OldAge", this.getOldAge());
        compound.setInteger("MilkCapacity", this.getCurMilk());
        compound.setInteger("MilkMaxCapacity", this.getMaxMilk());
        compound.setInteger("MilkTimer", this.getMilkTimer());
        compound.setInteger("Sex", this.getSex());
        compound.setFloat("Hunger", this.getHunger());
        compound.setInteger("Tamability", this.getTamability());
        compound.setInteger("Happiness", this.getHappiness());
        compound.setInteger("Temper", this.getTemper());
        
        compound.setInteger("Strength", this.getStrength());

        compound.setInteger("ParentTamability", this.parentTamability);
        compound.setInteger("ParentMaxMilk", this.parentMaxMilk);
        compound.setFloat("ParentMaxHealth", this.parentMaxHealth);
        
        compound.setInteger("PregnancyTime", this.getPregnancyTime());

        
        
        if (this.getOwnerId() != null)
        {
            compound.setString("OwnerUUID", this.getOwnerId().toString());
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
	public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        //this.setBreeding(compound.getBoolean("Bred"));
        //this.setHasReproduced(compound.getBoolean("HasReproduced"));
        this.setOldAge( compound.getInteger("OldAge"));                
        this.setCurMilk(compound.getInteger("MilkCapacity"));       
        this.setMaxMilk(compound.getInteger("MilkMaxCapacity"));       
        this.setMilkTimer(compound.getInteger("MilkTimer"));       
        this.setSex(compound.getInteger("Sex"));
        this.setHunger(compound.getInteger("Hunger"));
        this.setTamability(compound.getInteger("Tamability"));
        this.setHappiness(compound.getInteger("Happiness"));
        this.setTemper(compound.getInteger("Temper"));

        this.setStrength(compound.getInteger("Strength"));
        
        this.parentTamability = (compound.getInteger("ParentTamability"));
        this.parentMaxHealth =  (compound.getFloat("ParentMaxHealth"));
        this.parentMaxMilk =  (compound.getInteger("ParentMaxMilk"));

        this.pregnancyTime = (compound.getInteger("PregnancyTime"));
        
        String s;

        if (compound.hasKey("OwnerUUID", 8))
        {
            s = compound.getString("OwnerUUID");
        }
        else
        {
            String s1 = compound.getString("Owner");
            s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
        }

        if (!s.isEmpty())
        {
            this.setOwnerId(UUID.fromString(s));
        }
        //new
        this.setupTamedAI();

    }
    

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    @Override
	public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (otherAnimal instanceof EntityAurochs)
        {
        	IEntityAdvanced entitycow = (IEntityAdvanced)otherAnimal;

            if ( this.isInLove() && otherAnimal.isInLove() && (this.getSex() != entitycow.getSex() || (Config.enableSexes == false) ) )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    ////generation
    

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Override
	@Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
    	super.onInitialSpawn(difficulty, livingdata);

        int randroll = this.rand.nextInt(3);
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
    		this.dataManager.set(TAMABILITY, Integer.valueOf(-4));
		}
        else
        {
    		this.dataManager.set(TAMABILITY, Integer.valueOf(-5));
        }

		this.setHunger(foodMax);
		this.setHappiness(0);
		this.setMaxMilk(this.rand.nextInt(5) + this.rand.nextInt(6));
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
    /////
    
    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
    	this.setHappiness(this.getHappiness()-50);
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
	public boolean attackEntityAsMob(Entity entityIn)
    {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        int i = 0;

        if (entityIn instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag)
        {
            if (i > 0 && entityIn instanceof EntityLivingBase)
            {
                ((EntityLivingBase)entityIn).knockBack(this, i * 0.5F, MathHelper.sin(this.rotationYaw * 0.017453292F), (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                entityIn.setFire(j * 4);
            }

            if (entityIn instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : null;

                if (itemstack != null && itemstack1 != null && itemstack.getItem() instanceof ItemAxe && itemstack1.getItem() == Items.SHIELD)
                {
                    float f1 = 0.25F + EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                    if (this.rand.nextFloat() < f1)
                    {
                        entityplayer.getCooldownTracker().setCooldown(Items.SHIELD, 100);
                        this.world.setEntityState(entityplayer, (byte)30);
                    }
                }
            }

            this.applyEnchantments(this, entityIn);
        }

        return flag;
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

		if (this.groupTimer >= 750)
		{
	    	this.setupTamedAI();
	        Random random = this.getRNG();
	    	if (Config.enableAdvancedStats == true)
	    	{
	    		List<EntityAnimal> list = this.world.<EntityAnimal>getEntitiesWithinAABB(AbstractHorse.class, this.getEntityBoundingBox().grow(8.0D));
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
		        if (list.size() >= 2 && list.size() <= this.getPreferedGroupSize() * 2)
		        {
		        	if (Config.enableWildBreeding == true && !(this.isChild()) && !(this.isInLove()) && (this.getGrowingAge() == 0) )
		        	{
		        		if ( (this.getHappiness() + breedchance) >= random.nextInt(100)) this.setInLove(null);
		        	}
		        }	
	    	}
			this.groupTimer = random.nextInt(10);
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
        
	        //hungerprocess
	        this.setHunger(this.getHunger() - ((this.getMaxHealth() + this.getMaxMilk() + this.getStrength()) / 50000) );
	        
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

	/**
     * Decreases ItemStack size by one
     */
    @Override
    protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
    {
    	//new
		if (Config.enableAdvancedStats == true)
		{
	    	if (stack.getItem() instanceof ItemFood)
	    	{
	    		ItemFood eatenFood = (ItemFood)stack.getItem();
	    		float newHunger = this.getHunger() + eatenFood.getHealAmount(stack) + eatenFood.getSaturationModifier(stack); 
	    		int newHappiness = (int) (this.getHappiness() + eatenFood.getHealAmount(stack) + eatenFood.getSaturationModifier(stack)); 

                if (!this.isTame() && this.getTemper() < this.getMaxTemper())
                {
                    this.increaseTemper(eatenFood.getHealAmount(stack)+this.rand.nextInt(5));
                    if (this.getTemper() >= this.getMaxTemper())
                    {
                    	this.setTamedBy(player);
                        double d0 = this.rand.nextGaussian() * 0.025D;
                        double d1 = this.rand.nextGaussian() * 0.025D;
                        double d2 = this.rand.nextGaussian() * 0.025D;
                        this.playSound(SoundEvents.ENTITY_HORSE_EAT, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                    	this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2, new int[0]);
                    	this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2, new int[0]);
                    	this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2, new int[0]);
                    }
                    else
                    {
                        double d0 = this.rand.nextGaussian() * 0.025D;
                        double d1 = this.rand.nextGaussian() * 0.025D;
                        double d2 = this.rand.nextGaussian() * 0.025D;
                        this.playSound(SoundEvents.ENTITY_HORSE_EAT, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                    	this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2, new int[0]);
                    	
                    }
                }
                else
                {
                    double d0 = this.rand.nextGaussian() * 0.025D;
                    double d1 = this.rand.nextGaussian() * 0.025D;
                    double d2 = this.rand.nextGaussian() * 0.025D;
                    this.playSound(SoundEvents.ENTITY_HORSE_EAT, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                	this.world.spawnParticle(EnumParticleTypes.SPELL, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2, new int[0]);
                }
                
	    		//varies by entity
	            
	            this.setHunger(newHunger);
	            this.setHappiness(newHappiness);
	
	    	}
			else if (this.isEdible(stack))
			{
				if (!this.isTame() && this.getTemper() < this.getMaxTemper())
                {
                    this.increaseTemper(5+this.rand.nextInt(5));
                    if (this.getTemper() >= this.getMaxTemper())
                    {
                    	this.setTamedBy(player);
                        double d0 = this.rand.nextGaussian() * 0.025D;
                        double d1 = this.rand.nextGaussian() * 0.025D;
                        double d2 = this.rand.nextGaussian() * 0.025D;
                        this.playSound(SoundEvents.ENTITY_HORSE_EAT, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                    	this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2, new int[0]);
                    	this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2, new int[0]);
                    	this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2, new int[0]);
                    }
                    else
                    {
                        double d0 = this.rand.nextGaussian() * 0.025D;
                        double d1 = this.rand.nextGaussian() * 0.025D;
                        double d2 = this.rand.nextGaussian() * 0.025D;
                        this.playSound(SoundEvents.ENTITY_HORSE_EAT, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                    	this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2, new int[0]);
                    	
                    }
                }
                else
                {
                    double d0 = this.rand.nextGaussian() * 0.025D;
                    double d1 = this.rand.nextGaussian() * 0.025D;
                    double d2 = this.rand.nextGaussian() * 0.025D;
                    this.playSound(SoundEvents.ENTITY_HORSE_EAT, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                	this.world.spawnParticle(EnumParticleTypes.SPELL, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2, new int[0]);
                }
				this.eatGrassBonus();
			}
    	}
    	//
    	
        if (!player.capabilities.isCreativeMode)
        {
            stack.setCount(stack.getCount() -1);
        }
    }

    /**
     * This function applies the benefits of growing back wool and faster growing up to the acting entity. (This
     * function is used in the AIEatGrass)
     */
    @Override
	public void eatGrassBonus()
    {
        this.setHunger(this.getHunger() + 4);
        this.setHappiness(this.getHappiness() + 1);
        return;
    }
    
    public boolean isEdible(ItemStack stack)
    {
    	if (EntityAurochs.TEMPTATION_ITEMS.contains(stack.getItem())) return true;
    	return false;
    }
 
    @Override
	public boolean isEdible(EntityItem entityitem)
	{
    	if (EntityAurochs.TEMPTATION_ITEMS.contains(entityitem.getItem().getItem())) return true;
    	return false;
	}
	
    @Override
	public int getOldAge()
    {
        return this.dataManager.get(OLD_AGE).intValue();
    }
    
    @Override
	public void setOldAge(int age)
    {
        this.dataManager.set(OLD_AGE, Integer.valueOf(age));
    }    
    
	@Override
	public int getChildAge() 
	{
		return EntityAurochs.childAge;
	}
	
    @Override
	public float getGrowthSize()
    {
    	if (this.isChild())
    	{
	    	float growth = EntityAurochs.childAge + this.getOldAge();
	    	float ratio = growth / EntityAurochs.childAge;
	    	return 1.0F - ratio;
    	}
    	return 1.0F;
    }
    
    @Override
	public int getPreferedGroupSize()
    {
    	return 5;
    }

    @Override
	public float getFoodMax()
    {
        return foodMax;
    }

    //
    /**
     * Returns true if the cow is saddled.
     */
    public boolean getSaddled()
    {
        return this.dataManager.get(SADDLE).booleanValue();
    }

    /**
     * Set or remove the saddle of the cow.
     */
    public void setSaddled(boolean saddled)
    {
        if (saddled)
        {
            this.dataManager.set(SADDLE, Boolean.valueOf(true));
        }
        else
        {
            this.dataManager.set(SADDLE, Boolean.valueOf(false));
        }
    }
    /////////////////////
 

    @Override
	public int getPregnancyTime()
    {
        return this.pregnancyTime;
    }
    
    @Override
	public void setPregnancyTime(int age)
    {
        this.pregnancyTime = age;
    }
    
    @Override
	public void getOtherParentData(IEntityAdvanced parent)
    {
    	if (parent instanceof EntityAurochs)
    	{
    		EntityAurochs otherParent = (EntityAurochs) parent;
            this.parentTamability = otherParent.getTamability();
            this.parentMaxHealth = otherParent.getMaxHealth();
            this.parentMaxMilk = otherParent.getMaxMilk();
    	}
    	else //failsafe
    	{
            this.parentTamability = this.getTamability();
            this.parentMaxHealth = this.getMaxHealth();
            this.parentMaxMilk = this.getMaxMilk();
    	}
        return;
    }
    
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
        
        	entitynewcow = new EntityAurochs(this.world);
        
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
	        if (entityreturncow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() > 30.0D) entityreturncow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
	        if (entityreturncow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() < 20.0D) entityreturncow.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	        
	        entitynewcow.setMaxMilk((this.getMaxMilk() + this.parentMaxMilk) /2);
	        if (this.rand.nextInt(3) == 0) entitynewcow.setMaxMilk(entitynewcow.getMaxMilk()+this.rand.nextInt(3) - 1);
	        entitynewcow.setMaxMilk(MathHelper.clamp(entitynewcow.getMaxMilk(), 0, 9));
	        entitynewcow.setMilkTimer(0);
	        entitynewcow.setCurMilk(0);;
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
		//entitynewcow.oldAge = 0;
        
        return entityreturncow;
    }

    private void setStrength(int strengthIn)
    {
        this.dataManager.set(DATA_STRENGTH_ID, Integer.valueOf(Math.max(1, Math.min(5, strengthIn))));
    }
//
//    private void setRandomStrength()
//    {
//        int i = this.rand.nextFloat() < 0.04F ? 5 : 3;
//        this.setStrength(1 + this.rand.nextInt(i));
//    }

    public int getStrength()
    {
        return this.dataManager.get(DATA_STRENGTH_ID).intValue();
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    @Override
    public boolean isBreedingItem(@Nullable ItemStack stack)
    {
        return stack != null && TEMPTATION_ITEMS.contains(stack.getItem());
    }

       
    
    

    class AIAttackPlayer extends EntityAINearestAttackableTarget<EntityPlayer>
    {
        public AIAttackPlayer()
        {
            super(EntityAurochs.this, EntityPlayer.class, 20, true, true, (Predicate)null);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
		public boolean shouldExecute()
        {
            if (EntityAurochs.this.isChild())
            {
                return false;
            }
            else
            {
                if (super.shouldExecute())
                {
                    for (EntityPolarBear entitypolarbear : EntityAurochs.this.world.getEntitiesWithinAABB(EntityPolarBear.class, EntityAurochs.this.getEntityBoundingBox().grow(8.0D, 4.0D, 8.0D)))
                    {
                        if (entitypolarbear.isChild())
                        {
                            return true;
                        }
                    }
                }

                EntityAurochs.this.setAttackTarget((EntityLivingBase)null);
                return false;
            }
        }

        @Override
		protected double getTargetDistance()
        {
            return super.getTargetDistance() * 0.5D;
        }
    }
    

}