package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;

public class BattleBeastMaceLivingEntityIsHitWithItemProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_FALL.get()), (entity.getX()), (entity.getY() + entity.getBbHeight() / 2), (entity.getZ()), 10, 0.25, 0.25, 0.25, 0.25);
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (InvincibleCraftModParticleTypes.SPARK.get()), (entity.getX()), (entity.getY() + entity.getBbHeight() / 2), (entity.getZ()), 5, 0.5, 0.5, 0.5, 0);
	}
}
