package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.entity.LucanEntity;

public class CanLucanFlyingAttackProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof LucanEntity _datEntL0 && _datEntL0.getEntityData().get(LucanEntity.DATA_Flying);
	}
}
