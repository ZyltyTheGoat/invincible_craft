package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

public class FireballGlobalWhileProjectileFlyingTickProcedure {
	public static void execute(Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		if (immediatesourceentity.getPersistentData().getDouble("Timer") < 60) {
			immediatesourceentity.getPersistentData().putDouble("Timer", (immediatesourceentity.getPersistentData().getDouble("Timer") + 1));
		} else {
			if (!immediatesourceentity.level().isClientSide())
				immediatesourceentity.discard();
		}
		immediatesourceentity.setNoGravity(true);
	}
}
