package vaw.mod.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

public class CommonProxy 
{

	public void registerItemRenderer(Item item, int meta, String id) {}
	
	public void registerItemRenderer(Item item, int meta, String id, String color) {}
    public void initModels() {}
		
    public static Configuration config;
	
}
