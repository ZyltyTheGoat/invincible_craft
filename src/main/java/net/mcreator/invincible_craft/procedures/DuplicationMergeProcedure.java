package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.entity.DuplicationCloneEntity;

import java.util.List;
import java.util.ArrayList;

public class DuplicationMergeProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		List<Object> clones = new ArrayList<>();
		double i = 0;
		if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("Duplication")) {
			if (world instanceof ServerLevel) {
				for (Entity entityiterator : ((ServerLevel) world).getAllEntities()) {
					if (entityiterator == null)
						continue;
					if ((entityiterator instanceof TamableAnimal _tamIsTamedBy && entity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false) && entityiterator instanceof DuplicationCloneEntity) {
						if ((entityiterator.level().dimension()) == (entity.level().dimension())) {
							clones.add(entityiterator);
						}
					}
				}
			}
			for (int index0 = 0; index0 < (int) clones.size(); index0++) {
				if (!(clones.get((int) i) instanceof Entity _e ? _e : null).level().isClientSide())
					(clones.get((int) i) instanceof Entity _e ? _e : null).discard();
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, ((clones.get((int) i) instanceof Entity _e ? _e : null).getX()), ((clones.get((int) i) instanceof Entity _e ? _e : null).getY() + 1),
							((clones.get((int) i) instanceof Entity _e ? _e : null).getZ()), 5, 0.5, 1, 0.5, 0.1);
				{
					double _setval = 0;
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.clone_number = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).duplication_percentage + 10 <= 100) {
					{
						double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).duplication_percentage + 10;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.duplication_percentage = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					{
						double _setval = 100;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.duplication_percentage = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
				i = i + 1;
			}
		}
	}
}
