package net.mcreator.invincible_craft.procedures;

import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;
import net.minecraft.core.BlockPos;

import net.mcreator.invincible_craft.network.InvincibleCraftModVariables;
import net.mcreator.invincible_craft.init.InvincibleCraftModMobEffects;
import net.mcreator.invincible_craft.init.InvincibleCraftModEntities;
import net.mcreator.invincible_craft.InvincibleCraftMod;

import java.util.List;
import java.util.Iterator;

public class AbilityButton5OnKeyPressedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!DoesHaveCDAbility5Procedure.execute(entity)) {
			if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("Viltrumite")) {
				if (world.isClientSide()) {
					SetupAnimationsProcedure.setAnimationClientside((Player) entity, "sonic_clap", true);
				}
				if (!world.isClientSide()) {
					if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
						List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
						synchronized (connections) {
							Iterator<Connection> iterator = connections.iterator();
							while (iterator.hasNext()) {
								Connection connection = iterator.next();
								if (!connection.isConnecting() && connection.isConnected())
									InvincibleCraftMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.InvincibleCraftModAnimationMessage(Component.literal("sonic_clap"), entity.getId(), true), connection, NetworkDirection.PLAY_TO_CLIENT);
							}
						}
					}
				}
				{
					double _setval = 4;
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.ability_cooldown_5 = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				InvincibleCraftMod.queueServerWork(8, () -> {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(InvincibleCraftModMobEffects.SCREEN_SHAKE.get(), 4, 0, false, false));
					SonicClapAttackProcedure.execute(world, entity);
				});
			} else if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("DimensionalTravel")) {
				if (!entity.getPersistentData().getBoolean("openedPortal")) {
					entity.getPersistentData().putBoolean("openedPortal", true);
					if (world instanceof ServerLevel _serverLevel) {
						Entity entityinstance = InvincibleCraftModEntities.REFUGE_PORTAL.get().create(_serverLevel, null, null,
								BlockPos.containing(entity.getX() + 4 * entity.getLookAngle().x, entity.getY() + 1.6 + 4 * entity.getLookAngle().y, entity.getZ() + 4 * entity.getLookAngle().z), MobSpawnType.MOB_SUMMONED, false, false);
						if (entityinstance != null) {
							entityinstance.setYRot(world.getRandom().nextFloat() * 360.0F);
							if (entityinstance instanceof TamableAnimal _toTame && entity instanceof Player _owner)
								_toTame.tame(_owner);
							{
								Entity _ent = entityinstance;
								_ent.setYRot(entity.getYRot());
								_ent.setXRot(entity.getXRot());
								_ent.setYBodyRot(_ent.getYRot());
								_ent.setYHeadRot(_ent.getYRot());
								_ent.yRotO = _ent.getYRot();
								_ent.xRotO = _ent.getXRot();
								if (_ent instanceof LivingEntity _entity) {
									_entity.yBodyRotO = _entity.getYRot();
									_entity.yHeadRotO = _entity.getYRot();
								}
							}
							_serverLevel.addFreshEntity(entityinstance);
						}
					}
				}
			} else if (((entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new InvincibleCraftModVariables.PlayerVariables())).power).equals("BattleBeast")) {
				{
					double _setval = 100;
					entity.getCapability(InvincibleCraftModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.battle_beast_bite_timer = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
