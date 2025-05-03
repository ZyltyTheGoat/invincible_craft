
package net.mcreator.invincible_craft.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class ChainItem extends Item {
	public ChainItem() {
		super(new Item.Properties().durability(250).rarity(Rarity.COMMON));
	}

	@Override
	public int getEnchantmentValue() {
		return 14;
	}
}
