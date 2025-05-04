package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

public class DownslamOnEffectActiveTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() + Mth.nextDouble(RandomSource.create(), -0.2, 0.2)), (-2.5), (entity.getDeltaMovement().z() + Mth.nextDouble(RandomSource.create(), -0.2, 0.2))));
	}
}
