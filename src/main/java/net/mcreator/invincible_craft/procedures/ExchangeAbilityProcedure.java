package net.mcreator.invincible_craft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;

public class ExchangeAbilityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).exchange_targetting) {
			if (world instanceof ServerLevel) {
				for (Entity entityiterator : ((ServerLevel) world).getAllEntities()) {
					if (entityiterator == null)
						continue;
					if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).exchange_target).equals(entityiterator.getStringUUID())) {
						{
							Entity _ent = entity;
							_ent.teleportTo((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()));
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), _ent.getYRot(), _ent.getXRot());
						}
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 5, 0.5, 1, 0.5, 0.1);
						{
							Entity _ent = entity;
							if (!_ent.level().isClientSide() && _ent.getServer() != null) {
								_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
										_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "forceload remove ~ ~");
							}
						}
						if (!entityiterator.level().isClientSide())
							entityiterator.discard();
						{
							boolean _setval = false;
							entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.exchange_targetting = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							String _setval = "";
							entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.exchange_target = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				}
			}
		} else {
			if (world instanceof ServerLevel _serverLevel) {
				Entity entityinstance = InvincibleCraftModEntities.EXCHANGE_CLONE.get().create(_serverLevel, null, null, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED, false, false);
				if (entityinstance != null) {
					entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
					if (entityinstance instanceof TamableAnimal _toTame && entity instanceof Player _owner)
						_toTame.tame(_owner);
					{
						Entity _ent = entity;
						if (!_ent.level().isClientSide() && _ent.getServer() != null) {
							_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
									_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "forceload add ~ ~");
						}
					}
					{
						boolean _setval = true;
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.exchange_targetting = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						String _setval = entityinstance.getStringUUID();
						entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.exchange_target = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					_serverLevel.addFreshEntity(entityinstance);
				}
			}
		}
	}
}
