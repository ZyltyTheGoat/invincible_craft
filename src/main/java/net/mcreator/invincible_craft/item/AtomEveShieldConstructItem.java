
package net.mcreator.invincible_craft.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.procedures.AtomEveSwordConstructToolInInventoryTickProcedure;

public class AtomEveShieldConstructItem extends ShieldItem {
	public AtomEveShieldConstructItem() {
		super(new Item.Properties().durability(5));
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		AtomEveSwordConstructToolInInventoryTickProcedure.execute(entity, itemstack);
	}
}
