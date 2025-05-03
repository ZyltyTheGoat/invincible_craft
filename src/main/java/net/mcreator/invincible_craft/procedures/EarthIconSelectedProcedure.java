package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

public class EarthIconSelectedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putString("selectedDim", "Earth");
	}
}
