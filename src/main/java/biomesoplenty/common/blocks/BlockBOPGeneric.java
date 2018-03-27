package biomesoplenty.common.blocks;

import static biomesoplenty.api.utils.BiomeUtils.areBiomesEqual;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCItems;

public class BlockBOPGeneric extends Block
{
	public enum BlockType
	{
		ASH_STONE, HARD_SAND, HARD_DIRT, HARD_ICE, DRIED_DIRT, CRAG_ROCK, MUD_BRICK, BIOME_BLOCK, CRYSTAL;
	}

	private IIcon texture;
	private BlockType type;

	public BlockBOPGeneric(Material material, BlockType type)
	{
		super(material);
		this.setHarvestLevel("pickaxe", 3, 7);
		this.type = type;
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);

		if(type==BlockType.ASH_STONE) {
			this.setHardness(1.0F);		
			this.setStepSound(Block.soundTypePiston);
		} else if(type==BlockType.CRAG_ROCK) {
			this.setHardness(1.0F);
			this.setStepSound(Block.soundTypeStone);
		} else if(type==BlockType.DRIED_DIRT) {
			this.setHardness(0.1F);		
			this.setHarvestLevel("pickaxe", 0);
			this.setStepSound(Block.soundTypePiston);
		} else if(type==BlockType.HARD_DIRT) {
			this.setHardness(0.9F);
			this.setStepSound(Block.soundTypePiston);
		} else if(type==BlockType.HARD_ICE) {
			this.setHardness(0.75F);		
			this.setStepSound(Block.soundTypePiston);
		} else if(type==BlockType.HARD_SAND) {
			this.setHardness(0.7F);
			this.setHarvestLevel("shovel", 0);
			this.setStepSound(Block.soundTypeSand);
		} else if(type==BlockType.MUD_BRICK) {
			this.setHardness(1.0F);
			this.setResistance(2.0F);	
			this.setStepSound(Block.soundTypePiston);
		} else if(type==BlockType.BIOME_BLOCK) {
			this.setHardness(0.6F);
			this.setStepSound(soundTypeGlass);
		} else if(type==BlockType.CRYSTAL) {
			this.setHardness(0.15F);
			this.setResistance(5.0F);
			this.setLightLevel(1.0F);
			this.setStepSound(Block.soundTypeGlass);
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		if(type==BlockType.ASH_STONE) {
			texture = iconRegister.registerIcon("biomesoplenty:ashstone");
		} else if(type==BlockType.CRAG_ROCK) {
			texture = iconRegister.registerIcon("biomesoplenty:cragrock");
		} else if(type==BlockType.DRIED_DIRT) {
			texture = iconRegister.registerIcon("biomesoplenty:drieddirt");
		} else if(type==BlockType.HARD_DIRT) {
			texture = iconRegister.registerIcon("biomesoplenty:harddirt");
		} else if(type==BlockType.HARD_ICE) {
			texture = iconRegister.registerIcon("biomesoplenty:hardice");
		} else if(type==BlockType.HARD_SAND) {
			texture = iconRegister.registerIcon("biomesoplenty:hardsand");
		} else if(type==BlockType.MUD_BRICK) {
			texture = iconRegister.registerIcon("biomesoplenty:mudbrick");
		} else if(type==BlockType.BIOME_BLOCK) {
			texture = iconRegister.registerIcon("biomesoplenty:biomeblock");
		} else if(type==BlockType.CRYSTAL) {
			texture = iconRegister.registerIcon("biomesoplenty:crystal");
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		if(type==BlockType.CRYSTAL) {
			return BOPCItems.misc;
		} else {
			return super.getItemDropped(metadata, random, fortune);
		}
	}
	
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune)
	{
		if(type==BlockType.BIOME_BLOCK) {
			for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
			{
			    if (biome != null)
			    {
			    	if (!areBiomesEqual(biome, BOPCBiomes.spectralGarden) && !areBiomesEqual(biome, BOPCBiomes.boneyard) && !areBiomesEqual(biome, BOPCBiomes.visceralHeap) && !areBiomesEqual(biome, BOPCBiomes.polarChasm) && !areBiomesEqual(biome, BOPCBiomes.undergarden) && 
			    			!areBiomesEqual(biome, BOPCBiomes.corruptedSands) && !areBiomesEqual(biome, BOPCBiomes.phantasmagoricInferno) && !areBiomesEqual(biome, BOPCBiomes.lushRiver) && !areBiomesEqual(biome, BOPCBiomes.dryRiver) &&
			    			!areBiomesEqual(biome, BiomeGenBase.beach) && !areBiomesEqual(biome, BiomeGenBase.coldBeach) && !areBiomesEqual(biome, BiomeGenBase.stoneBeach) && !areBiomesEqual(biome, BiomeGenBase.frozenOcean) && 
			    			!areBiomesEqual(biome, BiomeGenBase.frozenRiver) && !areBiomesEqual(biome, BiomeGenBase.hell) && !areBiomesEqual(biome, BiomeGenBase.river) && !areBiomesEqual(biome, BiomeGenBase.sky) && 
			    			!areBiomesEqual(biome, BiomeGenBase.ocean) && !areBiomesEqual(biome, BiomeGenBase.deepOcean))
			    	{
				        ItemStack biomeEssence = new ItemStack(BOPCItems.biomeEssence);
	
				        biomeEssence.setTagCompound(new NBTTagCompound());
	
				        biomeEssence.getTagCompound().setInteger("biomeID", biome.biomeID);
				        
				        if (world.rand.nextInt(75) == 0)
				        {
				        	this.dropBlockAsItem(world, x, y, z, biomeEssence);
				        }
			    	}
			    }
			}
		} else {
			super.dropBlockAsItemWithChance(world, x, y, z, metadata, chance, fortune);
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		if(type==BlockType.CRYSTAL) {
			return 4;
		} else {
			return meta;
		}
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if(type==BlockType.CRYSTAL) {
			return 4;
		} else {
			return 1;
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return texture;
	}

    @Override
    public int tickRate(World p_149738_1_)
    {
        return 20;
    }
}
