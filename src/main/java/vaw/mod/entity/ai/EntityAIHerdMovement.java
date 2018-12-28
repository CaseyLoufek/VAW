package vaw.mod.entity.ai;

import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;

public class EntityAIHerdMovement extends EntityAIBase

{
    /** The child that is following its parent. */
    EntityAnimal thisAnimal;
    EntityAnimal herdAnimal;
    double moveSpeed;
    private int delayCounter;

    public EntityAIHerdMovement(EntityAnimal animal, double speed)
    {
        this.thisAnimal = animal;
        this.moveSpeed = speed;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        List<EntityAnimal> list = this.thisAnimal.world.<EntityAnimal>getEntitiesWithinAABB(this.thisAnimal.getClass(), this.thisAnimal.getEntityBoundingBox().expand(9.0D, 9.0D, 9.0D));
        EntityAnimal entityanimal = null;
        double d0 = Double.MAX_VALUE;

        for (EntityAnimal entityanimal1 : list)
        {
                double d1 = this.thisAnimal.getDistance(entityanimal1);

                if (d1 <= d0 && !entityanimal1.isChild())
                {
                    d0 = d1;
                    entityanimal = entityanimal1;
                }
        }

        if (entityanimal == null)
        {
            return false;
        }
        else
        {
            this.herdAnimal = entityanimal;
            return true;
        }
    }


    @Override
	public boolean shouldContinueExecuting()
    {
    	if (!this.herdAnimal.isEntityAlive())
        {
            return false;
        }
        else
        {
            double d0 = this.thisAnimal.getDistanceSq(this.herdAnimal);
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
     * Resets the task
     */
    @Override
    public void resetTask()
    {
        this.herdAnimal = null;
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask()
    {
        if (--this.delayCounter <= 0)
        {
            this.delayCounter = 10;
            this.thisAnimal.getNavigator().tryMoveToEntityLiving(this.herdAnimal, this.moveSpeed);
        }
    }
}