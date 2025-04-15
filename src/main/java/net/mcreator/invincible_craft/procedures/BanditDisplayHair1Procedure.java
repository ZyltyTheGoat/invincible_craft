package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.entity.BanditEntity;

public class BanditDisplayHair1Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof BanditEntity _datEntI ? _datEntI.getEntityData().get(BanditEntity.DATA_hair) : 0) == 1) {
			return true;
		}
		return false;
	}
}
