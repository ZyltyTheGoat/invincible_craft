package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.entity.DuplicationCloneEntity;

public class DuplicatioExchangeReturnProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (!(entity instanceof DuplicationCloneEntity _datEntL0 && _datEntL0.getEntityData().get(DuplicationCloneEntity.DATA_exchange_target))) {
			return true;
		}
		return false;
	}
}
