package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.entity.BanditEntity;

public class BanditDisplayBottom4Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof BanditEntity _datEntI ? _datEntI.getEntityData().get(BanditEntity.DATA_bottom) : 0) == 4) {
			return true;
		}
		return false;
	}
}
