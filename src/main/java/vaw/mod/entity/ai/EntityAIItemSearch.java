package vaw.mod.entity.ai;

import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemFood;
import vaw.mod.entity.IEntityAdvanced;
import vaw.mod.util.Config;

public class EntityAIItemSearch extends EntityAIBase

{
    /** The child that is following its parent. */
    EntityAnimal thisAnimal;
    EntityItem foundItem;
    double moveSpeed;
    private int delayCounter;

    public EntityAIItemSearch(EntityAnimal animal, double speed)
    {
        this.thisAnimal = animal;
        this.moveSpeed = speed;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        IEntityAdvanced animal = (IEntityAdvanced) this.thisAnimal;
        if (animal.getHunger() > animal.getFoodMax() - 0.15F) return false;
    	List<EntityItem> items = thisAnimal.world.getEntitiesWithinAABB(EntityItem.class, this.thisAnimal.getEntityBoundingBox().expand(5.0D, 4.0D, 5.0D));
    	EntityItem entityitem = null;
        double d0 = Double.MAX_VALUE;

    		
        for (EntityItem entityitem1 : items)
        {
            if (entityitem1 != null)
            {
                double d1 = this.thisAnimal.getDistanceSq(entityitem1);
                if (d1 <= d0 && animal.isEdible(entityitem1))
                {
                    d0 = d1;
                    entityitem = entityitem1;
                }
            }
        }

        if (entityitem == null)
        {
            return false;
        }
        else
        {
            this.foundItem = entityitem;
            return true;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
        this.delayCounter = 0;
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask()
    {
        this.foundItem = null;
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask()
    {
    	if (this.foundItem == null)
    	{
    		return;
    	}
    	if (!this.foundItem.isEntityAlive())
		{
            this.resetTask();
			return;
		}
        IEntityAdvanced animal = (IEntityAdvanced) this.thisAnimal;
        //if (animal.getHunger() > animal.getFoodMax() - 0.15F) return;
        
    	double d0 = this.thisAnimal.getDistanceSq(this.foundItem);
        if (d0 <= 0.5D)
        {
//        	if (Config.enableAdvancedStats == true)
//    		{
//    	    	if (this.foundItem.getItem().getItem() instanceof ItemFood)
//    	    	{
//    	    		ItemFood eatenFood = (ItemFood)this.foundItem.getItem().getItem();
//
//    	    		float newHunger = animal.getHunger() + eatenFood.getHealAmount(this.foundItem.getItem()) + eatenFood.getSaturationModifier(this.foundItem.getItem()); 
//    	    		int newHappiness = (int) (animal.getHappiness() + eatenFood.getHealAmount(this.foundItem.getItem()) + eatenFood.getSaturationModifier(this.foundItem.getItem())); 
//
//    	    		animal.setHunger(newHunger*this.foundItem.getItem().getCount());
//    	    		animal.setHappiness(newHappiness*this.foundItem.getItem().getCount());    	
//    	    	}
//    	    	else
//    	    	{
//    	    		//stacksize loop here
//    	            this.thisAnimal.eatGrassBonus();
//    	    	}
//	    	}        	
            this.foundItem.setDead();
            this.resetTask();
        }
        else if (--this.delayCounter <= 0)
        {
            this.delayCounter = 10;
            this.thisAnimal.getNavigator().tryMoveToXYZ(this.foundItem.posX, this.foundItem.posY, this.foundItem.posZ, this.moveSpeed);
            //this.thisAnimal.getNavigator().tryMoveToEntityLiving(this.foundItem, this.moveSpeed);
        }
    }
}