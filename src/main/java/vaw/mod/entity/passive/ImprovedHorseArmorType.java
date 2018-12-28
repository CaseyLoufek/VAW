package vaw.mod.entity.passive;

import javax.annotation.Nullable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum ImprovedHorseArmorType
{
    NONE(0),
    IRON(5, "iron", "meo"),
    GOLD(7, "gold", "goo"),
    DIAMOND(11, "diamond", "dio"),
    LEATHER(3, "leather", "lea");

    private final String textureName;
    private final String hash;
    private final int protection;

    private ImprovedHorseArmorType(int armorStrengthIn)
    {
        this.protection = armorStrengthIn;
        this.textureName = null;
        this.hash = "";
    }

    private ImprovedHorseArmorType(int armorStrengthIn, String p_i46800_4_, String p_i46800_5_)
    {
        this.protection = armorStrengthIn;
        this.textureName = "faunaandecology:textures/entity/horse/armor/horse_armor_" + p_i46800_4_ + ".png";
        this.hash = "forge";
    }

    public int getOrdinal()
    {
        return this.ordinal();
    }

    @SideOnly(Side.CLIENT)
    public String getHash()
    {
        return this.hash;
    }

    public int getProtection()
    {
        return this.protection;
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    public String getTextureName()
    {
        return this.textureName;
    }

    @Deprecated /**Forge: Use getByName. Ordinals of mod-added enum constants are dependent on load order, unlike names.**/
    public static ImprovedHorseArmorType getByOrdinal(int ordinal)
    {
        return values()[ordinal];
    }
    
    public static ImprovedHorseArmorType getByItemStack(ItemStack stack)
    {
        return getByItem(stack.getItem());
    }

    @Deprecated //Forge: Use getByItemStack
    public static ImprovedHorseArmorType getByItem(Item itemIn)
    {
        if (itemIn == Items.IRON_HORSE_ARMOR)
        {
            return IRON;
        }
        else if (itemIn == Items.GOLDEN_HORSE_ARMOR)
        {
            return GOLD;
        }
        else
        {
            return itemIn == Items.DIAMOND_HORSE_ARMOR ? DIAMOND : NONE;
        }
    }
    
    /* ======================================== FORGE START ======================================== */
    //Allows for textures located outside the vanilla horse armor folder
    private ImprovedHorseArmorType(String defaultTextureLocation, int armorStrengthIn)
    {
        this.protection = armorStrengthIn;
        this.textureName = defaultTextureLocation;
        this.hash = "forge";
    }
    
    public static ImprovedHorseArmorType getByName(String name)
    {
    	ImprovedHorseArmorType type = ImprovedHorseArmorType.valueOf(name);
        return type != null ? type : NONE;
    }
    /* ======================================== FORGE END ======================================== */

	public static boolean isHorseArmor(Item item) 
	{
		
	    if (item == Items.IRON_HORSE_ARMOR)
	    {
	        return true;
	    }
	    else if (item == Items.GOLDEN_HORSE_ARMOR)
	    {
	        return true;
	    }
	    else if (item == Items.DIAMOND_HORSE_ARMOR)
	    {
	        return true;
	    }
		return false;
	}
}