package vaw.mod.util.handlers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import vaw.mod.Main;
import vaw.mod.entity.IEntityAdvanced;
import vaw.mod.init.ItemInit;
import vaw.mod.objects.blocks.BlockHide;
import vaw.mod.objects.tools.ToolStake;
import vaw.mod.util.Config;

public class EventsHandler 
{
	@SubscribeEvent
	public void onSpawnAttempt(WorldEvent.PotentialSpawns event)
	{
		World world = event.getWorld();
		BlockPos oldpos = event.getPos();
		IBlockState state = world.getBlockState(oldpos);
		
		if (world.getBlockState(oldpos).getBlock() instanceof BlockBush || world.getBlockState(oldpos).getBlock() instanceof IPlantable )
		{
			BlockPos pos = new BlockPos(oldpos.getX(),oldpos.getY(),oldpos.getZ());
			pos.add(world.rand.nextInt(17) - 8,world.rand.nextInt(17) - 8,world.rand.nextInt(17) - 8);
			world.setBlockState(pos, state);
		}
		
	}
	
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event)
	{
		World world = event.world;
		//List Entity = world.getLoadedEntityList();
	}
	

	//Horse Hide drop, temporary
	@SubscribeEvent
	public void onLivingDropsEvent(LivingDropsEvent event) 
	{
	
	}
	
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event)
	{
		
		{
//			if(event.getEntity() instanceof EntitySkeleton || event.getEntity() instanceof EntityZombie || event.getEntity() instanceof EntitySpider || event.getEntity() instanceof EntitySlime  || event.getEntity() instanceof EntityWitch)
//			{
//		    	event.setCanceled(true);
//		    }
		}
		
	}
	
	

	@SubscribeEvent
	public void onEntityIntereact(EntityInteract event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntity();
			ItemStack itemstack = player.inventory.getCurrentItem();
			//use Zoopedia on new animals
			if (event.getTarget() != null && event.getTarget() instanceof IEntityAdvanced && itemstack != null && itemstack.getItem() == ItemInit.STAKE_WOOD)
			{
			        player.openGui(Main.instance, GUIHandler.ZOOPEDIA_GUI, player.world, event.getTarget().getEntityId(),(int)  player.posY,(int)  player.posZ);
			}
		}
		return;
	}	
		
	@SubscribeEvent
	public void harvestDrops(BlockEvent.HarvestDropsEvent event) 
	{

		if(event.getHarvester() !=null)
		{
			if(event.getHarvester().getHeldItemMainhand().getItem() !=null)
			{
				if (event.getHarvester().getHeldItemMainhand().getItem() instanceof ToolStake)
				{
					if (event.getState().getBlock() == Blocks.VINE || event.getState().getBlock() == Blocks.REEDS ) 
					{
						event.getDrops().clear();
						event.getDrops().add(new ItemStack(ItemInit.STAKE_WOOD, 1));
					}
					if (event.getState().getBlock() == Blocks.DOUBLE_PLANT && event.getState().getBlock().getMetaFromState(event.getState()) == 2)
					{
						event.getDrops().clear();
						event.getDrops().add(new ItemStack(ItemInit.STAKE_WOOD, 1));
					}
				}			
			}
			
		}
			
		//Leaves drop sticks
		if (Config.leavesDropSticks == true)
		{
			if (event.getState().getBlock().isLeaves(event.getState(), event.getWorld(), event.getPos()) && Math.random() > 0.9D) 
			{
				event.getDrops().add(new ItemStack(Items.STICK, 1));
			}
		}


		//Stop players from harvesting logs without the correct tool.
		if (Config.noPunchingWood == true)
		{
			if (isPlayerHarvestingLogWithoutCorrectTool(event.getState(), event.getWorld(), event.getPos(), event.getHarvester())) 
			{
				event.getDrops().clear();
			}
		}
		
	}
	

    @SubscribeEvent
    public void onItemDecay(ItemExpireEvent event) {
        if (Config.replantBlocks == true)
        {
            EntityItem ent = event.getEntityItem();
            if (ent.motionX < 0.001 && ent.motionZ < 0.001) // stationary 
            {
                ItemStack item = ent.getItem();
                if(item!=null && item.getCount() > 0 && item.getItem() instanceof ItemBlock)
                {
                    Block id = ((ItemBlock) item.getItem()).getBlock();
                    if (!(id instanceof BlockHide))
                    {
                        BlockPos pos = new BlockPos(ent);
                        if(id.canPlaceBlockAt(ent.world, pos)) {
                            IBlockState state = id.getStateFromMeta(item.getItem().getMetadata(item.getMetadata()));
                            if(ent.world.setBlockState(pos, state)){
                                item.setCount(item.getCount() - 1);
                            }
                        }
                    }
                }
            }
        }
    }

                        
    @SubscribeEvent
    public void onItemUsed(PlayerInteractEvent.RightClickItem event) 
    {
    	EntityLivingBase playerIn = event.getEntityLiving();
    	
    	if (playerIn instanceof EntityPlayer)
    	{
    		ItemStack items = event.getItemStack();
    		EntityPlayer player = (EntityPlayer) playerIn;
        	
        	if (items.getItem() == Items.BOWL)
        	{
        		World worldIn = playerIn.getEntityWorld();
        	    RayTraceResult raytraceresult = getNearestBlockWithDefaultReachDistance(event.getWorld(), event.getEntityPlayer(), true, false, false);
                if (raytraceresult != null)
                {
                    if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
                    {
                        BlockPos blockpos = raytraceresult.getBlockPos();
                        if (event.getEntity().getEntityWorld().getBlockState(blockpos).getMaterial() == Material.WATER)
                        {
                            worldIn.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        	items.shrink(1);
                        	//player.addStat(StatList.getObjectUseStats(Items.BOWL));
                            
                        	ItemStack stack = new ItemStack(ItemInit.STAKE_WOOD);
                        	
                        	EnumHand hand = event.getHand();
                        	if (stack.isEmpty())
                            {
                                playerIn.setHeldItem(hand, stack);
                            }
                            else if (!player.inventory.addItemStackToInventory(stack))
                            {
                            	   player.dropItem(stack, false);
                            }
                            else if (playerIn instanceof EntityPlayerMP)
                            {
                                ((EntityPlayerMP)playerIn).sendContainerToPlayer(player.inventoryContainer);
                            }

                        }
                        
                        if (event.getEntity().getEntityWorld().getBlockState(blockpos).getBlock() == Blocks.CAULDRON)
                        {
                        	if( Blocks.CAULDRON.getMetaFromState(event.getEntity().getEntityWorld().getBlockState(blockpos)) > 0)
                        	{
                                player.addStat(StatList.CAULDRON_USED);
                                
                            	items.shrink(1);
                            	ItemStack stack = new ItemStack(ItemInit.STAKE_WOOD);
                            	
                            	EnumHand hand = event.getHand();
                            	if (stack.isEmpty())
                                {
                                    playerIn.setHeldItem(hand, stack);
                                }
                                else if (!player.inventory.addItemStackToInventory(stack))
                                {
                                	   player.dropItem(stack, false);
                                }
                                else if (playerIn instanceof EntityPlayerMP)
                                {
                                    ((EntityPlayerMP)playerIn).sendContainerToPlayer(player.inventoryContainer);
                                }

                                worldIn.playSound((EntityPlayer)null, blockpos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                                Blocks.CAULDRON.setWaterLevel(worldIn, blockpos, event.getEntity().getEntityWorld().getBlockState(blockpos), Blocks.CAULDRON.getMetaFromState(event.getEntity().getEntityWorld().getBlockState(blockpos)) - 1);
                        		
                        	}
                        }


                    }
                }
        	}
        	
        	//refill cauldron
        	if (items.getItem() == ItemInit.STAKE_WOOD)
        	{
        		World worldIn = playerIn.getEntityWorld();
        	    RayTraceResult raytraceresult = getNearestBlockWithDefaultReachDistance(event.getWorld(), event.getEntityPlayer(), true, false, false);
                if (raytraceresult != null)
                {
                    if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
                    {
                        BlockPos blockpos = raytraceresult.getBlockPos();
                        if (event.getEntity().getEntityWorld().getBlockState(blockpos).getBlock() == Blocks.CAULDRON)
                        {
                        	if( Blocks.CAULDRON.getMetaFromState(event.getEntity().getEntityWorld().getBlockState(blockpos)) < 3)
                        	{
                                player.addStat(StatList.CAULDRON_USED);
                                
                            	items.shrink(1);
                            	ItemStack stack = new ItemStack(Items.BOWL);
                            	
                            	EnumHand hand = event.getHand();
                            	if (stack.isEmpty())
                                {
                                    playerIn.setHeldItem(hand, stack);
                                }
                                else if (!player.inventory.addItemStackToInventory(stack))
                                {
                                	   player.dropItem(stack, false);
                                }
                                else if (playerIn instanceof EntityPlayerMP)
                                {
                                    ((EntityPlayerMP)playerIn).sendContainerToPlayer(player.inventoryContainer);
                                }

                                worldIn.playSound((EntityPlayer)null, blockpos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                                Blocks.CAULDRON.setWaterLevel(worldIn, blockpos, event.getEntity().getEntityWorld().getBlockState(blockpos), Blocks.CAULDRON.getMetaFromState(event.getEntity().getEntityWorld().getBlockState(blockpos)) + 1);
                        		
                        	}
                        }


                    }
                }
        	}
    	}
    }
    
	private static RayTraceResult getMovingObjectPosWithReachDistance(World world, EntityPlayer player, double distance, boolean p1, boolean p2, boolean p3) {
		float f = player.rotationPitch;
		float f1 = player.rotationYaw;
		double d0 = player.posX;
		double d1 = player.posY + player.getEyeHeight();
		double d2 = player.posZ;
		Vec3d vec3 = new Vec3d(d0, d1, d2);
		float f2 = MathHelper.cos(-f1 * 0.017453292F - (float) Math.PI);
		float f3 = MathHelper.sin(-f1 * 0.017453292F - (float) Math.PI);
		float f4 = -MathHelper.cos(-f * 0.017453292F);
		float f5 = MathHelper.sin(-f * 0.017453292F);
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		Vec3d vec31 = vec3.addVector(f6 * distance, f5 * distance, f7 * distance);
		return world.rayTraceBlocks(vec3, vec31, p1, p2, p3);
	}

	public static RayTraceResult getNearestBlockWithDefaultReachDistance(World world, EntityPlayer player) {
		return getNearestBlockWithDefaultReachDistance(world, player, false, true, false);
	}

	public static RayTraceResult getNearestBlockWithDefaultReachDistance(World world, EntityPlayer player, boolean stopOnLiquids, boolean ignoreBlockWithoutBoundingBox, boolean returnLastUncollidableBlock) {
		return getMovingObjectPosWithReachDistance(world, player, player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue(), stopOnLiquids, ignoreBlockWithoutBoundingBox, returnLastUncollidableBlock);
	}
    
	
	/**
	 * Can the tool harvest the block?
	 * <p>
	 * Adapted from ForgeHooks.canToolHarvestBlock, but has an IBlockState parameter instead of getting the IBlockState
	 * from an IBlockAccess and a BlockPos.
	 *
	 * @param state The block's state
	 * @param stack The tool ItemStack
	 * @return True if the tool can harvest the block
	 */
	private boolean canToolHarvestBlock(IBlockState state, ItemStack stack, EntityPlayer player) {
		String tool = state.getBlock().getHarvestTool(state);
		return stack != null && tool != null
				&& stack.getItem().getHarvestLevel(stack, tool, player, state) >= state.getBlock().getHarvestLevel(state);
	}

	/**
	 * Is the player harvesting a log block without the correct tool?
	 *
	 * @param state       The block's state
	 * @param blockAccess The world that the block is in
	 * @param pos         The position of the block
	 * @param player      The player harvesting the block
	 * @return True if the block is a log, the player isn't in creative mode and the player doesn't have the correct tool equipped
	 */
	private boolean isPlayerHarvestingLogWithoutCorrectTool(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EntityPlayer player)
	{
		return player != null && !player.capabilities.isCreativeMode
				&& state.getBlock().isWood(blockAccess, pos)
				&& !canToolHarvestBlock(state, player.getHeldItemMainhand(), player);
	}


}
