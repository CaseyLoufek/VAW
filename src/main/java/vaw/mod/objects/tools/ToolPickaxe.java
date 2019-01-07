package vaw.mod.objects.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import vaw.mod.Main;
import vaw.mod.init.ItemInit;

public class ToolPickaxe extends ItemPickaxe
{

	public ToolPickaxe(String name, ToolMaterial material)
	{
		super(material);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.TOOLS);
		
		ItemInit.ITEMS.add(this);
	}



}
