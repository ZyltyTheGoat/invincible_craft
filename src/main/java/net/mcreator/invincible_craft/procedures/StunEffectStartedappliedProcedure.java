package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;

public class StunEffectStartedappliedProcedure {
	public static void execute(Entity entity, double amplifier) {
		if (entity == null)
			return;
		if (amplifier > 0) {
			{
				boolean _setval = false;
				entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.flying = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			entity.setNoGravity(false);
		}
	}
}
