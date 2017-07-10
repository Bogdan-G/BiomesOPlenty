package biomesoplenty.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.client.renderer.texture.IIconRegister;
import biomesoplenty.BiomesOPlenty;
import net.minecraft.world.World;

public class BlockWillow extends BlockVine
{
	public BlockWillow()
	{
		this.setHardness(0.2F);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setTickRandomly(true);

		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:willow");
	}

    @Override
    public int tickRate(World p_149738_1_)
    {
        return 20;
    }
}
