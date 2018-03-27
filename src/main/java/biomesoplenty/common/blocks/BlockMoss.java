package biomesoplenty.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import biomesoplenty.BiomesOPlenty;

public class BlockMoss extends BlockVine
{
	public BlockMoss()
	{
		this.setHardness(0.2F);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setTickRandomly(true);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:moss");
	}
	
	@Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
    {
        if(side==1) {
                Block block = world.getBlock(x, y + 1, z);
                return (block.isWood(world, x, y + 1, z) || block == Blocks.stone);
        } else if(side==2) {
            	Block block = world.getBlock(x, y, z + 1);
            	return (block.isWood(world, x, y, z + 1) || block == Blocks.stone);
        } else if(side==3) {
            	Block block = world.getBlock(x, y, z - 1);
            	return (block.isWood(world, x, y, z - 1) || block == Blocks.stone);
        } else if(side==4) {
            	Block block = world.getBlock(x + 1, y, z);
            	return (block.isWood(world, x + 1, y, z) || block == Blocks.stone);
        } else if(side==5) {
            	Block block = world.getBlock(x - 1, y, z);
            	return (block.isWood(world, x - 1, y, z) || block == Blocks.stone);
        } else {
                return false;
        }
    }
}
