package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class MotionOnEffectActiveTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world.dayTime() % 2 == 0) {
			entity.setDeltaMovement(new Vec3((entity.getPersistentData().getDouble("motX")), (entity.getPersistentData().getDouble("motY")), (entity.getPersistentData().getDouble("motZ"))));
		}
	}
}
