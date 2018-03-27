package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.client.render.RenderUtils;
import biomesoplenty.common.blocks.templates.BOPBlockWorldDecor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPFlower extends BOPBlockWorldDecor
{
	private static final String[] plants = new String[] {"clover", "swampflower", "deadbloom", "glowflower", "hydrangea", "cosmos", "daffodil", "wildflower", "violet", "anemone", "lilyflower", "enderlotus", "bromeliad", "eyebulbbottom", "eyebulbtop", "dandelion"};
	private IIcon[] textures;

	private static final int EYEBULBTOP = 14;
	private static final int EYEBULBBOTTOM = 13;
	private static final double dzero = 0.0D;private static final float fzf = 0.5F;private static final float fzero = 0.0F;private static final float fzo = 0.1F;
	private static final float fzt = 0.3F;private static final float fzs = 0.7F;private static final float fze = 0.8F;

	public BlockBOPFlower()
	{
		super(Material.plants);

		this.setHardness(0.0F);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setTickRandomly(true);

		float var4 = 0.2F;
		this.setBlockBounds(fzf - var4, 0.0F, fzf - var4, fzf + var4, var4 * 3.0F, fzf + var4);

		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[plants.length];

		for (int i = 0; i < plants.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + plants[i]);
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
	public int getRenderType()
	{
		return RenderUtils.foliageModel;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 3)
			return 9;
		else if (meta == 11)
			return 5;
		else
			return 0;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta==0) {
			this.setBlockBounds(fzero, fzero, fzero, 1.0F, 0.015625F, 1.0F);
		} else if(meta==5) {
			this.setBlockBounds(fzt, fzero, fzt, fzs, fze, fzs);
		} else if(meta==6) {
			this.setBlockBounds(fzt, fzero, fzt, fzs, 0.6F, fzs);
		} else if(meta==9) {
			this.setBlockBounds(fzt, fzero, fzt, fzs, fzf, fzs);
		} else if(meta==10) {
			this.setBlockBounds(fzero, fzero, fzero, fzero, fzero, fzero);
		} else if(meta==11) {
			this.setBlockBounds(fzt, fzero, fzt, fzs, 0.4F, fzs);
		} else if(meta==15) {
			this.setBlockBounds(fzt, fzero, fzt, fzs, 0.6F, fzs);
		} else {
			this.setBlockBounds(fzo, fzero, fzo, 0.9F, fze, 0.9F);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (!world.isRemote && meta == 2 && entity instanceof EntityLivingBase) 
		{
			if (entity instanceof EntityPlayer)
			{
				InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

				if (!((inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == Items.leather_boots) && (inventory.armorInventory[1] != null && inventory.armorInventory[1].getItem() == Items.leather_leggings)))
				{
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
				}
			}
			else
			{
				((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
			}
		}
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		super.randomDisplayTick(world, x, y, z, random);

		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 2)
		{
			if (random.nextInt(4) != 0)
			{
				world.spawnParticle("townaura", x + random.nextFloat(), y + random.nextFloat(), z + random.nextFloat(), dzero, dzero, dzero);
			}
			if (random.nextInt(4) == 0)
			{
				world.spawnParticle("smoke", x + random.nextFloat(), y, z + random.nextFloat(), dzero, dzero, dzero);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < plants.length; ++i)
		{
			if (i != 14)
			{
				list.add(new ItemStack(block, 1, i));
			}
		}
	}

	@Override
	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		Block block = world.getBlock(x, y - 1, z);
		
		if(metadata==10) { // Lily Flower
			return block == Blocks.waterlily;
		} else if(metadata==11) { // Enderlotus
			return block == BOPCBlocks.bopGrass;
		} else if(metadata==12) { // Bromeliad
			return block == BOPCBlocks.hardDirt || block == Blocks.hardened_clay || block == Blocks.sand;
		} else if(metadata==13) { // Eyebulb Bottom
			 return block == Blocks.netherrack || block == BOPCBlocks.overgrownNetherrack || block == BOPCBlocks.flesh;
		} else if(metadata==14) { // Eyebulb Top
			return block == this;
		} else {
			// include // Tulip
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == BOPCBlocks.longGrass || block == BOPCBlocks.overgrownNetherrack || block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
		}
	}
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
	{
		super.onNeighborBlockChange(world, x, y, z, neighborBlock);

		int meta = world.getBlockMetadata(x, y, z);
		if (meta == EYEBULBTOP && world.getBlock(x, y - 1, z) == this && world.getBlockMetadata(x, y - 1, z) != EYEBULBBOTTOM) 
		{
			world.setBlockToAir(x, y, z);
		}
		if (meta == EYEBULBBOTTOM && world.getBlock(x, y + 1, z) != this) 
		{
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == EYEBULBTOP) {
			meta = 13;
		}
		return meta;
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta == 14) return 13;
		else return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 13)
			return 0;
		else
			return 1;
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		super.harvestBlock(world, player, x, y, z, meta);

		ItemStack equippedItem = player.getCurrentEquippedItem();

		if (equippedItem != null)
		{
			if (equippedItem.getItem() != Items.shears && meta == 2 && !world.isRemote) {
				player.addPotionEffect(new PotionEffect(Potion.wither.id, 300));
			}
		}
		else
		{
			if (meta == 2 && !world.isRemote) {
				player.addPotionEffect(new PotionEffect(Potion.wither.id, 300));
			}
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 10) return true;

		return false;
	}

    @Override
    public int tickRate(World p_149738_1_)
    {
        return 20;
    }
}
