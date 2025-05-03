package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;

public class DuplicationCloneAbilityProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double counter = 0;
		entity.getPersistentData().putBoolean("foundRand", false);
		{
			double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).clone_number + 1;
			entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.clone_number = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		while (!entity.getPersistentData().getBoolean("foundRand")) {
			entity.getPersistentData().putDouble("rx", (entity.getX() + Mth.nextInt(RandomSource.create(), -2, 2)));
			entity.getPersistentData().putDouble("ry", (entity.getY() + Mth.nextInt(RandomSource.create(), -2, 2)));
			entity.getPersistentData().putDouble("rz", (entity.getZ() + Mth.nextInt(RandomSource.create(), -2, 2)));
			counter = counter + 1;
			if (counter >= 100) {
				break;
			}
			if ((world.getBlockState(BlockPos.containing(entity.getPersistentData().getDouble("rx"), entity.getPersistentData().getDouble("ry"), entity.getPersistentData().getDouble("rz")))).getBlock() == Blocks.AIR
					&& world.getBlockFloorHeight(BlockPos.containing(entity.getPersistentData().getDouble("rx"), entity.getPersistentData().getDouble("ry") - 1, entity.getPersistentData().getDouble("rz"))) > 0) {
				entity.getPersistentData().putBoolean("foundRand", true);
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (entity.getPersistentData().getDouble("rx")), (entity.getPersistentData().getDouble("ry") + 1), (entity.getPersistentData().getDouble("rz")), 5, 0.5, 1, 0.5, 0.1);
				{
					double _setval = (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).duplication_percentage
							- (10 + 2 * (entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).clone_number);
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.duplication_percentage = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).duplication_percentage < 0) {
					{
						double _setval = 0;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.duplication_percentage = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
				if (world instanceof ServerLevel _serverLevel) {
					Entity entityinstance = InvincibleCraftModEntities.DUPLICATION_CLONE.get().create(_serverLevel, null, null,
							BlockPos.containing(entity.getPersistentData().getDouble("rx"), entity.getPersistentData().getDouble("ry"), entity.getPersistentData().getDouble("rz")), MobSpawnType.MOB_SUMMONED, false, false);
					if (entityinstance != null) {
						entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
						if (entityinstance instanceof TamableAnimal _toTame && entity instanceof Player _owner)
							_toTame.tame(_owner);// Inside your clone spawning code, right after adding the entity to the world:
						if (entityinstance != null) {
							// === ADD THESE LINES === //
							// Get or create the clone list
							ListTag clonesList = entity.getPersistentData().getList("clones", 10);
							// Store the new clone's UUID
							clonesList.add(StringTag.valueOf(entityinstance.getUUID().toString()));
							// Save back to persistent data
							entity.getPersistentData().put("clones", clonesList);
							// === END ADDITION === //
						}
						_serverLevel.addFreshEntity(entityinstance);
					}
				}
			} else {
				continue;
			}
		}
	}
}
