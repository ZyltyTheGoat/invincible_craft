package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.entity.KreggEntity;

public class CanKreggFlyingAttackProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof KreggEntity _datEntL0 && _datEntL0.getEntityData().get(KreggEntity.DATA_Flying);
	}
}
