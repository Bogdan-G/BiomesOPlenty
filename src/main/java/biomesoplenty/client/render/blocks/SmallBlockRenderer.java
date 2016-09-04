package biomesoplenty.client.render.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import biomesoplenty.client.render.RenderUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import org.lwjgl.opengl.GL11;

public class SmallBlockRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId == RenderUtils.bonesModel)
		{
			int meta = world.getBlockMetadata(x, y, z);

			//0.062 Approx (Per pixel)
			if (meta == 0)
			{
				GL11.glPushMatrix();
				renderer.setRenderBounds(0.374F, 0.0F, 0.374F, 0.626F, 1.0F, 0.626F);
				renderer.renderStandardBlock(block, x, y, z);
				GL11.glPopMatrix();
			}
			else if (meta == 1)
			{
				GL11.glPushMatrix();
				renderer.setRenderBounds(0.187F, 0.0F, 0.187F, 0.813F, 1.0F, 0.813F);
				renderer.renderStandardBlock(block, x, y, z);
				GL11.glPopMatrix();
			}
			else if (meta == 3)
			{
				GL11.glPushMatrix();
				renderer.setRenderBounds(0.374F, 0.374F, 0.0F, 0.626F, 0.626F, 1.0F);
				renderer.renderStandardBlock(block, x, y, z);
				GL11.glPopMatrix();
			}
			else if (meta == 4)
			{
				GL11.glPushMatrix();
				renderer.setRenderBounds(0.0F, 0.374F, 0.374F, 1.0F, 0.626F, 0.626F);
				renderer.renderStandardBlock(block, x, y, z);
				GL11.glPopMatrix();
			}
			else if (meta == 5)
			{
				GL11.glPushMatrix();
				renderer.setRenderBounds(0.187F, 0.187F, 0.0F, 0.813F, 0.813F, 1.0F);
				renderer.renderStandardBlock(block, x, y, z);
				GL11.glPopMatrix();
			}
			else if (meta == 6)
			{
				GL11.glPushMatrix();
				renderer.setRenderBounds(0.0F, 0.187F, 0.187F, 1.0F, 0.813F, 0.813F);
				renderer.renderStandardBlock(block, x, y, z);
				GL11.glPopMatrix();
			}
			else
			{
				GL11.glPushMatrix();
				renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				renderer.renderStandardBlock(block, x, y, z);
				GL11.glPopMatrix();
			}
		}
		return true;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		if (modelID == RenderUtils.bonesModel)
		{
			Tessellator tessellator = Tessellator.instance;
			GL11.glPushMatrix();

			if (metadata == 0)
			{
				renderer.setRenderBounds(0.374F, 0.0F, 0.374F, 0.626F, 1.0F, 0.626F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 1)
			{
				renderer.setRenderBounds(0.187F, 0.0F, 0.187F, 0.813F, 1.0F, 0.813F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 3)
			{
				renderer.setRenderBounds(0.374F, 0.374F, 0.0F, 0.626F, 0.626F, 1.0F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 4)
			{
				renderer.setRenderBounds(0.0F, 0.374F, 0.374F, 1.0F, 0.626F, 0.626F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 5)
			{
				renderer.setRenderBounds(0.187F, 0.187F, 0.0F, 0.813F, 0.813F, 1.0F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
			else if (metadata == 6)
			{
				renderer.setRenderBounds(0.0F, 0.187F, 0.187F, 1.0F, 0.813F, 0.813F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
			else
			{
				renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				RenderUtils.renderStandardInvBlock(renderer, block, metadata);
			}
			GL11.glPopMatrix();
		}
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return RenderUtils.bonesModel;
	}
}
