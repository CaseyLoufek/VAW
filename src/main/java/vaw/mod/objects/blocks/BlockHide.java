package vaw.mod.objects.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vaw.mod.util.Config;
import vaw.mod.util.Reference;

public class BlockHide extends BlockBase
{
	protected static final AxisAlignedBB HIDE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.15D, 1.0D);

    protected static final AxisAlignedBB HIDE_EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.15D, 1.0D, 1.0D);
    protected static final AxisAlignedBB HIDE_WEST_AABB = new AxisAlignedBB(0.85D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB HIDE_SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.15D);
    protected static final AxisAlignedBB HIDE_NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.85D, 1.0D, 1.0D, 1.0D);
    
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool HANGING = PropertyBool.create("hanging");
    public static final PropertyBool AXIS = PropertyBool.create("axis");
    public static final PropertyBool FLIP = PropertyBool.create("flip");
    
    public String description = null;
    public int firstCount = 1;
    public Item firstDrop = null;
    public int secondCount = 0;
    public Item secondDrop = null;
    
    
	public BlockHide(String name, Material material, int count, Item primaryDrop)
	{
		super(name, material);
		this.setHarvestLevel("knife", 0);
		setCreativeTab(Reference.VampiresAndWerewolves);
		this.firstCount = count;
		this.firstDrop = primaryDrop;
        this.setDefaultState(this.blockState.getBaseState().withProperty(HANGING, false).withProperty(AXIS, false).withProperty(FLIP, false));
	}
    
	public BlockHide(String name, Material material, String descriptive, int count, Item primaryDrop)
	{
		super(name, material);
		this.setHarvestLevel("knife", 0);
		setCreativeTab(Reference.VampiresAndWerewolves);
		this.description = descriptive;
		this.firstCount = count;
		this.firstDrop = primaryDrop;
		if (Config.enableNewTanning == false) 
		{
			this.firstDrop = Items.LEATHER;
		}			
        this.setDefaultState(this.blockState.getBaseState().withProperty(HANGING, false).withProperty(AXIS, false).withProperty(FLIP, false));
	}

    
	public BlockHide(String name, Material material, int count, Item primaryDrop, int countTwo, Item secondaryDrop)
	{
		super(name, material);
		this.setHarvestLevel("knife", 0);
		setCreativeTab(Reference.VampiresAndWerewolves);
		this.firstCount = count;
		this.firstDrop = primaryDrop;
		this.secondCount = countTwo;
		this.secondDrop = secondaryDrop;
		if (Config.enableNewTanning == false) 
		{
			this.firstDrop = Items.LEATHER;
			this.secondDrop = Items.LEATHER;
		}
        this.setDefaultState(this.blockState.getBaseState().withProperty(HANGING, false).withProperty(AXIS, false).withProperty(FLIP, false));
	}
    
	public BlockHide(String name, Material material, String descriptive, int count, Item primaryDrop, int countTwo, Item secondaryDrop)
	{
		super(name, material);
		this.setHarvestLevel("knife", 0);
		setCreativeTab(Reference.VampiresAndWerewolves);
		this.description = descriptive;
		this.firstCount = count;
		this.firstDrop = primaryDrop;
		this.secondCount = countTwo;
		this.secondDrop = secondaryDrop;
		if (Config.enableNewTanning == false) 
		{
			this.firstDrop = Items.LEATHER;
			this.secondDrop = Items.LEATHER;
		}
        this.setDefaultState(this.blockState.getBaseState().withProperty(HANGING, false).withProperty(AXIS, false).withProperty(FLIP, false));
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, World par2EntityPlayer, List<String> par2List, ITooltipFlag par4)
	{
		if (this.description != null)
		{
			par2List.add(this.description);
		}
    }


    @Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return true;
    }
    
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
    @Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
    	return true;
    }
    
    @Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    

    @Override
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    @Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        if (state.getValue(HANGING).booleanValue())
        {
        	if ( state.getValue(FLIP).booleanValue() == false && state.getValue(AXIS).booleanValue() == false )
        	{
                return HIDE_SOUTH_AABB;
        	}
        	else if ( state.getValue(FLIP).booleanValue() == true && state.getValue(AXIS).booleanValue() == false )
        	{
                return HIDE_NORTH_AABB;
        	}
        	else if ( state.getValue(FLIP).booleanValue() == true && state.getValue(AXIS).booleanValue() == true )
        	{
                return HIDE_EAST_AABB;
        	}
        	else if ( state.getValue(FLIP).booleanValue() == false && state.getValue(AXIS).booleanValue() == true )
        	{
                return HIDE_WEST_AABB;
        	}
        }
        
        return HIDE_AABB;
    }



    /**
     * Whether this Block can be replaced directly by other blocks (true for e.g. tall grass)
     */
    @Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }


    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
	@Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	Boolean axis;
    	Boolean flip;
    	Boolean hang;
    	//north, south, east, west
    	if (placer.getHorizontalFacing() == EnumFacing.NORTH)
    	{
    		axis = false;
    		flip = false;
    	}
    	else if (placer.getHorizontalFacing() == EnumFacing.SOUTH)
    	{
    		axis = false;
    		flip = true;
    	}
    	else if (placer.getHorizontalFacing() == EnumFacing.EAST)
    	{
    		axis = true;
    		flip = false;
    	}
    	else
    	{
    		axis = true;
    		flip = true;
    	}
    	
        if (facing.getAxis().isHorizontal())
        {
            hang = true;
        }
        else
        {
            hang = false;
        }
        return this.getDefaultState().withProperty(HANGING, hang).withProperty(AXIS, axis).withProperty(FLIP, flip);
    }
    

    @Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.west()).isSideSolid(worldIn, pos.west(), EnumFacing.EAST) ||
                worldIn.getBlockState(pos.east()).isSideSolid(worldIn, pos.east(), EnumFacing.WEST) ||
                worldIn.getBlockState(pos.north()).isSideSolid(worldIn, pos.north(), EnumFacing.SOUTH) ||
                worldIn.getBlockState(pos.south()).isSideSolid(worldIn, pos.south(), EnumFacing.NORTH) ||
		 		( super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos) );
		
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {
        this.checkForDrop(worldIn, pos, state);
    }

    private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
		if (state.getValue(HANGING).booleanValue() == false) 
		{
			if (!this.canBlockStay(worldIn, pos))
			{
				this.dropBlockAsItem(worldIn, pos, state, 0);
				worldIn.setBlockToAir(pos);
				return false;
			}
			else
			{
				return true;
			}
		}
		
        EnumFacing enumfacing = EnumFacing.SOUTH;
		if ( state.getValue(FLIP).booleanValue() == false && state.getValue(AXIS).booleanValue() == false )
		{
			enumfacing = EnumFacing.SOUTH;
		}
		else if ( state.getValue(FLIP).booleanValue() == true && state.getValue(AXIS).booleanValue() == false )
		{
			enumfacing = EnumFacing.NORTH;
		}
		else if ( state.getValue(FLIP).booleanValue() == true && state.getValue(AXIS).booleanValue() == true )
		{
			enumfacing = EnumFacing.EAST;
		}
		else if ( state.getValue(FLIP).booleanValue() == false && state.getValue(AXIS).booleanValue() == true )
		{
			enumfacing = EnumFacing.WEST;
		}
		
        if (!this.canBlockStay(worldIn, pos, enumfacing))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
			return false;
        }
		return true;
    }

    protected boolean canBlockStay(World worldIn, BlockPos pos, EnumFacing facing)
    {
        return worldIn.getBlockState(pos.offset(facing.getOpposite())).isSideSolid(worldIn, pos.offset(facing.getOpposite()), facing);
    }
	
    private boolean canBlockStay(World worldIn, BlockPos pos)
    {
        return !worldIn.isAirBlock(pos.down());
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(HANGING, Boolean.valueOf((meta & 1) > 0)).withProperty(AXIS, Boolean.valueOf((meta & 2) > 0)).withProperty(FLIP, Boolean.valueOf((meta & 4) > 0));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
	public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (state.getValue(HANGING).booleanValue())
        {
            i |= 1;
        }

        if (state.getValue(AXIS).booleanValue())
        {
            i |= 2;
        }

        if (state.getValue(FLIP).booleanValue())
        {
            i |= 4;
        }


        return i;
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
    {

        EnumFacing face = rot.rotate(state.getValue(FACING));

    	Boolean axis;
    	Boolean flip;
    	Boolean hang;
    	//north, south, east, west
    	if (face == EnumFacing.SOUTH)
    	{
    		axis = false;
    		flip = false;
    	}
    	else if (face == EnumFacing.NORTH)
    	{
    		axis = false;
    		flip = true;
    	}
    	else if (face == EnumFacing.WEST)
    	{
    		axis = true;
    		flip = false;
    	}
    	else
    	{
    		axis = true;
    		flip = true;
    	}
    	
        if (face.getAxis().isHorizontal()) // && this.canBlockStay(worldIn, pos, facing)
        {
            hang = true;
        }
        else
        {
            hang = false;
        }
        return this.getDefaultState().withProperty(HANGING, hang).withProperty(AXIS, axis).withProperty(FLIP, flip);
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        Rotation rot = mirrorIn.toRotation(state.getValue(FACING));
        EnumFacing face = rot.rotate(state.getValue(FACING));

    	Boolean axis;
    	Boolean flip;
    	Boolean hang;
    	//north, south, east, west
    	if (face == EnumFacing.SOUTH)
    	{
    		axis = false;
    		flip = false;
    	}
    	else if (face == EnumFacing.NORTH)
    	{
    		axis = false;
    		flip = true;
    	}
    	else if (face == EnumFacing.WEST)
    	{
    		axis = true;
    		flip = false;
    	}
    	else
    	{
    		axis = true;
    		flip = true;
    	}
    	
        if (face.getAxis().isHorizontal()) // && this.canBlockStay(worldIn, pos, facing)
        {
            hang = true;
        }
        else
        {
            hang = false;
        }
        return this.getDefaultState().withProperty(HANGING, hang).withProperty(AXIS, axis).withProperty(FLIP, flip);
    }

    @Override
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {HANGING,AXIS,FLIP});
    }

    @Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }
    
    /**
     * Spawns the block's drops in the world. By the time this is called the Block has possibly been set to air via
     * Block.removedByPlayer
     */
    @Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem().getToolClasses(stack).contains("knife"))
        {
        	if (firstDrop != null) spawnAsEntity(worldIn, pos, new ItemStack(firstDrop, firstCount));
        	if (secondDrop != null) spawnAsEntity(worldIn, pos, new ItemStack(secondDrop, secondCount));
        }
        else if (!worldIn.isRemote && Config.enableKnives == false &&  stack.getItem().getToolClasses(stack).contains("sword"))
        {
        	if (firstDrop != null) spawnAsEntity(worldIn, pos, new ItemStack(firstDrop, firstCount));
        	if (secondDrop != null) spawnAsEntity(worldIn, pos, new ItemStack(secondDrop, secondCount));
        }
        else if (!worldIn.isRemote)
        {
        	player.addStat(StatList.getBlockStats(this));
            spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(this), 1));
        }
        
        player.addStat(StatList.getBlockStats(this));
        player.addExhaustion(0.005F);
        harvesters.set(player);
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
        this.dropBlockAsItem(worldIn, pos, state, i);
        harvesters.set(null);
    }

}
