package vaw.mod.util.handlers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class EventsHandler 
{

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

}
