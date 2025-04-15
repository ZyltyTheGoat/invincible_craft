
package net.mcreator.invincible_craft.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class SuitFabricItem extends Item {
	public SuitFabricItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.RARE));
	}
}
