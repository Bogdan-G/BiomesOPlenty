package biomesoplenty.client.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import org.bogdang.modifications.random.XSTR;

public class EntitySteamFX extends EntityFX
{
	private static final ResourceLocation texture = new ResourceLocation("biomesoplenty:textures/particles/steam.png");
    private static final ResourceLocation particles = new ResourceLocation("textures/particle/particles.png");
	
    float steamParticleScale;

	public EntitySteamFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
	{
		this(par1World, par2, par4, par6, par8, par10, par12, 1.0F);
	}

	public EntitySteamFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14)
	{
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        this.motionX *= 0.10000000149011612D;
        this.motionY *= 0.10000000149011612D;
        this.motionZ *= 0.10000000149011612D;
        this.motionX += par8;
        this.motionY += par10;
        this.motionZ += par12;
        this.particleRed = this.particleGreen = this.particleBlue = (float)((new XSTR()).nextDouble() * 0.30000001192092896D);
        this.particleScale *= 0.75F;
        this.particleScale *= par14;
        this.steamParticleScale = this.particleScale;
        this.particleMaxAge = (int)(8.0D / ((new XSTR()).nextDouble() * 0.8D + 0.2D));
        this.particleMaxAge = (int)((float)this.particleMaxAge * par14);
        this.noClip = false;
	}

	@Override
	public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		float f = (particleAge + par2) / particleMaxAge * 32.0F;

		if (f < 0.0F)
		{
			f = 0.0F;
		}

		if (f > 1.0F)
		{
			f = 1.0F;
		}

		particleScale = particleScale * f;

		tessellator.draw();
		GL11.glPushMatrix();

		GL11.glDepthMask(false);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 1);

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		
        float f6 = (float)this.particleTextureIndexX / 16.0F;
        float f7 = f6 + 0.0624375F;
        float f8 = (float)this.particleTextureIndexY / 16.0F;
        float f9 = f8 + 0.0624375F;
        float f10 = 0.1F * this.particleScale;

        if (this.particleIcon != null)
        {
            f6 = this.particleIcon.getMinU();
            f7 = this.particleIcon.getMaxU();
            f8 = this.particleIcon.getMinV();
            f9 = this.particleIcon.getMaxV();
        }

        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)par2 - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)par2 - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)par2 - interpPosZ);
        float f14 = 1.0F;
        
		tessellator.startDrawingQuads();
		tessellator.setBrightness(10);
        
        tessellator.setColorRGBA_F(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, this.particleAlpha);
        float pf0 = par3 * f10;float pf1 = par6 * f10;float pf2 = par4 * f10;float pf3 = par5 * f10;float pf4 = par7 * f10;
        float pp0 = pf0 - pf1;float pp1 = pf0 + pf1;
        float pp2 = f12 - pf2;float pp3 = f12 + pf2;
        float pp4 = pf3 - pf4;float pp5 = pf3 + pf4;
        tessellator.addVertexWithUV((double)(f11 - pp0), (double)(pp2), (double)(f13 - pp4), (double)f7, (double)f9);
        tessellator.addVertexWithUV((double)(f11 - pp1), (double)(pp3), (double)(f13 - pp5), (double)f7, (double)f8);
        tessellator.addVertexWithUV((double)(f11 + pp1), (double)(pp3), (double)(f13 + pp5), (double)f6, (double)f8);
        tessellator.addVertexWithUV((double)(f11 + pp0), (double)(pp2), (double)(f13 + pp4), (double)f6, (double)f9);
        
		tessellator.draw();

		GL11.glDisable(3042);
		GL11.glDepthMask(true);

		GL11.glPopMatrix();

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(particles);
		tessellator.startDrawingQuads();
	}

	 @Override
	 public void onUpdate()
	 {
		 prevPosX = posX;
		 prevPosY = posY;
		 prevPosZ = posZ;

		 if (particleAge++ >= particleMaxAge)
		 {
			 this.setDead();
		 }

		 this.setParticleTextureIndex(7 - particleAge * 8 / particleMaxAge);
		 motionY += 0.004D;
		 this.moveEntity(motionX, motionY, motionZ);

		 if (posY == prevPosY)
		 {
			 motionX *= 1.1D;
			 motionZ *= 1.1D;
		 }

		 motionX *= 0.9599999785423279D;
		 motionY *= 0.9599999785423279D;
		 motionZ *= 0.9599999785423279D;

		 if (onGround)
		 {
			 motionX *= 0.699999988079071D;
			 motionZ *= 0.699999988079071D;
		 }
	 }
}
