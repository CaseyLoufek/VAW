package vaw.mod.util.handlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import vaw.mod.init.ItemInit;

public class FuelHandler implements IFuelHandler
{

	@Override
	public int getBurnTime(ItemStack fuel) 
	{
		if (fuel.getItem() == ItemInit.STAKE_WOOD){
			return 200;
		}
		return 0;
	}

}