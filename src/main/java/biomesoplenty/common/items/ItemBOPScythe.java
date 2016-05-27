package biomesoplenty.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.content.BOPCItems;

public class ItemBOPScythe extends Item
{
	public int textureID = 0;
	
    protected ToolMaterial toolMaterial;

	public ItemBOPScythe(ToolMaterial toolMaterial, int texture)
	{
        this.toolMaterial = toolMaterial;
        this.maxStackSize = 1;
        this.setMaxDamage(toolMaterial.getMaxUses());
		textureID = texture;
        
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
    @Override
	public boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, int x, int y, int z, EntityLivingBase entity)
    {
        int radius = 3;
        int height = 4;
        
        if (toolMaterial == ToolMaterial.IRON || toolMaterial == ToolMaterial.GOLD)
        {
        	if (block != null)
        	{
        		if (block.isLeaves(world, x, y, z))
        			height = 4;
        	}
        	
        	radius = 4;
        }
        else if (toolMaterial == ToolMaterial.EMERALD)
        {
        	if (block != null)
        	{
        		if (block.isLeaves(world, x, y, z))
        			height = 5;
        	}
        	
        	radius = 5;
        }
        else if (toolMaterial == BOPItemHelper.toolMaterialAmethyst)
        {
        	if (block != null)
        	{
        		if (block.isLeaves(world, x, y, z))
        			height = 6;
        	}
        	
        	radius = 6;
        }
        else
        {
        	if (block != null)
        	{
        		if (block.isLeaves(world, x, y, z))
        			height = 0;
        	}
        }
    	
    	if (block != null)
    	{
    		if (block.isLeaves(world, x, y, z))
    		{
        		if (height > 0)
        		{
        			trimLeaves(itemstack, entity, world, x, y, z, height, radius);

        			return true;
        		}
        		else
        		{
					itemstack.damageItem(1, entity);
        		}
    		}
    		else
    		{
    			trimCutCorner(itemstack, entity, world, x, y, z, height, radius);

    			if (world.rand.nextInt(3) == 0)
    			{
    				trim(itemstack, entity, world, x, y, z, height, radius - 1);

    				return true;
    			}
    		}
    	}
    	
        return false;
    }
    
    public void trim(ItemStack stack, EntityLivingBase entity, World world, int x, int y, int z, int height, int radius)
    {
    	for (int aX = -radius; aX <= radius; aX++)
    	{
    		int v_x = x + aX;
    		for (int aY = 0; aY <= radius; aY++)
    		{
    			int v_y = y + aY;
    			for (int aZ = -radius; aZ <= radius; aZ++)
    			{
    				int v_z = z + aZ;
    				Block block = world.getBlock(v_x, v_y, v_z);
    				int meta = world.getBlockMetadata(v_x, v_y, v_z);

    				if (block != null && block != Blocks.air)
    				{
    					if (block == BOPCBlocks.foliage && (meta == 1 || meta == 2 || meta == 6))
    					{
    						if (meta == 1)
    						{
    							block.dropBlockAsItem(world, v_x, v_y, v_z, meta, 0);
    							world.setBlockToAir(v_x, v_y, v_z);
    						}
    						else if (meta == 2)
    						{
    							block.dropBlockAsItem(world, v_x, v_y, v_z, meta, 0);
    							world.setBlock(v_x, v_y, v_z, BOPCBlocks.foliage, 1, 2);
    						}
    						else/* if (meta == 6)*/
    						{
    							block.dropBlockAsItem(world, v_x, v_y, v_z, meta, 0);
    							world.setBlockToAir(v_x, v_y, v_z);
    						}
    					}
    					else if (block == Blocks.tallgrass)
    					{
    						block.dropBlockAsItem(world, v_x, v_y, v_z, meta, 0);
    						world.setBlock(v_x, v_y, v_z, BOPCBlocks.foliage, 2, 2);
    					}
    					else if (block != Blocks.waterlily && block instanceof BlockFlower)
    					{
    						block.dropBlockAsItem(world, v_x, v_y, v_z, meta, 0);
    						world.setBlockToAir(v_x, v_y, v_z);
    					}
    				}
    			}
    		}
    	}
    }
    
    public void trimLeaves(ItemStack stack, EntityLivingBase entity, World world, int x, int y, int z, int height, int radius)
    {
    	for (int aX = -radius; aX <= radius; aX++)
    	{
    		int v_x = x + aX;
    		for (int aY = -radius; aY <= radius; aY++)
    		{
    			int v_y = y+ aY;
    			for (int aZ = -radius; aZ <= radius; aZ++)
    			{
    				int v_z = z + aZ;
    				Block block = world.getBlock(v_x, v_y, v_z);
    				int meta = world.getBlockMetadata(v_x, v_y, v_z);

    				if (block != null)
    				{    					
    					if (block.isLeaves(world, v_x, v_y, v_z))
    					{
        					if (toolMaterial == ToolMaterial.IRON || toolMaterial == ToolMaterial.GOLD)
        					{
        						if (world.rand.nextInt(26) == 0)
        						{
        							stack.damageItem(1, entity);
        						}
        					}
        			        else if (toolMaterial == ToolMaterial.EMERALD)
        			        {
        						if (world.rand.nextInt(27) == 0)
        						{
        							stack.damageItem(1, entity);
        						}
        			        }
        			        else if (toolMaterial == BOPItemHelper.toolMaterialAmethyst)
        			        {
        						if (world.rand.nextInt(28) == 0)
        						{
        							stack.damageItem(1, entity);
        						}
        			        }
        			        else if (world.rand.nextInt(24) == 0)
    						{
    							stack.damageItem(1, entity);
    						}
        			        else
        			        {
        			        	return;
        			        }

    						block.dropBlockAsItem(world, v_x, v_y, v_z, meta, 0);
    						world.setBlockToAir(v_x, v_y, v_z);
    					}
    				}
    			}
    		}
    	}
    }
    
    public void trimCutCorner(ItemStack stack, EntityLivingBase entity, World world, int x, int y, int z, int height, int radius)
    {
    	for (int aX = -radius; aX <= radius; aX++)
    	{
    		int v_x = x + aX;
    		for (int aY = 0; aY <= radius; aY++)
    		{
    			int v_y = y + aY;
    			for (int aZ = -radius; aZ <= radius; aZ++)
    			{
    				int v_z = z + aZ;
    				int v_r = radius * 2;
    				if ((aX + aZ < v_r) && (-aX + aZ < v_r) && (aX + -aZ < v_r) && (-aX + -aZ < v_r))
    				{
        				Block block = world.getBlock(v_x, v_y, v_z);
    					int meta = world.getBlockMetadata(v_x, v_y, v_z);

    					if (block != null && block != Blocks.air)
    					{
        					if (toolMaterial == ToolMaterial.IRON || toolMaterial == ToolMaterial.GOLD)
        					{
        						if (world.rand.nextInt(36) == 0)
        						{
        							stack.damageItem(1, entity);
        						}
        					}
        			        else if (toolMaterial == ToolMaterial.EMERALD)
        			        {
        						if (world.rand.nextInt(37) == 0)
        						{
        							stack.damageItem(1, entity);
        						}
        			        }
        			        else if (toolMaterial == BOPItemHelper.toolMaterialAmethyst)
        			        {
        						if (world.rand.nextInt(38) == 0)
        						{
        							stack.damageItem(1, entity);
        						}
        			        }
        			        else if (world.rand.nextInt(34) == 0)
    						{
    							stack.damageItem(1, entity);
    						}
    						
    						if (block == BOPCBlocks.foliage && (meta == 1 || meta == 2 || meta == 6))
    						{
    							if (meta == 1)
    							{
    								block.dropBlockAsItem(world, v_x, v_y, v_z, meta, 0);
    								world.setBlockToAir(v_x, v_y, v_z);
    							}
    							else if (meta == 2)
    							{
    								block.dropBlockAsItem(world, v_x, v_y, v_z, meta, 0);
    								world.setBlock(v_x, v_y, v_z, BOPCBlocks.foliage, 1, 2);
    							}
    							else/* if (meta == 6)*/
    							{
    								block.dropBlockAsItem(world, v_x, v_y, v_z, meta, 0);
    								world.setBlockToAir(v_x, v_y, v_z);
    							}
    						}
    						else if (block == Blocks.tallgrass)
    						{
    							block.dropBlockAsItem(world, v_x, v_y, v_z, meta, 0);
    							world.setBlock(v_x, v_y, v_z, BOPCBlocks.foliage, 2, 2);
    						}
    						else if (block != Blocks.waterlily && block instanceof BlockFlower)
    						{
    							block.dropBlockAsItem(world, v_x, v_y, v_z, meta, 0);
    							world.setBlockToAir(v_x, v_y, v_z);
    						}
    					}
    				}
    			}
    		}
    	}
    }
    
	@Override
	public float func_150893_a(ItemStack item, Block block)
	{
		return Items.shears.func_150893_a(item, block);
	}

	@Override
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemToRepairWith)
	{
		if (textureID == 0 && itemToRepairWith.getItem() == Item.getItemFromBlock(Blocks.planks))
		{
			return true;
		}
		if (textureID == 1 && itemToRepairWith.getItem() == Item.getItemFromBlock(Blocks.cobblestone))
		{
			return true;
		}
		if (textureID == 2 && itemToRepairWith.getItem() == Items.iron_ingot)
		{
			return true;
		}
		if (textureID == 3 && itemToRepairWith.getItem() == Items.gold_ingot)
		{
			return true;
		}
		if (textureID == 4 && itemToRepairWith.getItem() == Items.diamond)
		{
			return true;
		}
		if (textureID == 6 && itemToRepairWith.getItem() == BOPCItems.misc && itemToRepairWith.getItemDamage() == 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
    public boolean isFull3D()
    {
        return true;
    }

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		if (textureID == 0)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:woodscythe"); 
		}
		else if (textureID == 1)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:stonescythe"); 
		}
		else if (textureID == 2)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:ironscythe"); 
		}
		else if (textureID == 3)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:goldscythe"); 
		}
		else if (textureID == 4)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:diamondscythe"); 
		}
		else if (textureID == 5)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:mudscythe"); 
		}
		else if (textureID == 6)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:amethystscythe"); 
		}
		else 
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:mudball"); 
		}
	}
}
