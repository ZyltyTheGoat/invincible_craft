package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.invincible_craft.init.InvincibleCraftModParticleTypes;

public class BloodFallAdditionalParticleExpiryConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, boolean onGround) {
		double random = 0;
		if (onGround == true) {
			if (Math.random() < (1) / ((float) 2)) {
				world.addParticle((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_LAND.get()), x, y, z, 0, 0, 0);
			} else {
				random = Mth.nextInt(RandomSource.create(), 1, 4);
				if (random == 1) {
					world.addParticle((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_SPLASH.get()), x, (y + 0.2), z, 0, 0.2, 0);
				} else if (random == 1) {
					world.addParticle((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_SPLASH_2.get()), x, (y + 0.2), z, 0, 0.2, 0);
				} else if (random == 1) {
					world.addParticle((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_SPLASH_3.get()), x, (y + 0.2), z, 0, 0.2, 0);
				} else {
					world.addParticle((SimpleParticleType) (InvincibleCraftModParticleTypes.BLOOD_SPLASH_4.get()), x, (y + 0.2), z, 0, 0.2, 0);
				}
			}
			return true;
		}
		return false;
	}
}
