package biomesoplenty.common.eventhandler.misc;

import biomesoplenty.api.content.BOPCBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class AchievementEventHandler {
	
	@SubscribeEvent
	public void woodMined(PlayerEvent.ItemPickupEvent event){
		
		final ItemStack pickedUpStack = event.pickedUp.getEntityItem();
		//when in getEntityItem() null or vanilla stone, include UBC (mod no override getEntityItem())
		if (!(pickedUpStack.field_151002_e == Item.getItemFromBlock(Blocks.stone)) && (pickedUpStack.field_151002_e == Item.getItemFromBlock(BOPCBlocks.logs1) || pickedUpStack.field_151002_e == Item.getItemFromBlock(BOPCBlocks.logs2) || pickedUpStack.field_151002_e == Item.getItemFromBlock(BOPCBlocks.logs3) || pickedUpStack.field_151002_e == Item.getItemFromBlock(BOPCBlocks.logs4)) && (pickedUpStack.itemDamage == 0 || pickedUpStack.itemDamage == 1 || pickedUpStack.itemDamage == 2 || pickedUpStack.itemDamage == 3)) {
			event.player.addStat(AchievementList.mineWood, 1);
			return;
		}
	}
}
