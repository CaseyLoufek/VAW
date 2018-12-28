package vaw.mod.entity.ai;


import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import vaw.mod.entity.IEntityAdvanced;

public class EntityAIImprovedFollowParent extends EntityAIBase
{
    /** The child that is following its parent. */
    EntityAnimal childAnimal;
    EntityAnimal parentAnimal;
    double moveSpeed;
    private int delayCounter;
    @SuppressWarnings("rawtypes")
	private Class parentKind;

    public EntityAIImprovedFollowParent(EntityAnimal animal, double speed, @SuppressWarnings("rawtypes") Class animalKind)
    {
        this.childAnimal = animal;
        this.moveSpeed = speed;
        this.parentKind = animalKind;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
	public boolean shouldExecute()
    {
        if (this.childAnimal.getGrowingAge() >= 0)
        {
            return false;
        }
        else
        {
            List<EntityAnimal> list = this.childAnimal.world.<EntityAnimal>getEntitiesWithinAABB(this.parentKind, this.childAnimal.getEntityBoundingBox().grow(8.0D, 4.0D, 8.0D));
            EntityAnimal entityanimal = null;
            double d0 = Double.MAX_VALUE;

            for (EntityAnimal entityanimal1 : list)
            {
                if (entityanimal1.getGrowingAge() >= 0)
                {
                    double d1 = this.childAnimal.getDistanceSq(entityanimal1);
                    IEntityAdvanced testParent = null;
                    if (entityanimal1 instanceof IEntityAdvanced) testParent = (IEntityAdvanced) entityanimal1;
            		
        			if (entityanimal == null)
        			{
                        d0 = d1;
                        entityanimal = entityanimal1;
        			}
        			else if (testParent != null && testParent.getSex() == 0)              		//prefer females
            		{
            			if ( this.childAnimal.getClass() == entityanimal1.getClass())   	//prefer species
                    	{
            				if (d0 > d1 || entityanimal.getClass() != this.childAnimal.getClass() )
            				{
                                d0 = d1;
                                entityanimal = entityanimal1;
            				}
            			}
            			else if (d0 > d1 && entityanimal.getClass() != this.childAnimal.getClass() )
            			{
                            d0 = d1;
                            entityanimal = entityanimal1;
            			}
                	}
                	
                }
            }

            if (entityanimal == null)
            {
                return false;
            }
            else if (d0 < 9.0D)
            {
                return false;
            }
            else
            {
                this.parentAnimal = entityanimal;
                return true;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
	public boolean shouldContinueExecuting()
    {
        if (this.childAnimal.getGrowingAge() >= 0)
        {
            return false;
        }
        else if (!this.parentAnimal.isEntityAlive())
        {
            return false;
        }
        else
        {
            double d0 = this.childAnimal.getDistanceSq(this.parentAnimal);
            return d0 >= 9.0D && d0 <= 256.0D;
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
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    @Override
	public void resetTask()
    {
        this.parentAnimal = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
	public void updateTask()
    {
        if (--this.delayCounter <= 0)
        {
            this.delayCounter = 10;
            this.childAnimal.getNavigator().tryMoveToEntityLiving(this.parentAnimal, this.moveSpeed);
        }
    }
}