package biomesoplenty.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.*;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWisteria extends BlockVine
{
	public BlockWisteria()
	{
		this.setHardness(0.2F);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setTickRandomly(true);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("biomesoplenty:wisteria");
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 16777215;
    }

    // JAVADOC METHOD $$ func_149741_i
	@Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_)
    {
        return 16777215;
    }

    // JAVADOC METHOD $$ func_149720_d
	@Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        return 16777215;
    }

    @Override
    public int tickRate(World p_149738_1_)
    {
        return 20;
    }
}
