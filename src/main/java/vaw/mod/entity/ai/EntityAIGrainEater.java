package vaw.mod.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIGrainEater extends EntityAIMoveToBlock
{
    private final EntityAnimal animal;
    private boolean wantsToRaid;
    private boolean canRaid;

    public EntityAIGrainEater(EntityAnimal animalIn)
    {
        super(animalIn, 0.699999988079071D, 16);
        this.animal = animalIn;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
	public boolean shouldExecute()
    {
        if (this.runDelay <= 0)
        {
            if (!this.animal.world.getGameRules().getBoolean("mobGriefing"))
            {
                return false;
            }

            this.canRaid = false;
            this.wantsToRaid = true;
        }

        return super.shouldExecute();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
	public void startExecuting()
    {
        super.startExecuting();
    }

    /**
     * Resets the task
     */
    @Override
	public void resetTask()
    {
        super.resetTask();
    }

    /**
     * Updates the task
     */
    @Override
	public void updateTask()
    {
        super.updateTask();
        this.animal.getLookHelper().setLookPosition(this.destinationBlock.getX() + 0.5D, this.destinationBlock.getY() + 1, this.destinationBlock.getZ() + 0.5D, 10.0F, this.animal.getVerticalFaceSpeed());

        if (this.getIsAboveDestination())
        {
            World world = this.animal.world;
            BlockPos blockpos = this.destinationBlock.up();
            IBlockState iblockstate = world.getBlockState(blockpos);
            Block block = iblockstate.getBlock();

            if (this.canRaid && block instanceof BlockCrops)
            {
                world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
                world.destroyBlock(blockpos, true);
                this.animal.eatGrassBonus();
            }

            this.canRaid = false;
            this.runDelay = 10;
        }
    }

    /**
     * Return true to set given position as destination
     */
    @Override
	protected boolean shouldMoveTo(World worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos).getBlock();

        if (block == Blocks.FARMLAND && this.wantsToRaid && !this.canRaid)
        {
            pos = pos.up();
            IBlockState iblockstate = worldIn.getBlockState(pos);
            block = iblockstate.getBlock();

            if (block instanceof BlockCrops && ((BlockCrops)block).isMaxAge(iblockstate))
            {
                this.canRaid = true;
                return true;
            }
        }

        return false;
    }
}
