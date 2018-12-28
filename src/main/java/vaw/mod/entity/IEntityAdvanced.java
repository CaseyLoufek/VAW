package vaw.mod.entity;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import vaw.mod.init.ItemInit;


//this is a general interface to cast the diverse Entity descedants too for use in AIs and so forth
public interface IEntityAdvanced 
{

    public static final Set<Item> MEAT = Sets.newHashSet(new Item[] {Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN});
    
    public static final Set<Item> FRUIT = Sets.newHashSet(new Item[] {Items.APPLE, ItemInit.PEACH, ItemInit.PEAR, ItemInit.PERSIMMON,
    		Items.GOLDEN_APPLE, Items.MELON, Items.SPECKLED_MELON});
    
    public static final Set<Item> ROOTS = Sets.newHashSet(new Item[] {Items.CARROT, Items.POTATO, Items.BEETROOT, Items.GOLDEN_CARROT});
        
	public float getFoodMax();

	public int getSex();
    
    public void setSex(int sexIn);

    public String getZoopediaName();
    
    public float getHunger();
    
    public void setHunger(float hungerIn);
    
    public int getTamability();
    
    public void setTamability(int tamabilityIn);

    public int getHappiness();
    
    public void setHappiness(int happinessIn);
    
    @Nullable
    //public UUID getOwnerUniqueId();

    //public void setOwnerUniqueId(@Nullable UUID uniqueId);

    public int getTemper();

    public void setTemper(int temperIn);

    public int increaseTemper(int p_110198_1_);

    public int getMaxTemper();

    public boolean isTame();

    public void setTamed(boolean tamed);

    public boolean setTamedBy(EntityPlayer player);
    

    
    @Nullable
    public UUID getOwnerId();

    public void setOwnerId(@Nullable UUID uniqueId);

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    public boolean canMateWith(EntityAnimal otherAnimal);

    /**
     * This function applies the benefits of growing back wool and faster growing up to the acting entity. (This
     * function is used in the AIEatGrass)
     */
    public void eatGrassBonus();

    /**
     * The age value may be negative or positive or zero. If it's negative, it get's incremented on each tick, if it's
     * positive, it get's decremented each tick. Don't confuse this with EntityLiving.getAge. With a negative value the
     * Entity is considered a child.
     */
    public int getOldAge();

    /**
     * The age value may be negative or positive or zero. If it's negative, it get's incremented on each tick, if it's
     * positive, it get's decremented each tick. With a negative value the Entity is considered a child.
     */
    public void setOldAge(int age);    
    
    public float getGrowthSize();

    public int getPreferedGroupSize();

	public IAttributeInstance getEntityAttribute(IAttribute maxHealth);

	public boolean isEdible(EntityItem entityitem);

	public int getChildAge();

	public boolean isInLove();

	public void getOtherParentData(IEntityAdvanced parent);
    
	public void setPregnancyTime(int i);
	
	public int getPregnancyTime();

    public float getZoopediaHealth();

    public float getZoopediaMaxHealth();
    
	//public void createChild();

}
