package biomesoplenty.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.client.render.RenderUtils;

public class BlockBones extends Block 
{
	//Meta 3 & 4 used by alternate small bone rotations, 5 & 6 are used by alternate medium bone rotations
	private static final String[] boneTypes = new String[] {"bones_small", "bones_medium", "bones_large"};
	private IIcon[] textures;
	private static final double[] d_num_arr = {0.187D, 0.374D, 0.626D, 0.813D, 1.0D, 1.00D};

	public BlockBones()
	{
		super(Material.rock);
		
		this.setHardness(3.0F);
		
		this.setResistance(5.0F);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[boneTypes.length];

		for (int i = 0; i < boneTypes.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+boneTypes[i]);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (meta == 5 || meta == 6) {
			meta = 1;
		}
		
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		case 0:
			return AxisAlignedBB.getBoundingBox(x + d_num_arr[1], y, z + d_num_arr[1], x + d_num_arr[2], y + d_num_arr[4], z + d_num_arr[2]);

		case 1:
			return AxisAlignedBB.getBoundingBox(x + d_num_arr[0], y, z + d_num_arr[0], x + d_num_arr[3], y + d_num_arr[4], z + d_num_arr[3]);

		case 3:
			return AxisAlignedBB.getBoundingBox(x + d_num_arr[1], y + d_num_arr[1], z, x + d_num_arr[2], y + d_num_arr[2], z + d_num_arr[5]);

		case 4:
			return AxisAlignedBB.getBoundingBox(x, y + d_num_arr[1], z + d_num_arr[1], x + d_num_arr[5], y + d_num_arr[2], z + d_num_arr[2]);

		case 5:
			return AxisAlignedBB.getBoundingBox(x + d_num_arr[0], y + d_num_arr[0], z, x + d_num_arr[3], y + d_num_arr[3], z + d_num_arr[5]);

		case 6:
			return AxisAlignedBB.getBoundingBox(x, y + d_num_arr[0], z + d_num_arr[0], x + d_num_arr[5], y + d_num_arr[3], z + d_num_arr[3]);

		default:
			return AxisAlignedBB.getBoundingBox(x, y, z, x + d_num_arr[4], y + d_num_arr[4], z + d_num_arr[4]);
		}
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		case 0:
			return AxisAlignedBB.getBoundingBox(x + d_num_arr[1], y, z + d_num_arr[1], x + d_num_arr[2], y + d_num_arr[4], z + d_num_arr[2]);

		case 1:
			return AxisAlignedBB.getBoundingBox(x + d_num_arr[0], y, z + d_num_arr[0], x + d_num_arr[3], y + d_num_arr[4], z + d_num_arr[3]);

		case 3:
			return AxisAlignedBB.getBoundingBox(x + d_num_arr[1], y + d_num_arr[1], z, x + d_num_arr[2], y + d_num_arr[2], z + d_num_arr[5]);

		case 4:
			return AxisAlignedBB.getBoundingBox(x, y + d_num_arr[1], z + d_num_arr[1], x + d_num_arr[5], y + d_num_arr[2], z + d_num_arr[2]);

		case 5:
			return AxisAlignedBB.getBoundingBox(x + d_num_arr[0], y + d_num_arr[0], z, x + d_num_arr[3], y + d_num_arr[3], z + d_num_arr[5]);

		case 6:
			return AxisAlignedBB.getBoundingBox(x, y + d_num_arr[0], z + d_num_arr[0], x + d_num_arr[5], y + d_num_arr[3], z + d_num_arr[3]);

		default:
			return AxisAlignedBB.getBoundingBox(x, y, z, x + d_num_arr[4], y + d_num_arr[4], z + d_num_arr[4]);
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		float minX;
		float minY;
		float minZ;
		float maxX;
		float maxY;
		float maxZ;

		switch (meta)
		{
		case 0:
			minY = 0F;
			minX = minZ = 0.374F;
			maxX = maxZ = 0.626F;
			maxY = 1.0F;
			break;

		case 1:
			minY = 0F;
			minX = minZ = 0.187F;
			maxX = maxZ = 0.813F;
			maxY = 1.00F;
			break;

		case 3:
			minX = minY = 0.374F;
			minZ = 0F;
			maxX = maxY = 0.626F;
			maxZ = 1.00F;
			break;

		case 4:
			minX = 0F;
			minY = minZ = 0.374F;
			maxX = 1.00F;
			maxY = maxZ = 0.626F;
			break;

		case 5:
			minX = minY = 0.187F;
			minZ = 0F;
			maxX = maxY = 0.813F;
			maxZ = 1.00F;
			break;

		case 6:
			minX = 0F;
			minY = minZ = 0.187F;
			maxX = 1.00F;
			maxY = maxZ = 0.813F;
			break;

		default:
			minY = 0F;
			minX = minZ = 0.0F;
			maxX = maxZ = 1.0F;
			maxY = 1.0F;
			break;
		}

		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 3 || meta == 4) {
			meta = 0;
		}
		if (meta == 5 || meta == 6) {
			meta = 1;
		}
		return meta;
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < boneTypes.length; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

	@Override
	public int getRenderType()
	{
		return RenderUtils.bonesModel;
	}

	@Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		return true;
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta == 3 || meta == 4) 
		{
			meta = 0;
		}
		if (meta == 5 || meta == 6) 
		{
			meta = 1;
		}
		return meta;
	}

    @Override
    public int tickRate(World p_149738_1_)
    {
        return 20;
    }
}
