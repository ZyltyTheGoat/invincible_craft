package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.entity.AnissaEntity;

public class CanAnissaFlyingAttackProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof AnissaEntity _datEntL0 && _datEntL0.getEntityData().get(AnissaEntity.DATA_Flying);
	}
}
