package biomesoplenty.client.render.entities.projectiles;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import biomesoplenty.common.entities.projectiles.EntityDart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDart extends Render
{
    private static final double n_0 = 2.0D;private static final double n_1 = -2.0D;
    private static final double n_2 = 8.0D;private static final double n_3 = -8.0D;
    private static final float n_4 = 32.0F;private static final double n_5 = -7.0D;
    private static final float n_6 = 0.0F;private static final float n_7 = 1.0F;
    private static final double n_8 = 0.0D;

    public void renderDart(EntityDart par1EntityDart, double par2, double par4, double par6, float par8, float par9)
    {
        this.bindEntityTexture(par1EntityDart);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(par1EntityDart.prevRotationYaw + (par1EntityDart.rotationYaw - par1EntityDart.prevRotationYaw) * par9 - 90.0F, n_6, n_7, n_6);
        GL11.glRotatef(par1EntityDart.prevRotationPitch + (par1EntityDart.rotationPitch - par1EntityDart.prevRotationPitch) * par9, n_6, n_6, n_7);
        Tessellator tessellator = Tessellator.instance;
        byte b0 = 0;
        float f2 = n_6;
        float f3 = 0.5F;
        int f_0 = b0 * 10;// xD
        float f4 = (float)(0 + f_0) / n_4;
        float f5 = (float)(5 + f_0) / n_4;
        float f6 = n_6;
        float f7 = 0.15625F;
        float f8 = (float)(5 + f_0) / n_4;
        float f9 = (float)(10 + f_0) / n_4;
        float f10 = 0.05625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f11 = (float)par1EntityDart.arrowShake - par9;

        if (f11 > n_6)
        {
            float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
            GL11.glRotatef(f12, n_6, n_6, n_7);
        }

        GL11.glRotatef(45.0F, n_7, n_6, n_6);
        GL11.glScalef(f10, f10, f10);
        GL11.glTranslatef(-4.0F, n_6, n_6);
        GL11.glNormal3f(f10, n_6, n_6);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(n_5, n_1, n_1, (double)f6, (double)f8);
        tessellator.addVertexWithUV(n_5, n_1, n_0, (double)f7, (double)f8);
        tessellator.addVertexWithUV(n_5, n_0, n_0, (double)f7, (double)f9);
        tessellator.addVertexWithUV(n_5, n_0, n_1, (double)f6, (double)f9);
        tessellator.draw();
        GL11.glNormal3f(-f10, n_6, n_6);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(n_5, n_0, n_1, (double)f6, (double)f8);
        tessellator.addVertexWithUV(n_5, n_0, n_0, (double)f7, (double)f8);
        tessellator.addVertexWithUV(n_5, n_1, n_0, (double)f7, (double)f9);
        tessellator.addVertexWithUV(n_5, n_1, n_1, (double)f6, (double)f9);
        tessellator.draw();

        for (int i = 0; i < 4; ++i)
        {
            GL11.glRotatef(90.0F, n_7, n_6, n_6);
            GL11.glNormal3f(n_6, n_6, f10);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(n_3, n_1, n_8, (double)f2, (double)f4);
            tessellator.addVertexWithUV(n_2, n_1, n_8, (double)f3, (double)f4);
            tessellator.addVertexWithUV(n_2, n_0, n_8, (double)f3, (double)f5);
            tessellator.addVertexWithUV(n_3, n_0, n_8, (double)f2, (double)f5);
            tessellator.draw();
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    @Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderDart((EntityDart)par1Entity, par2, par4, par6, par8, par9);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		if (((EntityDart)entity).isPoisonous()) 
		{
			return new ResourceLocation("biomesoplenty:textures/projectiles/poisondart.png");
		} 

		return new ResourceLocation("biomesoplenty:textures/projectiles/dart.png");
	}
}