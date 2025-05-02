package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.entity.ViltrumiteEntity;

public class CanViltrumiteFlyingAttackProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity instanceof ViltrumiteEntity _datEntL0 && _datEntL0.getEntityData().get(ViltrumiteEntity.DATA_flying);
	}
}
