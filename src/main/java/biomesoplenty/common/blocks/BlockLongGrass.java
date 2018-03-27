package biomesoplenty.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;

public class BlockLongGrass extends Block
{
	private IIcon[] icons = new IIcon[6];
	private static final int ione = 1;private static final int itwo = 2;private static final int ithree = 3;private static final int ifour = 4;private static final int izero = 0;
	private static final float fzf = 0.5F;

	public BlockLongGrass()
	{
		super(Material.grass);
		
		this.setHardness(0.6F);
		this.setHarvestLevel("shovel", 0);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setTickRandomly(true);

		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.icons[0] = iconRegister.registerIcon("biomesoplenty:longgrass3");
		this.icons[1] = iconRegister.registerIcon("biomesoplenty:longgrass1");
		this.icons[2] = iconRegister.registerIcon("biomesoplenty:longgrass2");
		this.icons[3] = iconRegister.registerIcon("biomesoplenty:longgrass2");
		this.icons[4] = iconRegister.registerIcon("biomesoplenty:longgrass2");
		this.icons[5] = iconRegister.registerIcon("biomesoplenty:longgrass2");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
	    if (side < 0 || side >= this.icons.length) side = 1;
        
		return this.icons[side];
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	{
		return true;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitVecX, float hitVecY, float hitVecZ)
	{
		if (player.getCurrentEquippedItem() != null)
		{
			if (player.getCurrentEquippedItem().getDisplayName().toLowerCase().contains(" hoe"))
			{
				Block tilledField = Blocks.farmland;

				world.playSoundEffect(x + fzf, y + fzf, z + fzf, tilledField.stepSound.func_150496_b(), (tilledField.stepSound.getVolume() + 1.0F) / 2.0F, tilledField.stepSound.getPitch() * 0.8F);

				if (!world.isRemote)
				{
					world.setBlock(x, y, z, tilledField, izero, itwo);
				}
				
				return true;
			} 
			else
			{
				return false;
			}
		} 
		else
		{
			return false;
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (!world.isRemote)
		{
			int block_light = world.getBlockLightValue(x, y + ione, z);
			if (block_light < ifour && world.getBlockLightOpacity(x, y + ione, z) > itwo)
			{
				world.setBlock(x, y, z, Blocks.dirt, izero, itwo);
			}
			else if (block_light >= 9)
			{
				for (int var6 = 0; var6 < ifour; ++var6)
				{
					int rX = x + random.nextInt(ithree) - ione;
					int rY = y + random.nextInt(5) - ithree;
					int rZ = z + random.nextInt(ithree) - ione;
                    Block block = world.getBlock(rX, rY + ione, rZ);

					if (world.getBlock(rX, rY, rZ) == Blocks.dirt && world.getBlockLightValue(rX, rY + ione, rZ) >= ifour && world.getBlockLightOpacity(rX, rY + ione, rZ) <= 2)
					{
						world.setBlock(rX, rY, rZ, this, izero, itwo);
					}
				}
			}
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return Blocks.dirt.getItemDropped(izero, random, fortune);
	}

    @Override
    public int tickRate(World p_149738_1_)
    {
        return 20;
    }
}
