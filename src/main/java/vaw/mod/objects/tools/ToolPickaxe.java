package vaw.mod.objects.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import vaw.mod.Main;
import vaw.mod.init.ItemInit;
import vaw.mod.util.IHasModel;
import vaw.mod.util.Reference;

public class ToolPickaxe extends ItemPickaxe implements IHasModel 
{

	public ToolPickaxe(String name, ToolMaterial material)
	{
		super(material);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.TOOLS);
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() 
	{
		// TODO Auto-generated method stub
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
