package vaw.mod.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vaw.mod.Main;
import vaw.mod.init.ItemInit;
import vaw.mod.util.Reference;

public class ItemModFood extends ItemFood
{
	private PotionEffect[] effects;
	private float effectChance;
	private String description;
    
	private Item firstDrop = null;
	
	
	public ItemModFood(String unlocalizedName, int healAmount, float saturationModifier, boolean wolvesFavorite, float effectChanceIn, PotionEffect... effects) {
	    super(healAmount, saturationModifier, wolvesFavorite);
	    this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
	    //this.setTextureName(Reference.MODID + ":" + unlocalizedName);
	    this.setCreativeTab(Reference.VampiresAndWerewolves);
	    this.effects = effects;
	    this.effectChance = effectChanceIn;

		ItemInit.ITEMS.add(this);
	}
	
	public ItemModFood(String unlocalizedName, int healAmount, float saturationModifier, Item primaryDrop, boolean wolvesFavorite, float effectChanceIn, PotionEffect... effects) {
	    super(healAmount, saturationModifier, wolvesFavorite);
	    this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
	    //this.setTextureName(Reference.MODID + ":" + unlocalizedName);
	    this.setCreativeTab(Reference.VampiresAndWerewolves);
	    this.effects = effects;
	    this.effectChance = effectChanceIn;
	    this.firstDrop = primaryDrop;
        
		ItemInit.ITEMS.add(this);
	}

	public ItemModFood(String unlocalizedName, String descriptive, int healAmount, float saturationModifier, boolean wolvesFavorite, float effectChanceIn, PotionEffect... effects) {
	    super(healAmount, saturationModifier, wolvesFavorite);
	    this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
	    //this.setTextureName(Reference.MODID + ":" + unlocalizedName);
	    this.setCreativeTab(Reference.VampiresAndWerewolves);
	    this.effects = effects;
	    this.effectChance = effectChanceIn;

		this.description = descriptive;
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, World par2EntityPlayer,  List<String> par2List, ITooltipFlag par4)
	{
		if (this.description != null)
		{
			par2List.add(this.description);
		}
    }
	
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
	    super.onFoodEaten(stack, world, player);
	        
	    for (int i = 0; i < effects.length; i ++) {
	    	if (world.rand.nextFloat() <= this.effectChance)
	    	{
		        if (!world.isRemote && effects[i] != null && Potion.getIdFromPotion(effects[i].getPotion()) > 0)
		            player.addPotionEffect(new PotionEffect(this.effects[i].getPotion(), this.effects[i].getDuration(), this.effects[i].getAmplifier(), this.effects[i].getIsAmbient(), false));	    		
	    	}
	    }

	    if (this.firstDrop != null)
	    {
		    ItemStack stackNew = new ItemStack(this.firstDrop);
	    	EnumHand hand = player.getActiveHand();
	    	if (stackNew.isEmpty())
	        {
	    		player.setHeldItem(hand, stackNew);
	        }
	        else if (!player.inventory.addItemStackToInventory(stackNew))
	        {
	        	   player.dropItem(stackNew, false);
	        }
	        else if (player instanceof EntityPlayerMP)
	        {
	            ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
	        }
	    }
	}



	@Override
	public Item getContainerItem() 
	{
		return this.firstDrop;
	}
	
	@Override
	public boolean hasContainerItem() 
	{
		return (this.firstDrop != null);
	}
	

}