package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;

public class PunchLimitEffectExpiresProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(InvincibleCraftModMobEffects.PUNCH_LIMIT.get()) ? _livEnt.getEffect(InvincibleCraftModMobEffects.PUNCH_LIMIT.get()).getDuration() : 0) == 1) {
			{
				double _setval = 0;
				entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.punch_limit = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			entity.getPersistentData().putString("track", "");
		}
	}
}
