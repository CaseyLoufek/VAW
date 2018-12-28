package vaw.mod.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import vaw.mod.init.EntityInit;

public class ClientProxy extends CommonProxy
{

	@Override
	public void registerItemRenderer(Item item, int meta, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
        
	}
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id, String color)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName()+color, id));
        
	}

	@Override
    public void initModels() {
        EntityInit.initModels();
    }
	
}
