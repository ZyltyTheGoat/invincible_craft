package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.entity.ViltrumiteEntity;

public class ViltrumiteDisplayConditionEyes2Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_eyes) : 0) == 2) {
			return true;
		}
		return false;
	}
}
