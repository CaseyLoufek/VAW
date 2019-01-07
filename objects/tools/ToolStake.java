package vaw.mod.objects.tools;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnchantmentFireAspect;
import net.minecraft.enchantment.EnchantmentMending;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.math.MathHelper;
import vaw.mod.init.ItemInit;
import vaw.mod.util.Reference;

import java.util.Set;

public class ToolStake extends ItemTool
{
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.WEB, Blocks.LEAVES, Blocks.LOG, Blocks.LOG2, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK);

    private String toolClass;
	public ToolStake(String name, ToolMaterial material)
	{
        super(material, EFFECTIVE_ON);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.toolClass = "knife";
		setCreativeTab(CreativeTabs.TOOLS);
		
		ItemInit.ITEMS.add(this);
	}

    protected ToolStake(String name, Item.ToolMaterial material, float damage, float speed)
    {
        super(material, EFFECTIVE_ON);
        setUnlocalizedName(name);
		setRegistryName(name);
		this.toolClass = "knife";
		setCreativeTab(Reference.VampiresAndWerewolves);
        this.attackDamage = damage + material.getAttackDamage();
        this.attackSpeed = speed;
		
		ItemInit.ITEMS.add(this);
	}

	public int getItemBurnTime(ItemStack item){
	    return 200;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, @javax.annotation.Nullable net.minecraft.entity.player.EntityPlayer player, @javax.annotation.Nullable IBlockState blockState)
    {
        int level = super.getHarvestLevel(stack, toolClass,  player, blockState);
        if (level == -1 && toolClass.equals(this.toolClass))
        {
            return this.toolMaterial.getHarvestLevel();
        }
        else
        {
            return level;
        }
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack)
    {
        return toolClass != null ? com.google.common.collect.ImmutableSet.of(toolClass) : super.getToolClasses(stack);
    }

    /**
     * Checks whether an item can be enchanted with a certain enchantment. This applies specifically to enchanting an item in the enchanting table and is called when retrieving the list of possible enchantments for an item.
     * Enchantments may additionally (or exclusively) be doing their own checks in {@link net.minecraft.enchantment.Enchantment#canApplyAtEnchantingTable(ItemStack)}; check the individual implementation for reference.
     * By default this will check if the enchantment type is valid for this item type.
     * @param stack the item stack to be enchanted
     * @param enchantment the enchantment to be applied
     * @return true if the enchantment can be applied to this item
     */
    @Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.enchantment.Enchantment enchantment)
    {
    	if (enchantment instanceof EnchantmentDamage) return true;
    	if (enchantment instanceof EnchantmentFireAspect) return true;
    	if (enchantment instanceof EnchantmentMending) return true;
        return enchantment.type.canEnchantItem(stack.getItem());
    }
    
    @Override
    public boolean hasContainerItem()
    {
    	 return true;
    }
    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    @Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
    	//weaken knockback effect, can be adjusted
        if (target != null)
        {
            target.knockBack(attacker, -0.1F, MathHelper.sin(attacker.rotationYaw * 0.017453292F), (-MathHelper.cos(attacker.rotationYaw * 0.017453292F)));
        }
        
        //extra damage for vampires here
        
        stack.damageItem(1, attacker);
        return true;
    }
    
    
    
}
