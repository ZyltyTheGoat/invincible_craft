package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.entity.ViltrumiteEntity;

public class ReturnGenderProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return (entity instanceof ViltrumiteEntity _datEntI ? _datEntI.getEntityData().get(ViltrumiteEntity.DATA_gender) : 0) == 2;
	}
}
