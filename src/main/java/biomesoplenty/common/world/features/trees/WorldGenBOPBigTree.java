package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;
import org.bogdang.modifications.random.XSTR;

public class WorldGenBOPBigTree extends WorldGenAbstractTree
{
	static final byte[] otherCoordPairs = new byte[] {(byte)2, (byte)0, (byte)0, (byte)1, (byte)2, (byte)1};

	Random rand = new XSTR();

	World worldObj;
	int[] basePos = new int[] {0, 0, 0};
	int heightLimit;
	int height;
	double heightAttenuation = 0.618D;
	double branchDensity = 1.0D;
	double branchSlope = 0.381D;
	double scaleWidth = 1.0D;
	double leafDensity = 1.0D;

	int trunkSize = 1;

	int heightLimitLimit = 12;

    int leafDistanceLimit = 4;
    
	int[][] leafNodes;

	private final Block wood;
	private final Block leaves;
	
	private final int metaWood;
	private final int metaLeaves;

	public WorldGenBOPBigTree(Block wood, Block leaves, int metaWood, int metaLeaves)
	{
		super(false);
		
		this.wood = wood;
		this.leaves = leaves;
		
		this.metaWood = metaWood;
		this.metaLeaves = metaLeaves;
	}

	void generateLeafNodeList()
	{
		this.height = (int)((double)this.heightLimit * this.heightAttenuation);

		if (this.height >= this.heightLimit)
		{
			this.height = this.heightLimit - 1;
		}

		int i = (int)(1.382D + Math.pow(this.leafDensity * (double)this.heightLimit / 13.0D, 2.0D));

		if (i < 1)
		{
			i = 1;
		}

		int[][] aint = new int[i * this.heightLimit][4];
		int j = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
		int k = 1;
		int l = this.basePos[1] + this.height;
		int i1 = j - this.basePos[1];
		aint[0][0] = this.basePos[0];
		aint[0][1] = j;
		aint[0][2] = this.basePos[2];
		aint[0][3] = l;
		--j;

		while (i1 >= 0)
		{
			int j1 = 0;
			float f = this.layerSize(i1);

			if (f < 0.0F)
			{
				--j;
				--i1;
			}
			else
			{
				for (float d0 = 0.5F; j1 < i; ++j1)
				{
					float d1 = (float)this.scaleWidth * f * (this.rand.nextFloat() + 0.328F);
					float d2 = this.rand.nextFloat() * 2.0F * (float)Math.PI;
					int k1 = MathHelper.floor_float(d1 * (float)Math.sin(d2) + this.basePos[0] + d0);
					int l1 = MathHelper.floor_float(d1 * (float)Math.cos(d2) + this.basePos[2] + d0);
					int[] aint1 = new int[] {k1, j, l1};
					int[] aint2 = new int[] {k1, j + this.leafDistanceLimit, l1};

					if (this.checkBlockLine(aint1, aint2) == -1)
					{
						int[] aint3 = new int[] {this.basePos[0], this.basePos[1], this.basePos[2]};
						float d3 = (float)Math.sqrt(Math.pow(Math.abs(this.basePos[0] - aint1[0]), 2.0F) + Math.pow(Math.abs(this.basePos[2] - aint1[2]), 2.0F));
						float d4 = d3 * (float)this.branchSlope;

						if (aint1[1] - d4 > l)
						{
							aint3[1] = l;
						}
						else
						{
							aint3[1] = (int)(aint1[1] - d4);
						}

						if (this.checkBlockLine(aint3, aint1) == -1)
						{
							aint[k][0] = k1;
							aint[k][1] = j;
							aint[k][2] = l1;
							aint[k][3] = aint3[1];
							++k;
						}
					}
				}

				--j;
				--i1;
			}
		}

		this.leafNodes = new int[k][4];
		System.arraycopy(aint, 0, this.leafNodes, 0, k);
	}

	void func_150529_a(int p_150529_1_, int p_150529_2_, int p_150529_3_, float p_150529_4_, byte p_150529_5_, Block p_150529_6_)
	{
		int l = (int)(p_150529_4_ + 0.618F);
		byte b1 = otherCoordPairs[p_150529_5_];
		byte b2 = otherCoordPairs[p_150529_5_ + 3];
		int[] aint = new int[] {p_150529_1_, p_150529_2_, p_150529_3_};
		int[] aint1 = new int[] {0, 0, 0};
		int i1 = -l;
		int j1 = -l;

		for (aint1[p_150529_5_] = aint[p_150529_5_]; i1 <= l; ++i1)
		{
			aint1[b1] = aint[b1] + i1;
			j1 = -l;

			while (j1 <= l)
			{
				float d0 = (float)(Math.pow(Math.abs(i1) + 0.5F, 2.0F) + Math.pow(Math.abs(j1) + 0.5F, 2.0F));

				if (d0 > (p_150529_4_ * p_150529_4_))
				{
					++j1;
				}
				else
				{
					aint1[b2] = aint[b2] + j1;
					Block block1 = this.worldObj.getBlock(aint1[0], aint1[1], aint1[2]);

					if (!block1.isAir(worldObj, aint1[0], aint1[1], aint1[2]) && !block1.isLeaves(worldObj, aint1[0], aint1[1], aint1[2]))
					{
						++j1;
					}
					else
					{
						this.setBlockAndNotifyAdequately(this.worldObj, aint1[0], aint1[1], aint1[2], p_150529_6_, metaLeaves);
						++j1;
					}
				}
			}
		}
	}

	// JAVADOC METHOD $$ layerSize
	float layerSize(int par1)
	{
		if (par1 < this.heightLimit * 0.3F)
		{
			return -1.618F;
		}
		else
		{
			float f = this.heightLimit / 2.0F;
			float f1 = this.heightLimit / 2.0F - par1;
			float f2;

			if (f1 == 0.0F)
			{
				f2 = f;
			}
			else if (Math.abs(f1) >= f)
			{
				f2 = 0.0F;
			}
			else
			{
				f2 = (float)Math.sqrt(Math.pow(Math.abs(f), 2.0F) - Math.pow(Math.abs(f1), 2.0F));
			}

			f2 *= 0.5F;
			return f2;
		}
	}

	float leafSize(int par1)
	{
		return par1 >= 0 && par1 < this.leafDistanceLimit ? (par1 != 0 && par1 != this.leafDistanceLimit - 1 ? 3.0F : 2.0F) : -1.0F;
	}

	// JAVADOC METHOD $$ generateLeafNode
	void generateLeafNode(final int par1, final int par2, final int par3)
	{
		int l = par2;

		for (int i1 = par2 + this.leafDistanceLimit; l < i1; ++l)
		{
			float f = this.leafSize(l - par2);
			this.func_150529_a(par1, l, par3, f, (byte)1, leaves);
		}
	}

	void func_150530_a(int[] p_150530_1_, int[] p_150530_2_, Block p_150530_3_)
	{
		int[] aint2 = new int[] {0, 0, 0};
		byte b0 = 0;
		byte b1;

		for (b1 = 0; b0 < 3; ++b0)
		{
			aint2[b0] = p_150530_2_[b0] - p_150530_1_[b0];

			if (Math.abs(aint2[b0]) > Math.abs(aint2[b1]))
			{
				b1 = b0;
			}
		}

		if (aint2[b1] != 0)
		{
			byte b2 = otherCoordPairs[b1];
			byte b3 = otherCoordPairs[b1 + 3];
			byte b4;

			if (aint2[b1] > 0)
			{
				b4 = 1;
			}
			else
			{
				b4 = -1;
			}

			float d0 = aint2[b2] / (float)aint2[b1];
			float d1 = aint2[b3] / (float)aint2[b1];
			int[] aint3 = new int[] {0, 0, 0};
			int i = 0;

			for (int j = aint2[b1] + b4; i != j; i += b4)
			{
				aint3[b1] = MathHelper.floor_float((p_150530_1_[b1] + i) + 0.5F);
				aint3[b2] = MathHelper.floor_float(p_150530_1_[b2] + i * d0 + 0.5F);
				aint3[b3] = MathHelper.floor_float(p_150530_1_[b3] + i * d1 + 0.5F);
				byte b5 = (byte)metaWood;
				int k = Math.abs(aint3[0] - p_150530_1_[0]);
				int l = Math.abs(aint3[2] - p_150530_1_[2]);
				int i1 = Math.max(k, l);

				if (i1 > 0)
				{
					if (k == i1)
					{
						b5 += 4;
					}
					else if (l == i1)
					{
						b5 += 8;
					}
				}

				this.setBlockAndNotifyAdequately(this.worldObj, aint3[0], aint3[1], aint3[2], p_150530_3_, b5);
			}
		}
	}

	void generateLeaves()
	{
		int i = 0;

		for (int j = this.leafNodes.length; i < j; ++i)
		{
			int k = this.leafNodes[i][0];
			int l = this.leafNodes[i][1];
			int i1 = this.leafNodes[i][2];
			this.generateLeafNode(k, l, i1);
		}
	}

	boolean leafNodeNeedsBase(int par1)
	{
		return par1 >= this.heightLimit * 0.2F;
	}

	void generateTrunk()
	{
		int i = this.basePos[0];
		int j = this.basePos[1];
		int k = this.basePos[1] + this.height;
		int l = this.basePos[2];
		int[] aint = new int[] {i, j, l};
		int[] aint1 = new int[] {i, k, l};
		this.func_150530_a(aint, aint1, wood);

		if (this.trunkSize == 2)
		{
			++aint[0];
			++aint1[0];
			this.func_150530_a(aint, aint1, wood);
			++aint[2];
			++aint1[2];
			this.func_150530_a(aint, aint1, wood);
			aint[0] += -1;
			aint1[0] += -1;
			this.func_150530_a(aint, aint1, wood);
		}
	}

	// JAVADOC METHOD $$ generateLeafNodeBases
	void generateLeafNodeBases()
	{
		int i = 0;
		int j = this.leafNodes.length;

		for (int[] aint = new int[] {this.basePos[0], this.basePos[1], this.basePos[2]}; i < j; ++i)
		{
			int[] aint1 = this.leafNodes[i];
			int[] aint2 = new int[] {aint1[0], aint1[1], aint1[2]};
			aint[1] = aint1[3];
			int k = aint[1] - this.basePos[1];

			if (this.leafNodeNeedsBase(k))
			{
				this.func_150530_a(aint, aint2, wood);
			}
		}
	}

	// JAVADOC METHOD $$ checkBlockLine
	int checkBlockLine(final int[] par1ArrayOfInteger, final int[] par2ArrayOfInteger)
	{
		int[] aint2 = new int[] {0, 0, 0};
		byte b1 = 0;

		aint2[0] = par2ArrayOfInteger[0] - par1ArrayOfInteger[0];
		aint2[1] = par2ArrayOfInteger[1] - par1ArrayOfInteger[1];
		if (Math.abs(aint2[1]) > Math.abs(aint2[0])) b1 = 1;
		aint2[2] = par2ArrayOfInteger[2] - par1ArrayOfInteger[2];
		if (Math.abs(aint2[2]) > Math.abs(aint2[b1])) b1 = 2;

		if (aint2[b1] == 0) return -1;

		byte b2 = otherCoordPairs[b1];
		byte b3 = otherCoordPairs[b1 + 3];
		byte b4;

		if (aint2[b1] > 0) b4 = 1;
		else b4 = -1;

		float d0 = aint2[b2] / (float)aint2[b1];
		float d1 = aint2[b3] / (float)aint2[b1];
		int[] aint3 = new int[] {0, 0, 0};
		int i = 0;
		int j;

		for (j = aint2[b1] + b4; i != j; i += b4)
		{
			aint3[b1] = par1ArrayOfInteger[b1] + i;
			aint3[b2] = MathHelper.floor_float(par1ArrayOfInteger[b2] + i * d0);
			aint3[b3] = MathHelper.floor_float(par1ArrayOfInteger[b3] + i * d1);
			//useless get -> not a full Forge patch, call getBlock() is not cheap, 
			//especially when there are a lot of them, they slow down thus the speed of generation of the world
			//Block block = this.worldObj.getBlock(aint3[0], aint3[1], aint3[2]);

			if (!this.isReplaceable(worldObj, aint3[0], aint3[1], aint3[2])) break;
		}

		return i == j ? -1 : Math.abs(i);
	}

	// JAVADOC METHOD $$ validTreeLocation
	boolean validTreeLocation()
	{
		final int[] aint = new int[] {this.basePos[0], this.basePos[1], this.basePos[2]};
		final int[] aint1 = new int[] {this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2]};
		if (!this.worldObj.getBlock(this.basePos[0], this.basePos[1] - 1, this.basePos[2]).canSustainPlant(worldObj, basePos[0], basePos[1] - 1, basePos[2], ForgeDirection.UP, (BlockSapling)Blocks.sapling)) return false;

		final int i = this.checkBlockLine(aint, aint1);
		if (i == -1) return true;
		if (i < 6) return false;
		this.heightLimit = i;
		return true;
	}

	@Override
	public void setScale(double par1, double par3, double par5)
	{
		this.heightLimitLimit = (int)(par1 * 12.0D);

		if (par1 > 0.5D)
		{
			this.leafDistanceLimit = 5;
		}

		this.scaleWidth = par3;
		this.leafDensity = par5;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		this.worldObj = par1World;
		//long l = par2Random.nextLong();
		this.rand.setSeed(par2Random.nextLong());
		this.basePos[0] = par3;
		this.basePos[1] = par4;
		this.basePos[2] = par5;

		if (this.heightLimit == 0)
		{
			this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit);
		}

		if (!this.validTreeLocation())
		{
			this.worldObj = null; //Code parse from Forge patch (data method copy from vanilla, yes?) //Fix vanilla Mem leak, holds latest world
			return false;
		}

		this.generateLeafNodeList();
		this.generateLeaves();
		this.generateTrunk();
		this.generateLeafNodeBases();
		this.worldObj = null; //Fix vanilla Mem leak, holds latest world
		return true;
	}
}
