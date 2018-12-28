package vaw.mod.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import vaw.mod.init.ItemInit;

public class Reference {


	public static final String MODID = "vaw";
	public static final String NAME = "Vampires and Werewolves";
	public static final String VERSION = "0.0.0.1";
	
	public static final String CLIENTPROXY = "vaw.mod.proxy.ClientProxy";
	public static final String COMMONPROXY = "vaw.mod.proxy.CommonProxy";
	
	public static final CreativeTabs VampiresAndWerewolves = new CreativeTabs("myMod") {
	    @Override public ItemStack getTabIconItem() {
	        return new ItemStack(ItemInit.STAKE_WOOD, 1);
	    }
	};
}
