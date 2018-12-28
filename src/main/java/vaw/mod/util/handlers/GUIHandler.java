package vaw.mod.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {
	public static final int ZOOPEDIA_GUI = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	    return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	    //if (ID == ZOOPEDIA_GUI)
	        //return new GuiScreenZoopedia((IEntityAdvanced) world.getEntityByID(x));
	    return null;
	}
	
}