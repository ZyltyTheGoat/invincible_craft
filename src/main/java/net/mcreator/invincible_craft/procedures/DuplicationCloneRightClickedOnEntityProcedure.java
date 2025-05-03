package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;

public class DuplicationCloneRightClickedOnEntityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		double clone_x = 0;
		double clone_y = 0;
		double clone_z = 0;
		if ((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null) == sourceentity) {
			if (!entity.level().isClientSide())
				entity.discard();
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, (y + 1), z, 5, 0.5, 0.5, 0.5, 0.1);
			{
				double _setval = ((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new InvincibleCraftModVariables.PlayerVariables())).clone_number - 1;
				(entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.clone_number = _setval;
					capability.syncPlayerVariables((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null));
				});
			}
			if (((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new InvincibleCraftModVariables.PlayerVariables())).duplication_percentage + 10 <= 100) {
				{
					double _setval = ((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new InvincibleCraftModVariables.PlayerVariables())).duplication_percentage + 10
							+ 2 * ((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new InvincibleCraftModVariables.PlayerVariables())).clone_number;
					(entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.duplication_percentage = _setval;
						capability.syncPlayerVariables((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null));
					});
				}
			} else {
				{
					double _setval = 100;
					(entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null).getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.duplication_percentage = _setval;
						capability.syncPlayerVariables((entity instanceof TamableAnimal _tamEnt ? (Entity) _tamEnt.getOwner() : null));
					});
				}
			}
		}
	}
}
