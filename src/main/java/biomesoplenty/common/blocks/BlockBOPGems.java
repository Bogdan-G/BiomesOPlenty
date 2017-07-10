package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCItems;

public class BlockBOPGems extends Block
{
	public static final String[] types = new String[] {"amethystore", "amethystblock", "rubyore", "rubyblock", "peridotore", "peridotblock", "topazore", "topazblock", "tanzaniteore", "tanzaniteblock", "malachiteore", "malachiteblock", "sapphireore", "sapphireblock", "amberore", "amberblock"};
	private IIcon[] textures;

	public BlockBOPGems()
	{
		super(Material.rock);
		
		this.setHarvestLevel("pickaxe", 3, 0);
		
		for (int i = 1; i < 16; i++)
		{
			this.setHarvestLevel("pickaxe", 2, i);
		}
		
		this.setStepSound(Block.soundTypePiston);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[types.length];

		for (int i = 0; i < types.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+types[i]);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) 
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < types.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		return (meta % 2 == 0) ? 3.0F : 5.0F;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		int meta = world.getBlockMetadata(x, y, z);

		return (meta % 2 == 0) ? 1.0F : 2.0F;
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return (metadata % 2 == 0) ? BOPCItems.gems : super.getItemDropped(metadata, random, fortune);
	}

	@Override
	public int damageDropped(int meta)
	{
		return (meta % 2 == 0) ? meta / 2 : meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return (meta % 2 == 0) ? quantityDroppedWithBonus(fortune, random) : 1;
	}

	@Override
	public int quantityDroppedWithBonus(int bonus, Random random)
	{
		if (bonus > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, random, bonus))
		{
			int rnd = random.nextInt(bonus + 2) - 1;

			if (rnd < 0) 
			{
				rnd = 0;
			}

			return this.quantityDropped(random) * (rnd + 1);
		}
		else
			return this.quantityDropped(random);
	}

    @Override
    public int tickRate(World p_149738_1_)
    {
        return 20;
    }

    @Override
    public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target)
    {
        return true;
    }
}
