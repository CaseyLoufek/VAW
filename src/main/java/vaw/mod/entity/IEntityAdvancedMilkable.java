package vaw.mod.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;


//this is a general interface to cast the diverse Entity descedants too for use in AIs and so forth
public interface IEntityAdvancedMilkable extends IEntityAdvanced
{

	@Override
	public int getSex();
	
    @Override
	public void setSex(int sexIn);

    @Override
	public float getHunger();
    
    @Override
	public void setHunger(float hungerIn);
    
    @Override
	public int getTamability();
    
    @Override
	public void setTamability(int tamabilityIn);

    @Override
	public int getHappiness();
    
    @Override
	public void setHappiness(int happinessIn);
    
    @Override
	public int getChildAge();
    
    @Override
	@Nullable
    public UUID getOwnerId();

    @Override
	public void setOwnerId(@Nullable UUID uniqueId);

    @Override
	public int getTemper();

    @Override
	public void setTemper(int temperIn);

    @Override
	public int increaseTemper(int p_110198_1_);

    @Override
	public int getMaxTemper();

    public boolean isTame();

    @Override
	public void setTamed(boolean tamed);

    @Override
	public boolean setTamedBy(EntityPlayer player);
    

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    @Override
	public boolean canMateWith(EntityAnimal otherAnimal);

    /**
     * This function applies the benefits of growing back wool and faster growing up to the acting entity. (This
     * function is used in the AIEatGrass)
     */
    @Override
	public void eatGrassBonus();

    /**
     * The age value may be negative or positive or zero. If it's negative, it get's incremented on each tick, if it's
     * positive, it get's decremented each tick. Don't confuse this with EntityLiving.getAge. With a negative value the
     * Entity is considered a child.
     */
    @Override
	public int getOldAge();

    /**
     * The age value may be negative or positive or zero. If it's negative, it get's incremented on each tick, if it's
     * positive, it get's decremented each tick. With a negative value the Entity is considered a child.
     */
    @Override
	public void setOldAge(int age);    
    
    @Override
	public float getGrowthSize();

    @Override
	public int getPreferedGroupSize();

	@Override
	public IAttributeInstance getEntityAttribute(IAttribute maxHealth);

	public int getMaxMilk();

	public void setMaxMilk(int i);

	public void setMilkTimer(int i);

	public void setCurMilk(int i);


}
