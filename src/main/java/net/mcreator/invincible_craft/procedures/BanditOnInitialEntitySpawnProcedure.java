package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.mcreator.invincible_craft.init.InvincibleCraftModItems;
import net.mcreator.invincible_craft.entity.BanditEntity;

public class BanditOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double ran_weapon = 0;
		if (entity instanceof BanditEntity _datEntSetI)
			_datEntSetI.getEntityData().set(BanditEntity.DATA_base, Mth.nextInt(RandomSource.create(), 1, 3));
		if (entity instanceof BanditEntity _datEntSetI)
			_datEntSetI.getEntityData().set(BanditEntity.DATA_hair, Mth.nextInt(RandomSource.create(), 1, 7));
		if (entity instanceof BanditEntity _datEntSetI)
			_datEntSetI.getEntityData().set(BanditEntity.DATA_top, Mth.nextInt(RandomSource.create(), 1, 7));
		if (entity instanceof BanditEntity _datEntSetI)
			_datEntSetI.getEntityData().set(BanditEntity.DATA_bottom, Mth.nextInt(RandomSource.create(), 1, 4));
		if (entity instanceof BanditEntity _datEntSetI)
			_datEntSetI.getEntityData().set(BanditEntity.DATA_mask, Mth.nextInt(RandomSource.create(), 1, 10));
		if (Math.random() < (1) / ((float) 2)) {
			ran_weapon = Mth.nextInt(RandomSource.create(), 1, 3);
			if (ran_weapon == 1) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(InvincibleCraftModItems.WOODEN_BAT.get()).copy();
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			} else if (ran_weapon == 2) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(InvincibleCraftModItems.METAL_BAT.get()).copy();
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			} else {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(InvincibleCraftModItems.CHAIN.get()).copy();
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
			}
		}
	}
}
