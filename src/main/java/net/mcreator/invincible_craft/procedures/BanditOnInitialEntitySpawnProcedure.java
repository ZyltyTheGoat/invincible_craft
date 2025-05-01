package net.mcreator.invincible_craft.procedures;

import virtuoel.pehkui.api.ScaleTypes;
import virtuoel.pehkui.api.ScaleOperations;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;

import net.mcreator.invincible_craft.init.InvincibleCraftModItems;
import net.mcreator.invincible_craft.entity.BanditEntity;

public class BanditOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double ran_weapon = 0;
		double random = 0;
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
		random = Mth.nextInt(RandomSource.create(), 1, 6);
		if (random <= 3) {
			if (entity instanceof LivingEntity _livingEntity16 && _livingEntity16.getAttributes().hasAttribute(Attributes.MAX_HEALTH))
				_livingEntity16.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
			if (entity instanceof LivingEntity _livingEntity17 && _livingEntity17.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE))
				_livingEntity17.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(1);
			if (entity instanceof LivingEntity _livingEntity18 && _livingEntity18.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED))
				_livingEntity18.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.15);
			entity.setCustomName(Component.literal("Thug"));
		} else if (random <= 5) {
			if (entity instanceof LivingEntity _livingEntity20 && _livingEntity20.getAttributes().hasAttribute(Attributes.MAX_HEALTH))
				_livingEntity20.getAttribute(Attributes.MAX_HEALTH).setBaseValue(25);
			if (entity instanceof LivingEntity _livingEntity21 && _livingEntity21.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE))
				_livingEntity21.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(3);
			if (entity instanceof LivingEntity _livingEntity22 && _livingEntity22.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED))
				_livingEntity22.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25);
			entity.setCustomName(Component.literal("Mercenary"));
			ScaleTypes.WIDTH.getScaleData(entity).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.WIDTH.getScaleData(entity).getTargetScale(), 1.25));
			ScaleTypes.HITBOX_WIDTH.getScaleData(entity).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HITBOX_WIDTH.getScaleData(entity).getTargetScale(), 1.25));
			ScaleTypes.HEIGHT.getScaleData(entity).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HEIGHT.getScaleData(entity).getTargetScale(), 1.25));
			ScaleTypes.HITBOX_HEIGHT.getScaleData(entity).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HITBOX_HEIGHT.getScaleData(entity).getTargetScale(), 1.25));
		} else {
			if (entity instanceof LivingEntity _livingEntity28 && _livingEntity28.getAttributes().hasAttribute(Attributes.MAX_HEALTH))
				_livingEntity28.getAttribute(Attributes.MAX_HEALTH).setBaseValue(30);
			if (entity instanceof LivingEntity _livingEntity29 && _livingEntity29.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE))
				_livingEntity29.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(5);
			if (entity instanceof LivingEntity _livingEntity30 && _livingEntity30.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED))
				_livingEntity30.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35);
			entity.setCustomName(Component.literal("Warlord"));
			ScaleTypes.WIDTH.getScaleData(entity).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.WIDTH.getScaleData(entity).getTargetScale(), 1.5));
			ScaleTypes.HITBOX_WIDTH.getScaleData(entity).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HITBOX_WIDTH.getScaleData(entity).getTargetScale(), 1.5));
			ScaleTypes.HEIGHT.getScaleData(entity).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HEIGHT.getScaleData(entity).getTargetScale(), 1.5));
			ScaleTypes.HITBOX_HEIGHT.getScaleData(entity).setTargetScale((float) ScaleOperations.SET.applyAsDouble(ScaleTypes.HITBOX_HEIGHT.getScaleData(entity).getTargetScale(), 1.5));
		}
	}
}
