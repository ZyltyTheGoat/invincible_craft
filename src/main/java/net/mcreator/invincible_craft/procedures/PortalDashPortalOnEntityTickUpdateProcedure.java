package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Entity;

public class PortalDashPortalOnEntityTickUpdateProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getDouble("Timer") < 15) {
			entity.getPersistentData().putDouble("Timer", (entity.getPersistentData().getDouble("Timer") + 1));
		} else {
			if (!entity.level().isClientSide())
				entity.discard();
		}
		entity.setDeltaMovement(new Vec3(0, 0, 0));
	}
}
