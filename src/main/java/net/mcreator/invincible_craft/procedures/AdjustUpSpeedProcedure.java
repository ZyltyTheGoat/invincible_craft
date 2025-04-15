package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;

public class AdjustUpSpeedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).adj_speed < 100) {
			if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).adj_speed + 5 > 100) {
				{
					double _setval = 100;
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.adj_speed = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				AttributeHandlerProcedure.execute(world, entity);
			} else {
				{
					double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).adj_speed + 5;
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.adj_speed = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				AttributeHandlerProcedure.execute(world, entity);
			}
		}
	}
}
